package cool.structures;

public class TypeSymbol extends Symbol {
    public static final TypeSymbol INT = new TypeSymbol("Int");
    public static final TypeSymbol STRING = new TypeSymbol("String");
    public static final TypeSymbol BOOL = new TypeSymbol("Bool");
    public static final TypeSymbol SELF = new TypeSymbol("SELF_TYPE");
    public static final TypeSymbol OBJECT = new TypeSymbol("Object");

    public TypeSymbol(String name) {
        super(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypeSymbol))
            return false;
        TypeSymbol symbol = (TypeSymbol) obj;

        return name.equals(symbol.name);
    }

    public static TypeSymbol getType(String type) {
        if (type.equals(INT.getName())) {
            return INT;
        } else if (type.equals(STRING.getName())) {
            return STRING;
        }
        if (type.equals(BOOL.getName())) {
            return BOOL;
        }
        if (type.equals(OBJECT.getName())) {
            return OBJECT;
        }

        return null;
    }

    public static boolean isPrimitive(TypeSymbol type) {
        return type.equals(INT) || type.equals(STRING) || type.equals(BOOL) || type.equals(OBJECT);
    }

    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
