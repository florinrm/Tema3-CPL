package cool.compiler;

import org.antlr.v4.runtime.Token;

public class ParanthesesNode extends Expression {
    private Expression expression;

    public ParanthesesNode(Token token, Expression expression) {
        super(token);
        this.expression = expression;
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
