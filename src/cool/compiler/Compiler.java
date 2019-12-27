package cool.compiler;

import cool.lexer.CoolLexer;
import cool.parser.CoolParser;
import cool.structures.DefinitionPassVisitor;
import cool.structures.FindTypesVisitor;
import cool.structures.ResolutionPassVisitor;
import cool.structures.SymbolTable;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Compiler {
    // Annotates class nodes with the names of files where they are defined.
    public static ParseTreeProperty<String> fileNames = new ParseTreeProperty<>();
    public static String currentFile = null;

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("No file(s) given");
            return;
        }
        
        CoolLexer lexer = null;
        CommonTokenStream tokenStream = null;
        CoolParser parser = null;
        ParserRuleContext globalTree = null;
        
        // True if any lexical or syntax errors occur.
        boolean lexicalSyntaxErrors = false;
        
        // Parse each input file and build one big parse tree out of
        // individual parse trees.
        for (var fileName : args) {
            var input = CharStreams.fromFileName(fileName);
            currentFile = fileName;
            
            // Lexer
            if (lexer == null)
                lexer = new CoolLexer(input);
            else
                lexer.setInputStream(input);

            // Token stream
            if (tokenStream == null)
                tokenStream = new CommonTokenStream(lexer);
            else
                tokenStream.setTokenSource(lexer);
                
            /*
            // Test lexer only.
            tokenStream.fill();
            List<Token> tokens = tokenStream.getTokens();
            tokens.stream().forEach(token -> {
                var text = token.getText();
                var name = CoolLexer.VOCABULARY.getSymbolicName(token.getType());
                
                System.out.println(text + " : " + name);
                //System.out.println(token);
            });
            */
            
            // Parser
            if (parser == null)
                parser = new CoolParser(tokenStream);
            else
                parser.setTokenStream(tokenStream);
            
            // Customized error listener, for including file names in error
            // messages.
            var errorListener = new BaseErrorListener() {
                public boolean errors = false;
                
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer,
                                        Object offendingSymbol,
                                        int line, int charPositionInLine,
                                        String msg,
                                        RecognitionException e) {
                    String newMsg = "\"" + new File(fileName).getName() + "\", line " +
                                        line + ":" + (charPositionInLine + 1) + ", ";
                    
                    Token token = (Token)offendingSymbol;
                    if (token.getType() == CoolLexer.ERROR)
                        newMsg += "Lexical error: " + token.getText();
                    else
                        newMsg += "Syntax error: " + msg;
                    
                    System.err.println(newMsg);
                    errors = true;
                }
            };
            
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);
            
            // Actual parsing
            var tree = parser.program();
            if (globalTree == null)
                globalTree = tree;
            else
                // Add the current parse tree's children to the global tree.
                for (int i = 0; i < tree.getChildCount(); i++)
                    globalTree.addAnyChild(tree.getChild(i));
                    
            // Annotate class nodes with file names, to be used later
            // in semantic error messages.
            for (int i = 0; i < tree.getChildCount(); i++) {
                var child = tree.getChild(i);
                // The only ParserRuleContext children of the program node
                // are class nodes.
                if (child instanceof ParserRuleContext)
                    fileNames.put(child, fileName);
            }
            
            // Record any lexical or syntax errors.
            lexicalSyntaxErrors |= errorListener.errors;

            if (lexicalSyntaxErrors) {
                System.err.println("Compilation halted");
                return;
            }

            var astVisitor = new ASTVisitor();
            var printTree = new NodeVisitor();

            var ast = astVisitor.visit(tree);
            // ast.accept(printTree);

            var definitionPassVisitor = new DefinitionPassVisitor();
            var findTypesVisitor = new FindTypesVisitor();
            var resolutionPassVisitor = new ResolutionPassVisitor();

            ast.accept(findTypesVisitor);
            ast.accept(definitionPassVisitor);

            // add inherited methods from super class
            for (var entry : SymbolTable.classesAndParents.entrySet()) {
                var className = entry.getKey();
                var parent = entry.getValue();
                if (parent != null) {
                    var inheritedMethods = SymbolTable.classesAndMethods.get(parent);
                    if (inheritedMethods != null) {
                        var methods = SymbolTable.classesAndMethods.get(className);
                        if (methods == null) {
                            methods = new ArrayList<>();
                        }
                        methods.addAll(inheritedMethods);
                        SymbolTable.classesAndMethods.put(className, methods);
                    }
                }
            }

            SymbolTable.classesAndParents.put("IO", null);


            ast.accept(resolutionPassVisitor);

            SymbolTable.classesAndMethods.clear();
            SymbolTable.classesAndVariables.clear();
            SymbolTable.classesAndParents.clear();

            if (SymbolTable.hasSemanticErrors()) {
                System.err.println("Compilation halted");
                return;
            }

        }

        // Stop before semantic analysis phase, in case errors occurred.
        if (lexicalSyntaxErrors) {
            System.err.println("Compilation halted");
            return;
        }

        // Populate global scope.
        SymbolTable.defineBasicClasses();

        // TODO Semantic analysis

        if (SymbolTable.hasSemanticErrors()) {
            System.err.println("Compilation halted");
            return;
        }
    }
}
