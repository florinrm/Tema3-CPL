package cool.compiler;

import org.antlr.v4.runtime.Token;

public class Int extends Expression {

    public Int(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
