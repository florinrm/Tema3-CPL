package cool.compiler;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class CaseOfNode extends Expression {
    private Expression expression;
    private ArrayList<Branch> branches;

    protected CaseOfNode(Token token) {
        super(token);
    }

    public CaseOfNode(Expression expression, Token token) {
        super(token);
        this.expression = expression;
        this.branches = new ArrayList<>();
    }

    public void addBranch(Branch branch) {
        branches.add(branch);
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    public void setBranches(ArrayList<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
