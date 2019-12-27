package cool.compiler;

import org.antlr.v4.runtime.Token;

public class CompareNode extends Expression {
    private Expression left;
    private Expression right;
    private Token op;

    public CompareNode(Expression left, Token op, Expression right, Token start) {
        super(start);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public Expression getRight() {
        return right;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public Token getOp() {
        return op;
    }

    public void setOp(Token op) {
        this.op = op;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
