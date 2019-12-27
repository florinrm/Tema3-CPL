package cool.compiler;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class BlockNode extends Expression {
    private ArrayList<Expression> expressions;

    public BlockNode(Token token) {
        super(token);
        this.expressions = new ArrayList<>();
    }

    public void addExpression(Expression expression) {
        expressions.add(expression);
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
