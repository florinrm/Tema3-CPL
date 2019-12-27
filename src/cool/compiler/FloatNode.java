package cool.compiler;

import org.antlr.v4.runtime.Token;

public class FloatNode extends Expression {
    private float value;

    public FloatNode(float value, Token start) {
        super(start);
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
