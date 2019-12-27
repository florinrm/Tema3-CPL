package cool.structures;

import cool.compiler.*;

public class FindTypesVisitor implements Visitor<Void> {
    private Scope currentScope = null;

    @Override
    public Void visit(Id id) {
        return null;
    }

    @Override
    public Void visit(Int intt) {
        return null;
    }

    @Override
    public Void visit(If iff) {
        return null;
    }

    @Override
    public Void visit(AddSub sum) {
        return null;
    }

    @Override
    public Void visit(Program prog) {
        currentScope = new DefaultScope(null);
        currentScope.add(TypeSymbol.BOOL);
        currentScope.add(TypeSymbol.INT);
        currentScope.add(TypeSymbol.STRING);

        for (var statement : prog.getClasses()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(MultDivNode prod) {
        return null;
    }

    @Override
    public Void visit(BoolNode bool) {
        return null;
    }

    @Override
    public Void visit(FunctionCall call) {
        return null;
    }

    @Override
    public Void visit(CompareNode comp) {
        return null;
    }

    @Override
    public Void visit(FloatNode flt) {
        return null;
    }

    @Override
    public Void visit(FuncDefNode func) {
        return null;
    }

    @Override
    public Void visit(UnaryMinusNode minus) {
        return null;
    }

    @Override
    public Void visit(VarDef var) {
        return null;
    }

    @Override
    public Void visit(AssignmentNode assignmentNode) {
        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        var id = classNode.getName();
        var idType = new TypeSymbol(id.getText());

        if (idType.equals(TypeSymbol.BOOL)
                || idType.equals(TypeSymbol.INT)
                || idType.equals(TypeSymbol.STRING)) {
            return null;
        }

        if (idType.equals(TypeSymbol.SELF)) {
            return null;
        }

        ClassSymbol classSymbol = new ClassSymbol(currentScope, classNode.getName().getText());
        currentScope = classSymbol;

        Id classId = new Id(id);
        classId.setScope(currentScope);
        classId.setSymbol(classSymbol);

        if (classNode.getParent() != null) {
            SymbolTable.classesAndParents.put(classNode.getName().getText(), classNode.getParent().getText());
        } else {
            SymbolTable.classesAndParents.put(classNode.getName().getText(), null);
        }

        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(StringNode stringNode) {
        return null;
    }

    @Override
    public Void visit(WhileNode whileNode) {
        return null;
    }

    @Override
    public Void visit(VoidNode voidNode) {
        return null;
    }

    @Override
    public Void visit(NewNode newNode) {
        return null;
    }

    @Override
    public Void visit(LetNode letNode) {
        return null;
    }

    @Override
    public Void visit(ListVariables listVariables) {
        return null;
    }

    @Override
    public Void visit(CaseOfNode caseOfNode) {
        return null;
    }

    @Override
    public Void visit(Branch branch) {
        return null;
    }

    @Override
    public Void visit(BlockNode blockNode) {
        return null;
    }

    @Override
    public Void visit(ParanthesesNode paranthesesNode) {
        return null;
    }

    @Override
    public Void visit(NegationNode negationNode) {
        return null;
    }
}
