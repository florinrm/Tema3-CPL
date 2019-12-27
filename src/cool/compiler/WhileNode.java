package cool.compiler;

import org.antlr.v4.runtime.Token;

public class WhileNode extends Expression {
    private Expression condition;
    private Expression body;

    public WhileNode(Expression condition, Expression body, Token start) {
        super(start);
        this.condition = condition;
        this.body = body;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public Expression getBody() {
        return body;
    }

    public void setBody(Expression body) {
        this.body = body;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
