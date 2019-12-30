package cool.structures;

import cool.compiler.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

public class CodeGenVisitor implements Visitor<ST> {
    static STGroupFile templates = new STGroupFile("cool/structures/CodeTemplate.stg");

    static int relationalLabelIndex = 0;
    static int ifLabelIndex = 0;

    ST mainSection;    // filled directly (through visitor returns)
    ST dataSection; // filled collaterally ("global" access)
    ST funcSection; // filled collaterally ("global" access)

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

    @Override
    public ST visit(Program prog) {
        var programST = templates.getInstanceOf("program");
        for (var statement : prog.getClasses()) {
            statement.accept(this);
        }
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

    @Override
    public ST visit(ClassNode classNode) {
        var st = templates.getInstanceOf("class");
        String className = classNode.getName().getText();

        if (classNode.getParent() == null) {
            st.add("super_class", "Object_init");
        } else {
            st.add("super_class", classNode.getParent().getText() + "_init");
        }

        return null;
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
