package cool.compiler;

public class NodeVisitor implements Visitor<Void> {
    private int indent = 0;
    private boolean print = true;
    private boolean addStrings = false;
    private boolean addIntegers = false;

    public void setPrint(boolean print) {
        this.print = print;
    }

    public void setAddStrings(boolean addStrings) {
        this.addStrings = addStrings;
    }

    public void setAddIntegers(boolean addIntegers) {
        this.addIntegers = addIntegers;
    }

    private void printIndent(String str) {
        if (print) {
            for (int i = 0; i < indent; i++)
                System.out.print("  ");
            System.out.println(str);
        }
    }

    @Override
    public Void visit(Id id) {
        printIndent(id.token.getText());
        return null;
    }

    @Override
    public Void visit(Int intt) {
        printIndent(intt.getToken().getText());
        if (addIntegers) {
            Constants.addInteger(Integer.parseInt(intt.token.getText()));
        }
        return null;
    }

    @Override
    public Void visit(If iff) {
        printIndent("if");
        indent++;
        iff.getCond().accept(this);
        iff.getThenBranch().accept(this);
        iff.getElseBranch().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(AddSub sum) {
        printIndent(sum.getOp().getText());
        indent++;
        sum.getLeft().accept(this);
        sum.getRight().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(Program prog) {
        printIndent("program");
        indent++;
        for (var def : prog.getClasses()) {
            def.accept(this);
        }
        indent--;

        return null;
    }

    @Override
    public Void visit(MultDivNode prod) {
        printIndent(prod.getOp().getText());
        indent++;
        prod.getLeft().accept(this);
        prod.getRight().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(BoolNode bool) {
        printIndent(bool.token.getText());
        return null;
    }

    @Override
    public Void visit(FunctionCall call) {
        if (call.getExpression() == null) {
            printIndent("implicit dispatch");
        } else {
            printIndent(".");
            indent++;
            call.getExpression().accept(this);
            indent--;
        }

        indent++;
        if (call.getUpcast() != null) {
            printIndent(call.getUpcast().getText());
        }
        call.getName().accept(this);

        for (var param : call.getParams()) {
            param.accept(this);
        }
        indent--;

        return null;
    }

    @Override
    public Void visit(CompareNode comp) {
        printIndent(comp.getOp().getText());
        indent++;
        comp.getLeft().accept(this);
        comp.getRight().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(FloatNode flt) {
        return null;
    }

    @Override
    public Void visit(FuncDefNode func) {
        printIndent("method");
        indent++;
        printIndent(func.getNameToken().getText());
        for (var def : func.getParams()) {
            def.accept(this);
        }

        printIndent(func.getRetTypeToken().getText());
        func.getBody().accept(this);

        indent--;
        return null;
    }

    @Override
    public Void visit(UnaryMinusNode minus) {
        printIndent("~");
        indent++;
        minus.getNested().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(NegationNode negationNode) {
        printIndent("not");
        indent++;
        negationNode.getNested().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(VarDef var) {
        if (var.getType() != null && var.getType().equals(VarDefType.FORMAL)) {
            printIndent(var.getType());
        } else {
            printIndent(VarDefType.ATTRIBUTE);
        }

        indent++;
        printIndent(var.getNameToken().getText());
        if (var.getTypeToken() != null) {
            printIndent(var.getTypeToken().getText());
        }
        if (var.getInitExpr() != null) {
            var.getInitExpr().accept(this);
        }
        indent--;

        return null;
    }

    @Override
    public Void visit(AssignmentNode assignmentNode) {
        printIndent("<-");
        indent++;
        printIndent(assignmentNode.getVarName().getText());
        assignmentNode.getValue().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        printIndent("class");
        indent++;
        printIndent(classNode.getName().getText());
        if (classNode.getParent() != null) {
            printIndent(classNode.getParent().getText());
        }
        for (var def : classNode.getDefinitions()) {
            def.accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(StringNode stringNode) {
        if (stringNode.token.getText().equals("\"\"")) {
            //System.out.println("fac sex anal");
            printIndent("");
            return null;
        }
        String toPrint = stringNode.token.getText()
                .replace("\"", "")
                .replace("\\n", "\n")
                .replace("\\t", "\t");
        if (toPrint.contains("\\\\")) {
            toPrint = toPrint.replace("\\\\", "\\");
        } else if (toPrint.contains("\\")) {
            toPrint = toPrint.replace("\\", "");
        }
        printIndent(toPrint);

        if (addStrings) {
            Constants.addString(toPrint);
        }

        return null;
    }

    @Override
    public Void visit(WhileNode whileNode) {
        printIndent("while");
        indent++;
        whileNode.getCondition().accept(this);
        whileNode.getBody().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(VoidNode voidNode) {
        printIndent("isvoid");
        indent++;
        voidNode.getExpression().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(NewNode newNode) {
        printIndent("new");
        indent++;
        printIndent(newNode.getType().getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(LetNode letNode) {
        printIndent("let");
        indent++;
        for (var variable : letNode.getVariables()) {
            variable.accept(this);
        }
        letNode.getBody().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(ListVariables listVariables) {
        printIndent("local");
        indent++;
        printIndent(listVariables.getName().getText());
        printIndent(listVariables.getType().getText());
        if (listVariables.getExpression() != null) {
            listVariables.getExpression().accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(CaseOfNode caseOfNode) {
        printIndent("case");
        indent++;
        caseOfNode.getExpression().accept(this);
        for (var branch : caseOfNode.getBranches()) {
            branch.accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(Branch branch) {
        printIndent("case branch");
        indent++;
        printIndent(branch.getName().getText());
        printIndent(branch.getType().getText());
        branch.getExpression().accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(BlockNode blockNode) {
        printIndent("block");
        indent++;
        for (var expression : blockNode.getExpressions()) {
            expression.accept(this);
        }
        indent--;
        return null;
    }

    @Override
    public Void visit(ParanthesesNode paranthesesNode) {
        paranthesesNode.getExpression().accept(this);
        return null;
    }
}
