package cool.compiler;

import org.antlr.v4.runtime.Token;

import java.util.Objects;

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

    public VarDef(VarDef var) {
        this(var.nameToken, var.typeToken, var.initExpr, var.token);
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

    @Override
    public String toString() {
        if (initExpr != null) {
            return "VarDef{" +
                    "nameToken=" + nameToken +
                    ", typeToken=" + typeToken +
                    ", initExpr=" + initExpr +
                    ", type='" + type + '\'' +
                    '}';
        }
        return "VarDef{" +
                "nameToken=" + nameToken +
                ", typeToken=" + typeToken +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VarDef)) return false;
        VarDef varDef = (VarDef) o;
        return Objects.equals(nameToken, varDef.nameToken) &&
                Objects.equals(typeToken, varDef.typeToken) &&
                Objects.equals(initExpr, varDef.initExpr) &&
                Objects.equals(type, varDef.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameToken, typeToken, initExpr, type);
    }
}