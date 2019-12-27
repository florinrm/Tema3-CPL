package cool.compiler;

import org.antlr.v4.runtime.Token;

public class NewNode extends Expression {
    private Token type;

    public NewNode(Token token, Token type) {
        super(token);
        this.type = type;
    }

    public Token getType() {
        return type;
    }

    public void setType(Token type) {
        this.type = type;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
