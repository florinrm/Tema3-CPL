package cool.compiler;

import org.antlr.v4.runtime.Token;

public class StringNode extends Expression {

    public StringNode(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
