package cool.compiler;

import org.antlr.v4.runtime.Token;

public class ListVariables extends Expression {
    private Token name;
    private Token type;
    private Expression expression;


    public ListVariables(Token name, Token type, Token token) {
        super(token);
        this.name = name;
        this.type = type;
    }

    public ListVariables(Token token, Token name, Token type, Expression expression) {
        this(name, type, token);
        this.expression = expression;
    }

    public Token getName() {
        return name;
    }

    public void setName(Token name) {
        this.name = name;
    }

    public Token getType() {
        return type;
    }

    public void setType(Token type) {
        this.type = type;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
