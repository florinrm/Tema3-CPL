package cool.compiler;

import org.antlr.v4.runtime.Token;

public abstract class Expression extends ASTNode {
    Token token;

    protected Expression(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
