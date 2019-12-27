package cool.compiler;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class LetNode extends Expression {
    private ArrayList<ListVariables> variables = new ArrayList<>();
    private Expression body;

    public LetNode(Expression body, Token token) {
        super(token);
        this.body = body;
    }

    public ArrayList<ListVariables> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<ListVariables> variables) {
        this.variables = variables;
    }

    public Expression getBody() {
        return body;
    }

    public void setBody(Expression body) {
        this.body = body;
    }

    public void addVariable(ListVariables variable) {
        variables.add(variable);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
