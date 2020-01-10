package cool.compiler;

import cool.lexer.CoolLexer;
import cool.parser.CoolParser;
import cool.structures.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;


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
            printTree.setPrint(false);

            // populates classes and parents
            // SymbolTable.classesAndParents.put("Object", null);
            SymbolTable.classesAndParents.put("IO", "Object");
            SymbolTable.classesAndParents.put("Int", "Object");
            SymbolTable.classesAndParents.put("String", "Object");
            SymbolTable.classesAndParents.put("Bool", "Object");

            // indexes
            Constants.classesAndIndexes.put("Object", 0);

            var findTypesVisitor = new FindTypesVisitor();
            ast.accept(findTypesVisitor);

            Constants.addString("");
            Constants.addString("Object");
            // adding class names
            for (var entry : SymbolTable.classesAndParents.entrySet()) {
                Constants.addString(entry.getKey());
            }

            // adding strings
            printTree.setAddStrings(true);
            printTree.setAddIntegers(false);
            ast.accept(printTree);

            // adding strings lengths
            for (var entry : Constants.stringValues.entrySet()) {
                Constants.addInteger(entry.getKey().length());
            }

            // adding integers
            printTree.setAddStrings(false);
            printTree.setAddIntegers(true);
            printTree.setPrint(false);
            ast.accept(printTree);

            var definitionPassVisitor = new DefinitionPassVisitor();
            var resolutionPassVisitor = new ResolutionPassVisitor();

            ast.accept(definitionPassVisitor);

            // add methods for predefined classes
            // Object
            SymbolTable.classesAndMethods.put("Object", new ArrayList<FuncDefNode>(List.of(
                    new FuncDefNode(new FuncDefToken("abort"), new FuncDefToken("Object"), null, null),
                    new FuncDefNode(new FuncDefToken("type_name"), new FuncDefToken("String"), null, null),
                    new FuncDefNode(new FuncDefToken("copy"), new FuncDefToken("SELF_TYPE"), null, null)
            )));

            // IO
            SymbolTable.classesAndMethods.put("IO", new ArrayList<FuncDefNode>(List.of(
                    new FuncDefNode(new FuncDefToken("out_string"), new FuncDefToken("SELF_TYPE"), null, null),
                    new FuncDefNode(new FuncDefToken("out_int"), new FuncDefToken("SELF_TYPE"), null, null),
                    new FuncDefNode(new FuncDefToken("in_string"), new FuncDefToken("String"), null, null),
                    new FuncDefNode(new FuncDefToken("in_int"), new FuncDefToken("Int"), null, null)
            )));

            // Int
            SymbolTable.classesAndMethods.put("Int", new ArrayList<FuncDefNode>());

            // String
            SymbolTable.classesAndMethods.put("String", new ArrayList<FuncDefNode>(List.of(
                    new FuncDefNode(new FuncDefToken("length"), new FuncDefToken("Int"), null, null),
                    new FuncDefNode(new FuncDefToken("concat"), new FuncDefToken("String"), null, null),
                    new FuncDefNode(new FuncDefToken("substr"), new FuncDefToken("String"), null, null)
            )));

            // Bool
            SymbolTable.classesAndMethods.put("Bool", new ArrayList<FuncDefNode>());

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
                } else {
                    if (!className.equals(TypeSymbol.OBJECT.getName())) {
                        SymbolTable.classesAndParents.put(className, TypeSymbol.OBJECT.getName());
                    }
                }
            }

            // delete duplicated methods from classes (overriden methods)
            for (var entry : SymbolTable.classesAndMethods.entrySet()) {
                var methods = entry.getValue();
                ArrayList<FuncDefNode> temp = new ArrayList<>();
                for (var method : methods) {
                    int count = SymbolTable.countsMethodInClass(temp, method.getNameToken().getText());
                    if (count < 1) {
                        temp.add(method);
                    }
                }
                SymbolTable.classesAndMethods.put(entry.getKey(), temp);
            }

            // add inherited attributes from super class
            for (var entry : SymbolTable.classesAndParents.entrySet()) {
                var className = entry.getKey();
                var parent = entry.getValue();
                if (!className.equals("Int") && !className.equals("String") && !className.equals("Bool")
                        && !className.equals("IO") && !parent.equals("Object")) {
                    if (!parent.equals("Object") && !parent.equals("IO")) {
                        var inheritedAttributes = SymbolTable.classesAndAttributes.get(parent);
                        if (inheritedAttributes != null) {
                            var variables = SymbolTable.classesAndAttributes.get(className);
                            if (variables == null) {
                                variables = new LinkedHashSet<>();
                            }
                            variables.addAll(inheritedAttributes);
                            SymbolTable.classesAndAttributes.put(className, variables);
                        }
                    } else {
                        if (!className.equals(TypeSymbol.OBJECT.getName())) {
                            SymbolTable.classesAndParents.put(className, TypeSymbol.OBJECT.getName());
                        }
                    }
                }
            }


            int index = 1;
            // System.out.println("CE PPLM " + SymbolTable.classesAndParents);
            for (var entry : SymbolTable.classesAndParents.entrySet()) {
                if (!entry.getKey().equals("Int") && !entry.getKey().equals("String")
                        && !entry.getKey().equals("Bool")) {
                    Constants.classesAndIndexes.put(entry.getKey(), index++);
                    var inheritedMethods = SymbolTable.findInheritedMethods(entry.getKey());
                    var inheritedAttributes = SymbolTable.findInheritedAttributes(entry.getKey());
                    var methods = SymbolTable.classesAndMethods.get(entry.getKey());
                    var attrs = SymbolTable.classesAndAttributes.get(entry.getKey());

                    if (inheritedMethods != null) {
                        methods.addAll(inheritedMethods);
                        SymbolTable.classesAndMethods.put(entry.getKey(), methods);
                    }

                    if (inheritedAttributes != null) {
                        attrs.addAll(inheritedAttributes);
                        SymbolTable.classesAndAttributes.put(entry.getKey(), attrs);
                    }
                }
            }

            Constants.classesAndIndexes.put(TypeSymbol.INT.getName(), index++);
            Constants.classesAndIndexes.put(TypeSymbol.STRING.getName(), index++);
            Constants.classesAndIndexes.put(TypeSymbol.BOOL.getName(), index++);

            // System.out.println(Constants.classesAndIndexes);
            // System.out.println(Constants.classesAndIndexes);

            /*
            for (var entry : SymbolTable.classesAndMethods.entrySet()) {
                System.out.println(entry.getKey() + " " + SymbolTable.classesAndMethods.get(entry.getKey()));
            }

             */

            /*
            for (var entry : SymbolTable.classesAndAttributes.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }*/


            ast.accept(resolutionPassVisitor);

            var codeGenVisitor = new CodeGenVisitor();
            var codeGen = ast.accept(codeGenVisitor);
            System.out.println(codeGen.render());

            //System.out.println(fileName);

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.replace(".cl", ".s")));
            writer.write(codeGen.render());
            writer.close();

            SymbolTable.classesAndMethods.clear();
            SymbolTable.classesAndVariables.clear();
            SymbolTable.classesAndParents.clear();

            /*
            System.out.println(Constants.stringValues);
            System.out.println(Constants.intValues);
             */

            Constants.stringValues.clear();
            Constants.intValues.clear();

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
