package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSymbol extends IdSymbol implements Scope {
    private Map<String, Symbol> symbols = new LinkedHashMap<>();
    private Map<String, Symbol> functions = new LinkedHashMap<>();
    private Scope parent;

    public ClassSymbol(Scope parent, String name) {
        super(name);
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        if (sym instanceof FunctionSymbol) {
            if (functions.containsKey(sym.getName())) {
                return false;
            }
            functions.put(sym.getName(), sym);
            return true;
        } else {
            if (symbols.containsKey(sym.getName())) {
                return false;
            }
            symbols.put(sym.getName(), sym);
            return true;
        }
    }

    @Override
    public Symbol lookup(String s) {
        var sym = symbols.get(s);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.lookup(s);

        sym = functions.get(s);

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

    public Map<String, Symbol> getFormals() {
        return symbols;
    }
}
