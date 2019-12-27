package cool.compiler;

import java.util.ArrayList;

public class Program extends ASTNode {
    private ArrayList<ClassNode> classes = new ArrayList<>();

    public Program() {
        super();
    }

    public void addClass(ClassNode classNode) {
        classes.add(classNode);
    }

    public ArrayList<ClassNode> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassNode> classes) {
        this.classes = classes;
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
