package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends IdSymbol implements Scope {
    private Map<String, Symbol> symbols = new LinkedHashMap<>();
    private Map<String, Symbol> params = new LinkedHashMap<>();
    private Scope parent;

    public FunctionSymbol(Scope parent, String name) {
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

    public boolean addParam(Symbol sym) {
        if (params.containsKey(sym.getName()))
            return false;

        params.put(sym.getName(), sym);

        return true;
    }

    public Symbol lookupParam(String s) {

        return params.get(s);
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

    @Override
    public Scope getParent() {
        return parent;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public Map<String, Symbol> getParams() {
        return params;
    }
}
