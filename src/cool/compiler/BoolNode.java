package cool.compiler;

import org.antlr.v4.runtime.Token;

public class BoolNode extends Expression {
    private boolean value;

    public BoolNode(boolean val, Token start) {
        super(start);
        value = val;
    }

    public BoolNode(Token start) {
        super(start);
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
