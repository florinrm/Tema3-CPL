package cool.compiler;

import org.antlr.v4.runtime.Token;

public class AssignmentNode extends Expression {
    private Token varName;
    private Expression value;

    public AssignmentNode(Token var, Expression val, Token start) {
        super(start);
        varName = var;
        value = val;
    }

    public Token getVarName() {
        return varName;
    }

    public void setVarName(Token varName) {
        this.varName = varName;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
