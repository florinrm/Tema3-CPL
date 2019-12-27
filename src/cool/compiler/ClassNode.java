package cool.compiler;

import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class ClassNode extends Expression {
    private Token name;
    private Token parent;
    private ArrayList<Definition> definitions;

    public ClassNode(Token name, Token token) {
        super(token);
        this.name = name;
        definitions = new ArrayList<>();
    }

    public ClassNode(Token name, Token parent, Token token) {
        this(name, token);
        this.parent = parent;
    }

    public void addDefinition(Definition def) {
        definitions.add(def);
    }

    public Token getParent() {
        return parent;
    }

    public void setParent(Token parent) {
        this.parent = parent;
    }

    public Token getName() {
        return name;
    }

    public void setName(Token name) {
        this.name = name;
    }

    public ArrayList<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<Definition> definitions) {
        this.definitions = definitions;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
