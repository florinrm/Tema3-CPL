package cool.compiler;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.Objects;

public class FuncDefNode extends Definition {
    private Token nameToken;
    private Token retTypeToken;
    private Expression body;
    private ArrayList<VarDef> params;

    public FuncDefNode(Token nameToken, Token retTypeToken, Expression body, Token start) {
        super(start);
        this.nameToken = nameToken;
        this.retTypeToken = retTypeToken;
        this.body = body;
        this.params = new ArrayList<>();
    }

    public Token getNameToken() {
        return nameToken;
    }

    public void setNameToken(Token nameToken) {
        this.nameToken = nameToken;
    }

    public Token getRetTypeToken() {
        return retTypeToken;
    }

    public void setRetTypeToken(Token retTypeToken) {
        this.retTypeToken = retTypeToken;
    }

    public Expression getBody() {
        return body;
    }

    public void setBody(Expression body) {
        this.body = body;
    }

    public void addParam(VarDef param) {
        params.add(param);
    }

    public ArrayList<VarDef> getParams() {
        return params;
    }

    public void setParams(ArrayList<VarDef> params) {
        this.params = params;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        if (body != null) {
            return "FuncDefNode{" +
                    "nameToken=" + nameToken.getText() +
                    ", retTypeToken=" + retTypeToken.getText() +
                    ", body=" + body.getToken().getText() +
                    ", params=" + params +
                    '}';
        } else {
            return "FuncDefNode{" +
                    "nameToken=" + nameToken.getText() +
                    ", retTypeToken=" + retTypeToken.getText() +
                    ", params=" + params +
                    '}';
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncDefNode)) return false;
        FuncDefNode that = (FuncDefNode) o;
        return Objects.equals(nameToken, that.nameToken) &&
                Objects.equals(retTypeToken, that.retTypeToken) &&
                Objects.equals(body, that.body) &&
                Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameToken, retTypeToken, body, params);
    }
}
