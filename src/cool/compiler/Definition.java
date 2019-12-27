package cool.compiler;

import org.antlr.v4.runtime.Token;

public abstract class Definition extends ASTNode {
    Token token;

    Definition(Token token) {
        this.token = token;
    }
}
