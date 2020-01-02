package cool.structures;

import cool.compiler.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.util.ArrayList;
import java.util.List;

public class CodeGenVisitor implements Visitor<ST> {
    private static STGroupFile templates = new STGroupFile("cool/structures/CodeTemplate.stg");

    static int relationalLabelIndex = 0;
    static int ifLabelIndex = 0;

    private ST mainSection;    // filled directly (through visitor returns)
    private ST dataSection; // filled collaterally ("global" access)
    private ST funcSection; // filled collaterally ("global" access)

    @Override
    public ST visit(Id id) {
        return null;
    }

    @Override
    public ST visit(Int intt) {
        return null;
    }

    @Override
    public ST visit(If iff) {
        return null;
    }

    @Override
    public ST visit(AddSub sum) {
        return null;
    }

    private ST visitIntegerConstant(int value, String label) {
        return null;
    }

    @Override
    public ST visit(Program prog) {
        dataSection = templates.getInstanceOf("sequence");
        funcSection = templates.getInstanceOf("sequenceSpaced");
        mainSection = templates.getInstanceOf("sequence");

        // for int, bool, string - indexes
        ST primaryIndexes = templates.getInstanceOf("header");
        primaryIndexes.add("val1", Constants.classesAndIndexes.get("Int"));
        primaryIndexes.add("val2", Constants.classesAndIndexes.get("String"));
        primaryIndexes.add("val3", Constants.classesAndIndexes.get("Bool"));
        dataSection.add("e", primaryIndexes);

        // add constant values
        // string
        for (var entry : Constants.stringValues.entrySet()) {
            var label = entry.getValue();
            var value = entry.getKey();
            var section = templates.getInstanceOf("stringConstant");
            section.add("val", "\"" + value + "\"");
            section.add("label", label);
            var length = Constants.intValues.get(value.length());
            section.add("len", length);
            int size = 4 + (value.length() + 1) / 4 + ((value.length() + 1) % 4 == 0 ? 0 : 1);
            section.add("size", size);
            section.add("index", Constants.classesAndIndexes.get("String"));
            dataSection.add("e", section);
        }

        // int
        for (var entry : Constants.intValues.entrySet()) {
            var label = entry.getValue();
            var value = entry.getKey();
            var section = templates.getInstanceOf("intConstant");
            section.add("val", value);
            section.add("label", label);
            section.add("index", Constants.classesAndIndexes.get("Int"));
            dataSection.add("e", section);
        }

        Constants.addBool();
        // bool
        for (var entry : Constants.boolValues.entrySet()) {
            var label = entry.getValue();
            var value = entry.getKey();
            var section = templates.getInstanceOf("boolConstant");
            section.add("val", value);
            section.add("label", label);
            section.add("index", Constants.classesAndIndexes.get("Bool"));
            dataSection.add("e", section);
        }

        // table of class indexes
        List<String> indexLabel = new ArrayList<>();
        for (var entry : Constants.classesAndIndexes.entrySet()) {
            if (SymbolTable.classesAndMethods.containsKey(entry.getKey())) {
                indexLabel.add(Constants.stringValues.get(entry.getKey()));
            }
        }
        ST classIndexes = templates.getInstanceOf("classesTable");
        classIndexes.add("args", indexLabel);
        dataSection.add("e", classIndexes);

        // routines and class inits
        ST classInits = templates.getInstanceOf("initClasses");
        List<String> initLabel = new ArrayList<>();
        for (var entry : Constants.classesAndIndexes.entrySet()) {
            initLabel.add(entry.getKey() + "_protObj");
            initLabel.add(entry.getKey() + "_init");
        }
        classInits.add("args", initLabel);
        dataSection.add("e", classInits);

        // TODO: _protOb and _dispTab for each class

        // init predefined classes
        mainSection.add("e", visitPredefinedClass(TypeSymbol.OBJECT.getName()));
        mainSection.add("e", visitPredefinedClass("IO"));
        mainSection.add("e", visitPredefinedClass(TypeSymbol.INT.getName()));
        mainSection.add("e", visitPredefinedClass(TypeSymbol.STRING.getName()));
        mainSection.add("e", visitPredefinedClass(TypeSymbol.BOOL.getName()));

        for (var statement : prog.getClasses())
            mainSection.add("e", statement.accept(this));

        //assembly-ing it all together. HA! get it?
        var programST = templates.getInstanceOf("program");
        programST.add("data", dataSection);
        programST.add("textFuncs", funcSection);
        programST.add("textMain", mainSection);

        return programST;
    }

    @Override
    public ST visit(MultDivNode prod) {
        return null;
    }

    @Override
    public ST visit(BoolNode bool) {
        return null;
    }

    @Override
    public ST visit(FunctionCall call) {
        return null;
    }

    @Override
    public ST visit(CompareNode comp) {
        return null;
    }

    @Override
    public ST visit(FloatNode flt) {
        return null;
    }

    @Override
    public ST visit(FuncDefNode func) {
        return null;
    }

    @Override
    public ST visit(UnaryMinusNode minus) {
        return null;
    }

    @Override
    public ST visit(VarDef var) {
        return null;
    }

    @Override
    public ST visit(AssignmentNode assignmentNode) {
        return null;
    }

    private ST visitPredefinedClass(String className) {
        var st = templates.getInstanceOf("class");
        st.add("class_label", className + "_init");

        if (!className.equals(TypeSymbol.OBJECT.getName())) {
            st.add("super_class", "Object_init");
        }

        return st;
    }

    @Override
    public ST visit(ClassNode classNode) {
        var st = templates.getInstanceOf("class");
        String className = classNode.getName().getText();
        st.add("class_label", classNode.getName().getText() + "_init");

        if (classNode.getParent() == null) {
            st.add("super_class", "Object_init");
        } else {
            st.add("super_class", classNode.getParent().getText() + "_init");
        }

        return st;
    }

    @Override
    public ST visit(StringNode stringNode) {
        return null;
    }

    @Override
    public ST visit(WhileNode whileNode) {
        return null;
    }

    @Override
    public ST visit(VoidNode voidNode) {
        return null;
    }

    @Override
    public ST visit(NewNode newNode) {
        return null;
    }

    @Override
    public ST visit(LetNode letNode) {
        return null;
    }

    @Override
    public ST visit(ListVariables listVariables) {
        return null;
    }

    @Override
    public ST visit(CaseOfNode caseOfNode) {
        return null;
    }

    @Override
    public ST visit(Branch branch) {
        return null;
    }

    @Override
    public ST visit(BlockNode blockNode) {
        return null;
    }

    @Override
    public ST visit(ParanthesesNode paranthesesNode) {
        return null;
    }

    @Override
    public ST visit(NegationNode negationNode) {
        return null;
    }
}
