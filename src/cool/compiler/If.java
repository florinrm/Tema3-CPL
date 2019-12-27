package cool.compiler;

import org.antlr.v4.runtime.Token;

public class If extends Expression {
    // Sunt necesare trei câmpuri pentru cele trei componente ale expresiei.
    private Expression cond;
    private Expression thenBranch;
    private Expression elseBranch;

    If(Expression cond,
       Expression thenBranch,
       Expression elseBranch,
       Token start) {
        super(start);
        this.cond = cond;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    public Expression getCond() {
        return cond;
    }

    public void setCond(Expression cond) {
        this.cond = cond;
    }

    public Expression getThenBranch() {
        return thenBranch;
    }

    public void setThenBranch(Expression thenBranch) {
        this.thenBranch = thenBranch;
    }

    public Expression getElseBranch() {
        return elseBranch;
    }

    public void setElseBranch(Expression elseBranch) {
        this.elseBranch = elseBranch;
    }

    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
