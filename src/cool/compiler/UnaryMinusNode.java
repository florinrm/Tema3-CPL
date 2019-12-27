package cool.compiler;

import org.antlr.v4.runtime.Token;

public class UnaryMinusNode extends Expression {
    private Expression nested;

    public UnaryMinusNode(Expression nested, Token start) {
        super(start);
        this.nested = nested;
    }

    public Expression getNested() {
        return nested;
    }

    public void setNested(Expression nested) {
        this.nested = nested;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}