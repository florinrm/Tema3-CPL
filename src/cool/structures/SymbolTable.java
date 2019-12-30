package cool.structures;

import cool.compiler.Compiler;
import cool.compiler.FuncDefNode;
import cool.parser.CoolParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
    public static Scope globals;

    public static Map<String, String> classesAndParents = new LinkedHashMap<>();
    public static Map<String, ArrayList<Pair<String, String>>> classesAndVariables = new LinkedHashMap<>();
    public static Map<String, ArrayList<FuncDefNode>> classesAndMethods = new LinkedHashMap<>();

    public final static String ID = "Id";
    public final static String CLASS = "Class";
    public final static String FUNCTION = "Function";
    public final static String LET = "Let";
    public final static String CASE = "Case";
    
    private static boolean semanticErrors;

    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;
        
        // TODO Populate global scope.
    }
    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }

    public static void error(String line, String str) {

        String message = "\"" + Compiler.currentFile.split("\\\\")[2]
                + "\", line " + line + ", Semantic error:" + str;

        //String message = "line " + line + ", Semantic error:" + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }

    public static boolean checkExistingClass(String classNode) {
        for (Map.Entry<String, String> entry : classesAndParents.entrySet()) {
            if (classNode.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkForInheritanceCycle(String baseClass, String classNode) {
        for (Map.Entry<String, String> entry : classesAndParents.entrySet()) {
            if (entry.getKey().equals(classNode)) {
                if (entry.getValue() == null) {
                    return false;
                }
                if (entry.getValue().equals(baseClass)) {
                    return true;
                }
                return checkForInheritanceCycle(baseClass, entry.getValue());
            }
        }

        return false;
    }

    public static boolean checkForInheritanceCycle(String baseClass) {
        return checkForInheritanceCycle(baseClass, baseClass);
    }

    public static boolean checkIfTypeExists(String type) {
        boolean ok1 = false, ok2 = false;

        TypeSymbol typeSymbol = new TypeSymbol(type);
        if (typeSymbol.equals(TypeSymbol.SELF)
                || typeSymbol.equals(TypeSymbol.BOOL)
                || typeSymbol.equals(TypeSymbol.STRING)
                || typeSymbol.equals(TypeSymbol.INT)
                || typeSymbol.equals(TypeSymbol.OBJECT)) {
            ok1 = true;
        }

        for (Map.Entry<String, String> entry : classesAndParents.entrySet()) {
            if (type.equals(entry.getKey())) {
                ok2 = true;
            }
        }

        return ok1 | ok2;
    }

    public static boolean checkInheritance(String childClass, String parentClass) {
        for (Map.Entry<String, String> entry : classesAndParents.entrySet()) {
            if (childClass.equals(entry.getKey()) && parentClass.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    public static String findParentClass(String childClass) {
        for (Map.Entry<String, String> entry : classesAndParents.entrySet()) {
            if (childClass.equals(entry.getKey()) && entry.getValue() != null) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static boolean checkIfParentClass(String baseClass, String superClass) {
        for (Map.Entry<String, String> entry : classesAndParents.entrySet()) {
            if (entry.getKey().equals(baseClass)) {
                if (entry.getValue() == null) {
                    return false;
                }
                if (entry.getValue().equals(superClass)) {
                    return true;
                }
                return checkIfParentClass(entry.getValue(), superClass);
            }
        }
        return false;
    }

    public static String findMostParentClass(String baseClass) {
        for (Map.Entry<String, String> entry : classesAndParents.entrySet()) {
            if (entry.getKey().equals(baseClass)) {
                if (entry.getValue() == null) {
                    return baseClass;
                }
                return findMostParentClass(entry.getValue());
            }
        }
        return baseClass;
    }

}
