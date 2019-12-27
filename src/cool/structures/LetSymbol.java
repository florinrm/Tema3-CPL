package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class LetSymbol extends IdSymbol implements Scope {
    private Map<String, Symbol> symbols = new LinkedHashMap<>();
    private Map<String, Symbol> variables = new LinkedHashMap<>();
    private Scope parent;

    public LetSymbol(Scope parent, String name) {
        super(name);
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        if (symbols.containsKey(sym.getName()))
            return false;

        symbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol lookup(String s) {
        var sym = symbols.get(s);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.lookup(s);

        return null;
    }

    public boolean addVar(Symbol sym) {
        if (variables.containsKey(sym.getName()))
            return false;

        variables.put(sym.getName(), sym);

        return true;
    }

    public Symbol lookupVar(String s) {

        return variables.get(s);
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public Map<String, Symbol> getVariables() {
        return variables;
    }
}
