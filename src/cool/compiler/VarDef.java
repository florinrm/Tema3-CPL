package cool.compiler;

import org.antlr.v4.runtime.Token;

public class VarDef extends Definition {
    private Token nameToken;
    private Token typeToken;
    private Expression initExpr;
    private String type = VarDefType.ATTRIBUTE;

    public VarDef(Token name, Token type, Expression init, Token start) {
        this(name, type, start);
        initExpr = init;
    }

    public VarDef(Token name, Token type, Token start) {
        super(start);
        nameToken = name;
        typeToken = type;
    }

    public Token getNameToken() {
        return nameToken;
    }

    public void setNameToken(Token nameToken) {
        this.nameToken = nameToken;
    }

    public Token getTypeToken() {
        return typeToken;
    }

    public void setTypeToken(Token typeToken) {
        this.typeToken = typeToken;
    }

    public Expression getInitExpr() {
        return initExpr;
    }

    public void setInitExpr(Expression initExpr) {
        this.initExpr = initExpr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}