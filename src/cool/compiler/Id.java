package cool.compiler;

import cool.structures.IdSymbol;
import cool.structures.Scope;
import org.antlr.v4.runtime.Token;

public class Id extends Expression {
    private Scope scope;
    private IdSymbol symbol;

    public Id(Token token) {
        super(token);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Id{" +
                "token=" + token.getText() +
                '}';
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public IdSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(IdSymbol symbol) {
        this.symbol = symbol;
    }
}
