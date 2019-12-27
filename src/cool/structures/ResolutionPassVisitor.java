package cool.structures;

import cool.compiler.*;

import java.util.ArrayList;
import java.util.List;

public class ResolutionPassVisitor implements Visitor<TypeSymbol> {
    private Scope currentScope = null;

    @Override
    public TypeSymbol visit(Id id) {
        return null;
    }

    @Override
    public TypeSymbol visit(Int intt) {
        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(If iff) {
        var condition = iff.getCond().accept(this);


        if (iff.getCond() instanceof Id) {
            // TODO fa frumos ca sa vezi daca cond este bool in function block sau in class
            String id = iff.getCond().getToken().getText();
            if (currentScope instanceof FunctionSymbol) {

                var symbol = currentScope.lookup(id);
                if (symbol instanceof IdSymbol) {
                    TypeSymbol typeSymbol = ((IdSymbol) symbol).getType();
                    if (!typeSymbol.equals(TypeSymbol.BOOL)) {
                        return TypeSymbol.OBJECT;
                    }
                }
            }
        }
        if (condition != null) {
            if (condition.equals(TypeSymbol.BOOL)
                    && iff.getCond().getToken().getText().equals("true")) {
                if (iff.getThenBranch() instanceof Id) {
                    if (iff.getThenBranch().getToken().getText().equals("self")) {
                        return TypeSymbol.OBJECT;
                    }

                    if (currentScope instanceof FunctionSymbol) {
                        String id = iff.getThenBranch().getToken().getText();
                        var symbol = currentScope.lookup(id);
                        if (symbol instanceof IdSymbol) {
                            return ((IdSymbol) symbol).getType();
                        }
                    }
                }
                return iff.getThenBranch().accept(this);
            } else {
                if (iff.getElseBranch() instanceof Id) {
                    if (iff.getElseBranch().getToken().getText().equals("self")) {
                        return TypeSymbol.OBJECT;
                    }

                    if (currentScope instanceof FunctionSymbol) {
                        String id = iff.getElseBranch().getToken().getText();
                        var symbol = currentScope.lookup(id);
                        if (symbol instanceof IdSymbol) {
                            return ((IdSymbol) symbol).getType();
                        }
                    }
                }
                return iff.getElseBranch().accept(this);
            }
        }
        return null;
    }

    @Override
    public TypeSymbol visit(AddSub sum) {
        var left = sum.getLeft().accept(this);
        var right = sum.getRight().accept(this);

        // System.out.println(sum.getRight().getToken().getText() + " " + sum.getLeft().getToken().getText());
        // System.out.println(((LetSymbol)currentScope).getVariables());

        if (right == null) {
            if (sum.getRight() instanceof Id) {
                // check for type
                String varName = sum.getRight().getToken().getText();
                if (currentScope instanceof ClassSymbol) {
                    String className = currentScope.toString();

                    var pairs = SymbolTable.classesAndVariables.get(className);
                    if (pairs != null) {
                        for (var pair : pairs) {
                            if (varName.equals(pair.getFirst())) {
                                TypeSymbol typeSymbol = TypeSymbol.getType(pair.getSecond());
                                if (typeSymbol != null
                                        && (typeSymbol.equals(TypeSymbol.STRING) || typeSymbol.equals(TypeSymbol.BOOL)
                                        || typeSymbol.equals(TypeSymbol.SELF))) {
                                    SymbolTable.error(sum.getRight().getToken().getLine()
                                                    + ":" + (sum.getRight().getToken().getCharPositionInLine() + 1),
                                            " Operand of " + sum.getOp().getText() + " has type "
                                                    + typeSymbol.getName() + " instead of Int");
                                    //return null;
                                }
                            }
                        }
                    }
                } else if (currentScope instanceof LetSymbol) {
                    IdSymbol symbolId = (IdSymbol) ((LetSymbol) currentScope).lookupVar(varName);
                    if (symbolId != null) {
                        TypeSymbol typeSymbol = symbolId.getType();
                        if (typeSymbol != null
                                && (typeSymbol.equals(TypeSymbol.STRING) || typeSymbol.equals(TypeSymbol.BOOL)
                                || typeSymbol.equals(TypeSymbol.SELF))) {
                            SymbolTable.error(sum.getRight().getToken().getLine()
                                            + ":" + (sum.getRight().getToken().getCharPositionInLine() + 1),
                                    " Operand of " + sum.getOp().getText() + " has type "
                                            + typeSymbol.getName() + " instead of Int");
                            //return null;
                        }
                    }
                }
            }
        }

        if (right != null && (right.equals(TypeSymbol.STRING) || right.equals(TypeSymbol.BOOL)
                || right.equals(TypeSymbol.SELF))) {
            SymbolTable.error(sum.getRight().getToken().getLine()
                            + ":" + (sum.getRight().getToken().getCharPositionInLine() + 1),
                    " Operand of " + sum.getOp().getText() + " has type " + right.getName() + " instead of Int");
            //return null;
        }

        if (left == null) {
            if (sum.getLeft() instanceof Id) {
                // check for type
                String varName = sum.getLeft().getToken().getText();
                if (currentScope instanceof ClassSymbol) {
                    String className = currentScope.toString();

                    var pairs = SymbolTable.classesAndVariables.get(className);
                    if (pairs != null) {
                        for (var pair : pairs) {
                            if (varName.equals(pair.getFirst())) {
                                TypeSymbol typeSymbol = TypeSymbol.getType(pair.getSecond());
                                if (typeSymbol != null
                                        && (typeSymbol.equals(TypeSymbol.STRING) || typeSymbol.equals(TypeSymbol.BOOL)
                                        || typeSymbol.equals(TypeSymbol.SELF))) {
                                    SymbolTable.error(sum.getLeft().getToken().getLine()
                                                    + ":" + (sum.getLeft().getToken().getCharPositionInLine() + 1),
                                            " Operand of " + sum.getOp().getText() + " has type "
                                                    + typeSymbol.getName() + " instead of Int");
                                    //return null;
                                }
                            }
                        }
                    }
                } else if (currentScope instanceof LetSymbol) {
                    IdSymbol symbolId = (IdSymbol) ((LetSymbol) currentScope).lookupVar(varName);
                    if (symbolId != null) {
                        TypeSymbol typeSymbol = symbolId.getType();
                        if (typeSymbol != null
                                && (typeSymbol.equals(TypeSymbol.STRING) || typeSymbol.equals(TypeSymbol.BOOL)
                                || typeSymbol.equals(TypeSymbol.SELF))) {
                            SymbolTable.error(sum.getLeft().getToken().getLine()
                                            + ":" + (sum.getLeft().getToken().getCharPositionInLine() + 1),
                                    " Operand of " + sum.getOp().getText() + " has type "
                                            + typeSymbol.getName() + " instead of Int");
                            //return null;
                        }
                    }
                }
            }
        }

        if (left != null && (left.equals(TypeSymbol.STRING) || left.equals(TypeSymbol.BOOL)
                || left.equals(TypeSymbol.SELF))) {
            SymbolTable.error(sum.getLeft().getToken().getLine()
                            + ":" + (sum.getLeft().getToken().getCharPositionInLine() + 1),
                    " Operand of " + sum.getOp().getText() + " has type " + left.getName() + " instead of Int");
            //return null;
        }

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(MultDivNode prod) {
        var left = prod.getLeft().accept(this);
        var right = prod.getRight().accept(this);

        if (right == null) {
            if (prod.getRight() instanceof Id) {
                // check for type
                String className = currentScope.toString();
                String varName = prod.getLeft().getToken().getText();
                var pairs = SymbolTable.classesAndVariables.get(className);
                if (pairs != null) {
                    for (var pair : pairs) {
                        if (varName.equals(pair.getFirst())) {
                            TypeSymbol typeSymbol = TypeSymbol.getType(pair.getSecond());
                            if (typeSymbol != null
                                    && (typeSymbol.equals(TypeSymbol.STRING) || typeSymbol.equals(TypeSymbol.BOOL))) {
                                SymbolTable.error(prod.getLeft().getToken().getLine()
                                                + ":" + (prod.getLeft().getToken().getCharPositionInLine() + 1),
                                        " Operand of " + prod.getOp().getText() + " has type "
                                                + typeSymbol.getName() + " instead of Int");
                                //return null;
                            }
                        }
                    }
                }
            }
        }

        if (right != null && (right.equals(TypeSymbol.STRING) || right.equals(TypeSymbol.BOOL))) {
            SymbolTable.error(prod.getRight().getToken().getLine()
                            + ":" + (prod.getRight().getToken().getCharPositionInLine() + 1),
                    " Operand of " + prod.getOp().getText() + " has type " + right.getName() + " instead of Int");
            //return null;
        }

        if (left == null) {
            if (prod.getLeft() instanceof Id) {
                // check for type
                String className = currentScope.toString();
                String varName = prod.getLeft().getToken().getText();
                var pairs = SymbolTable.classesAndVariables.get(className);
                if (pairs != null) {
                    for (var pair : pairs) {
                        if (varName.equals(pair.getFirst())) {
                            TypeSymbol typeSymbol = TypeSymbol.getType(pair.getSecond());
                            if (typeSymbol != null
                                    && (typeSymbol.equals(TypeSymbol.STRING) || typeSymbol.equals(TypeSymbol.BOOL))) {
                                SymbolTable.error(prod.getLeft().getToken().getLine()
                                                + ":" + (prod.getLeft().getToken().getCharPositionInLine() + 1),
                                        " Operand of " + prod.getOp().getText() + " has type "
                                                + typeSymbol.getName() + " instead of Int");
                                //return null;
                            }
                        }
                    }
                }
            }
        }

        if (left != null && (left.equals(TypeSymbol.STRING) || left.equals(TypeSymbol.BOOL))) {
            SymbolTable.error(prod.getLeft().getToken().getLine()
                            + ":" + (prod.getLeft().getToken().getCharPositionInLine() + 1),
                    " Operand of " + prod.getOp().getText() + " has type " + left.getName() + " instead of Int");
            //return null;
        }

        return TypeSymbol.INT;
    }

    @Override
    public TypeSymbol visit(Program prog) {
        currentScope = new DefaultScope(null);
        currentScope.add(TypeSymbol.BOOL);
        currentScope.add(TypeSymbol.INT);
        currentScope.add(TypeSymbol.STRING);

        for (var statement : prog.getClasses()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public TypeSymbol visit(BoolNode bool) {
        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(FunctionCall call) {
        TypeSymbol returnType = null;

        String originClass = null;
        // find the origin class of the method
        if (call.getExpression() != null) {
            String callee = call.getExpression().getToken().getText();
            if (currentScope instanceof FunctionSymbol) {
                if (!callee.equals("self")) {
                    IdSymbol idSymbol = (IdSymbol) ((FunctionSymbol) currentScope).lookupParam(callee);
                    originClass = idSymbol.getType().getName();
                } else {
                    originClass = currentScope.getParent().toString();
                }
            }
        } else {
            originClass = currentScope.getParent().toString();
        }

        // gotta find the method signature
        // System.out.println(SymbolTable.classesAndMethods);
        var methods = SymbolTable.classesAndMethods.get(originClass);
        FuncDefNode definition = null;
        if (methods != null) {
            for (var method : methods) {
                if (method.getNameToken().getText().equals(call.getName().getToken().getText())) {
                    definition = method;
                }
            }
        }

        // sa vedem daca apelul unei functii din clasa curenta corespunde ca parametrii cu antetul din clasa respectiva
        if (originClass != null) {
            if (originClass.equals(currentScope.getParent().toString())) {
                var classMethods = SymbolTable.classesAndMethods.get(originClass);
                if (classMethods != null) {
                    for (var method : classMethods) {
                        if (method.getNameToken().getText().equals(call.getName().getToken().getText())) {
                            var formals = method.getParams();
                            for (int i = 0; i < formals.size(); i++) {
                                TypeSymbol formalType = null, paramType = null;

                                formalType = TypeSymbol.getType(formals.get(i).getTypeToken().getText());
                                if (formalType == null) {
                                    formalType = new TypeSymbol(formals.get(i).getTypeToken().getText());
                                }

                                var param = call.getParams().get(i);
                                if (param instanceof BoolNode) {
                                    paramType = TypeSymbol.BOOL;
                                } else if (param instanceof StringNode) {
                                    paramType = TypeSymbol.STRING;
                                } else if (param instanceof Id) {
                                    String name = param.getToken().getText();
                                    if (TypeSymbol.isNumeric(name)) {
                                        paramType = TypeSymbol.INT;
                                    } else {
                                        IdSymbol id = (IdSymbol) ((FunctionSymbol) currentScope).lookupParam(name);
                                        if (id != null) {
                                            paramType = id.getType();
                                        }
                                    }
                                }

                                if (paramType != null) {
                                    if (!TypeSymbol.isPrimitive(formalType) && !TypeSymbol.isPrimitive(paramType)) {
                                        if (!formalType.equals(paramType)) {
                                            if (!SymbolTable.checkIfParentClass(paramType.getName(),
                                                    formalType.getName())) {
                                                SymbolTable.error(call.getParams().get(i).getToken().getLine()
                                                                + ":" + (call.getParams().get(i).getToken().getCharPositionInLine() + 1),
                                                        " In call to method " + call.getName().getToken().getText()
                                                                + " of class " + originClass + ", actual type "
                                                                + paramType
                                                                + " of formal parameter "
                                                                + formals.get(i).getNameToken().getText()
                                                                + " is incompatible with declared type " + formalType);

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (definition != null) {
            if (definition.getParams().size() != call.getParams().size()) {
                // Method f of class B is applied to wrong number of arguments
                SymbolTable.error(call.getName().getToken().getLine()
                                + ":" + (call.getName().getToken().getCharPositionInLine() + 1),
                        " Method " + call.getName().getToken().getText()
                                + " of class " + originClass + " is applied to wrong number of arguments");
                return null;
            }
        }

        // gotta check formals
        if (definition != null) {
            for (int i = 0; i < call.getParams().size(); i++) {
                TypeSymbol callType = null;
                var param = call.getParams().get(i);
                if (param instanceof Id) {
                    if (TypeSymbol.isNumeric(param.getToken().getText())) {
                        callType = TypeSymbol.INT;
                    } else {
                        IdSymbol idSymbol = (IdSymbol) ((FunctionSymbol) currentScope)
                                .lookupParam(param.getToken().getText());
                        if (idSymbol != null) {
                            callType = idSymbol.getType();
                        }
                    }
                } else if (param instanceof BoolNode) {
                    callType = TypeSymbol.BOOL;
                } else if (param instanceof StringNode) {
                    callType = TypeSymbol.STRING;
                }

                String formalTypeString = definition.getParams().get(i).getTypeToken().getText();
                TypeSymbol formalType = TypeSymbol.getType(formalTypeString);
                if (formalType == null) {
                    formalType = new TypeSymbol(formalTypeString);
                }

                if (callType != null) {
                    if (TypeSymbol.isPrimitive(callType) && TypeSymbol.isPrimitive(formalType)) {
                        if (!callType.equals(formalType)) {
                            // In call to method f of class B, actual type Bool of formal parameter x is incompatible with declared type Int
                            SymbolTable.error(call.getParams().get(i).getToken().getLine()
                                            + ":" + (call.getParams().get(i).getToken().getCharPositionInLine() + 1),
                                    " In call to method " + call.getName().getToken().getText()
                                            + " of class " + originClass + ", actual type " + callType
                                            + " of formal parameter "
                                            + definition.getParams().get(i).getNameToken().getText()
                                            + " is incompatible with declared type " + formalType);
                            return null;
                        }
                    }
                }
            }
        }

        if (definition != null) {
            String returnTypeName = definition.getRetTypeToken().getText();
            returnType = TypeSymbol.getType(returnTypeName);
            if (returnType == null) {
                returnType = new TypeSymbol(returnTypeName);
            }
        }

        // check if method exists in origin class
        if (originClass == null) {
            // TODO ca 3 jumate noaptea, ce pula mea
            String varName = call.getExpression().getToken().getText();
            TypeSymbol typeVar = null;
            // System.out.println("location " + call.getName().getToken().getLine());
            if (varName.equals("self")) {
                if (currentScope instanceof LetSymbol) {
                    originClass = currentScope.getParent().getParent().toString();
                    // System.out.println(originClass);
                }
            } else {
                if (call.getUpcast() != null) {
                    originClass = call.getUpcast().getText();
                } else {
                    if (currentScope instanceof LetSymbol) {
                        if (call.getExpression() == null) {
                            originClass = currentScope.getParent().getParent().toString();
                        } else {
                            IdSymbol idSymbol = (IdSymbol) ((LetSymbol) currentScope).lookupVar(varName);
                            if (idSymbol != null) {
                                originClass = idSymbol.getType().getName();
                            }
                        }
                    }
                }
            }
        }

        // System.out.println(SymbolTable.classesAndMethods.get("B"));

        // gotta find origin class
        if (call.getExpression() instanceof FunctionCall) {
            String functionName = call.getName().getToken().getText();
            FunctionCall functionCall = (FunctionCall) call.getExpression();
            String classToSearch = null;
            if (functionCall.getExpression() instanceof Id) {
                // search for origin class of variable
                String varName = functionCall.getExpression().getToken().getText();
                if (currentScope instanceof LetSymbol) {
                    IdSymbol idSymbol = (IdSymbol) ((LetSymbol) currentScope).lookupVar(varName);
                    if (idSymbol != null) {
                        // var origin class found, gotta find first function ret type
                        classToSearch = idSymbol.getType().getName();

                        var methodsToSearch = SymbolTable.classesAndMethods.get(classToSearch);
                        if (methodsToSearch != null) {
                            for (var function : methodsToSearch) {
                                if (functionCall.getName().getToken().getText()
                                        .equals(function.getNameToken().getText())) {
                                    // function found, check return type, the class I mean
                                    String retType = function.getRetTypeToken().getText();

                                    var methodsRet = SymbolTable.classesAndMethods.get(retType);
                                    if (methodsRet != null) {
                                        boolean okFound = false;
                                        for (var f : methodsRet) {
                                            if (f.getNameToken().getText().equals(functionName)) {
                                                okFound = true;
                                            }
                                        }

                                        if (!okFound) {
                                            SymbolTable.error(call.getName().getToken().getLine()
                                                            + ":" + (call.getName().getToken().getCharPositionInLine() + 1),
                                                    " Undefined method " + call.getName().getToken().getText()
                                                            + " in class " + retType);
                                            return null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        var methodsClass = SymbolTable.classesAndMethods.get(originClass);
        boolean doesMethodExist = false;
        if (methodsClass != null) {
            for (var method : methodsClass) {
                if (call.getName().getToken().getText().equals(method.getNameToken().getText())) {
                    doesMethodExist = true;
                }
            }
            if (!doesMethodExist) {
                SymbolTable.error(call.getName().getToken().getLine()
                                + ":" + (call.getName().getToken().getCharPositionInLine() + 1),
                        " Undefined method " + call.getName().getToken().getText() + " in class " + originClass);
                return null;
            }
        } else {
            if (SymbolTable.classesAndParents.containsKey(originClass)) {
                SymbolTable.error(call.getName().getToken().getLine()
                                + ":" + (call.getName().getToken().getCharPositionInLine() + 1),
                        " Undefined method " + call.getName().getToken().getText() + " in class " + originClass);
                return null;
            }
        }

        if (call.getUpcast() != null) {
            var parentClass = call.getUpcast().getText();
            // System.out.println("parent class " + parentClass + " " + originClass);

            // upcast cannot be SELF_TYPE
            if (parentClass.equals(TypeSymbol.SELF.getName())) {
                SymbolTable.error(call.getUpcast().getLine()
                                + ":" + (call.getUpcast().getCharPositionInLine() + 1),
                        " Type of static dispatch cannot be SELF_TYPE");
                return null;
            }

            // upcast type must exist
            if (!SymbolTable.checkIfTypeExists(parentClass)) {
                // Type Y of static dispatch is undefined
                SymbolTable.error(call.getUpcast().getLine()
                                + ":" + (call.getUpcast().getCharPositionInLine() + 1),
                        " Type " + parentClass + " of static dispatch is undefined");
                return null;
            }

            if (!SymbolTable.checkIfParentClass(originClass, parentClass) && !originClass.equals(parentClass)) {
                // Type A of static dispatch is not a superclass of type C
                SymbolTable.error(call.getUpcast().getLine()
                                + ":" + (call.getUpcast().getCharPositionInLine() + 1),
                        " Type " + parentClass + " of static dispatch is not a superclass of type " + originClass);
                return null;
            }

            // check if method exists in upcast class
            var methodsUpcast = SymbolTable.classesAndMethods.get(parentClass);
            // System.out.println(methodsUpcast);
            // System.out.println("parent " + parentClass + " call " + call.getName().getToken().getText());
            boolean doesExist = false;
            if (methodsUpcast != null) {
                for (var methodUpcast : methodsUpcast) {
                    if (call.getName().getToken().getText().equals(methodUpcast.getNameToken().getText())) {
                        doesExist = true;
                    }
                }

                if (!doesExist) {
                    SymbolTable.error(call.getName().getToken().getLine()
                                    + ":" + (call.getName().getToken().getCharPositionInLine() + 1),
                            " Undefined method " + call.getName().getToken().getText() + " in class " + parentClass);
                    return null;
                }
            } else {
                SymbolTable.error(call.getName().getToken().getLine()
                                + ":" + (call.getName().getToken().getCharPositionInLine() + 1),
                        " Undefined method " + call.getName().getToken().getText() + " in class " + parentClass);
                return null;
            }
        }

        for (var arg : call.getParams()) {
            arg.accept(this);
        }
        var id = new Id(call.getName().getToken());
        id.setScope(currentScope);


        return returnType;
    }

    @Override
    public TypeSymbol visit(CompareNode comp) {
        var left = comp.getLeft().accept(this);
        var right = comp.getRight().accept(this);
        /*
        System.out.println(comp.getLeft() + " " + comp.getRight());
        System.out.println(left + " " + right);
        */
        TypeSymbol type1 = null, type2 = null;

        if (comp.getLeft() instanceof BoolNode) {
            type1 = TypeSymbol.BOOL;
        } else if (comp.getLeft() instanceof StringNode) {
            type1 = TypeSymbol.STRING;
        } else if (comp.getLeft() instanceof Id) {
            if (TypeSymbol.isNumeric(comp.getLeft().getToken().getText())) {
                type1 = TypeSymbol.INT;
            } else {
                String varName = comp.getLeft().getToken().getText();
                String className = currentScope.toString();

                var pairs = SymbolTable.classesAndVariables.get(className);
                if (pairs != null) {
                    for (var pair : pairs) {
                        if (varName.equals(pair.getFirst())) {
                            type1 = TypeSymbol.getType(pair.getSecond());
                        }
                    }
                }
            }
        }

        if (comp.getRight() instanceof BoolNode) {
            type2 = TypeSymbol.BOOL;
        } else if (comp.getRight() instanceof StringNode) {
            type2 = TypeSymbol.STRING;
        } else if (comp.getRight() instanceof Id) {
            if (TypeSymbol.isNumeric(comp.getRight().getToken().getText())) {
                type2 = TypeSymbol.INT;
            } else {
                String varName = comp.getRight().getToken().getText();
                String className = currentScope.toString();

                var pairs = SymbolTable.classesAndVariables.get(className);
                if (pairs != null) {
                    for (var pair : pairs) {
                        if (varName.equals(pair.getFirst())) {
                            type2 = TypeSymbol.getType(pair.getSecond());
                        }
                    }
                }
            }
        }

        if (type1 != null && type2 != null) {
            // must be Int
            if (comp.getOp().getText().equals("<") || comp.getOp().getText().equals("<=")) {
                if (!type1.equals(TypeSymbol.INT)) {
                    SymbolTable.error(comp.getLeft().getToken().getLine()
                                    + ":" + (comp.getLeft().getToken().getCharPositionInLine() + 1),
                            " Operand of " + comp.getOp().getText() + " has type "
                                    + type1.getName() + " instead of Int");
                    return null;
                }

                if (!type2.equals(TypeSymbol.INT)) {
                    SymbolTable.error(comp.getRight().getToken().getLine()
                                    + ":" + (comp.getRight().getToken().getCharPositionInLine() + 1),
                            " Operand of " + comp.getOp().getText() + " has type "
                                    + type2.getName() + " instead of Int");
                    return null;
                }
            } else if (comp.getOp().getText().equals("=")) {
                if (!type1.equals(type2)) {
                    // cannot compare with different 2 types
                    SymbolTable.error(comp.getOp().getLine()
                                    + ":" + (comp.getOp().getCharPositionInLine() + 1),
                            " Cannot compare " + type1.getName() + " with " + type2.getName());
                    return null;
                }
            }
        }

        return TypeSymbol.BOOL;
    }

    @Override
    public TypeSymbol visit(FloatNode flt) {
        return null;
    }

    @Override
    public TypeSymbol visit(FuncDefNode func) {
        String className = currentScope.toString();
        String parentClass = SymbolTable.findParentClass(className);

        if (func.getNameToken().getText().equals("abort")) {
            return TypeSymbol.OBJECT;
        }

        if (parentClass != null) {
            var functions = SymbolTable.classesAndMethods.get(parentClass);
            if (functions != null) {
                for (var function : functions) {
                    if (function.getNameToken().getText().equals(func.getNameToken().getText())) {
                        // check override with different number of formals
                        if (function.getParams().size() != func.getParams().size()) {
                            SymbolTable.error(func.getNameToken().getLine()
                                            + ":" + (func.getNameToken().getCharPositionInLine() + 1),
                                    " Class " + className + " overrides method " + func.getNameToken().getText()
                                            + " with different number of formal parameters");
                            return null;
                        }

                        // check if formal types are the same
                        for (int i = 0; i < func.getParams().size(); i++) {
                            if (!function.getParams().get(i).getTypeToken().getText()
                                    .equals(func.getParams().get(i).getTypeToken().getText())) {
                                SymbolTable.error(func.getParams().get(i).getTypeToken().getLine()
                                                + ":" + (func.getParams().get(i).getTypeToken().getCharPositionInLine() + 1),
                                        " Class " + className + " overrides method " + func.getNameToken().getText()
                                                + " but changes type of formal parameter "
                                                + func.getParams().get(i).getNameToken().getText()
                                                + " from " + function.getParams().get(i).getTypeToken().getText()
                                                + " to " + func.getParams().get(i).getTypeToken().getText());
                                return null;
                            }
                        }

                        // check if return type is the same as in parent class
                        if (!func.getRetTypeToken().getText().equals(function.getRetTypeToken().getText())) {
                            // Class F overrides method f but changes return type from Int to Bool
                            SymbolTable.error(func.getNameToken().getLine()
                                            + ":" + (func.getRetTypeToken().getCharPositionInLine() + 1),
                                    " Class " + className + " overrides method " + func.getNameToken().getText()
                                            + " but changes return type from " + function.getRetTypeToken().getText()
                                            + " to " + func.getRetTypeToken().getText());
                            return null;
                        }
                    }
                }
            }
        }

        FunctionSymbol functionSymbol = new FunctionSymbol(currentScope, func.getNameToken().getText());
        currentScope = functionSymbol;

        var variables = func.getParams();

        for (var variable : variables) {
            IdSymbol id = new IdSymbol(variable.getNameToken().getText());
            TypeSymbol idType = TypeSymbol.getType(variable.getTypeToken().getText());
            if (idType == null) {
                idType = new TypeSymbol(variable.getTypeToken().getText());
            }
            id.setType(idType);
            currentScope.add(id);
            ((FunctionSymbol) currentScope).addParam(id);

            variable.accept(this);
        }

        TypeSymbol type = func.getBody().accept(this), retType = null;
        if (func.getBody() instanceof Id) {
            String varName = func.getBody().getToken().getText();
            IdSymbol symbol = (IdSymbol) ((FunctionSymbol) currentScope).lookupParam(varName);
            if (symbol != null) {
                type = symbol.getType();
            }
        } else if (func.getBody() instanceof BlockNode) {
            var bodyExpressions = ((BlockNode) func.getBody()).getExpressions();
            var bodyType = bodyExpressions.get(bodyExpressions.size() - 1);
            if (bodyType instanceof Id) {
                if (TypeSymbol.isNumeric(bodyType.getToken().getText())) {
                    type = TypeSymbol.INT;
                }
            }
        }
        currentScope = currentScope.getParent();

        String returnType = func.getRetTypeToken().getText();
        retType = TypeSymbol.getType(returnType);
        if (retType == null) {
            retType = new TypeSymbol(returnType);
        }

        var token = func.getBody().getToken();

        if (type != null && retType != null) {
            if (TypeSymbol.isPrimitive(type) && TypeSymbol.isPrimitive(retType)) {
                // un mic hack ca imi bag pula in ea de tema, AM SI EU VACANTA MEA
                if (!type.equals(retType) && !(retType.equals(TypeSymbol.INT) && type.equals(TypeSymbol.OBJECT))) {
                    // Type B of the body of method j is incompatible with declared return type C
                    // AICI INTRA MORTII MA-SII -> rezolvat
                    SymbolTable.error(token.getLine()
                                    + ":" + (token.getCharPositionInLine() + 1),
                            " Type " + type + " of the body of method "
                                    + func.getNameToken().getText()
                                    + " is incompatible with declared return type " + retType);
                    return null;
                }
            }

            if (!TypeSymbol.isPrimitive(type) && !TypeSymbol.isPrimitive(retType)) {
                if (!SymbolTable.checkIfParentClass(type.getName(), retType.getName()) && !type.equals(retType)) {
                    SymbolTable.error(token.getLine()
                                    + ":" + (token.getCharPositionInLine() + 1),
                            " Type " + type + " of the body of method "
                                    + func.getNameToken().getText()
                                    + " is incompatible with declared return type " + retType);
                    return null;
                }
            }

            if (retType.equals(TypeSymbol.SELF)) {
                if (currentScope instanceof ClassSymbol) {
                    retType = new TypeSymbol(currentScope.toString());
                    if (!type.equals(retType)) {
                        SymbolTable.error(token.getLine()
                                        + ":" + (token.getCharPositionInLine() + 1),
                                " Type " + type + " of the body of method "
                                        + func.getNameToken().getText()
                                        + " is incompatible with declared return type " + TypeSymbol.SELF);
                        return null;
                    }
                }
            }

        }

        //System.out.println(retType + " " + type);

        return type;
    }

    @Override
    public TypeSymbol visit(UnaryMinusNode minus) {
        var result = minus.getNested().accept(this);

        if (result != null && (result.equals(TypeSymbol.STRING) || result.equals(TypeSymbol.BOOL))) {
            SymbolTable.error(minus.getNested().getToken().getLine()
                            + ":" + (minus.getNested().getToken().getCharPositionInLine() + 1),
                    " Operand of ~ has type " + result.getName() + " instead of Int");
            // return null;
        }
        return TypeSymbol.INT;
    }

    private FunctionCall getLastCall(FunctionCall call) {
        return null;
    }

    @Override
    public TypeSymbol visit(VarDef var) {
        if (var.getType().equals(VarDefType.ATTRIBUTE)) {
            String classNode = currentScope.toString();
            String parentClass = SymbolTable.findParentClass(classNode);
            if (parentClass != null) {
                if (parentClass.equals("IO")) {
                    if (var.getInitExpr() != null) {

                        if (var.getInitExpr() instanceof FunctionCall) {
                            String callName = null;
                            //System.out.println(var.getInitExpr().getToken().getText());

                            var value = (FunctionCall) var.getInitExpr();

                            String assignType = var.getTypeToken().getText();
                            callName = value.getName().getToken().getText();
                            // System.out.println(value.getName().getToken().getText());
                            if (callName.equals("abort")) {
                                if (!assignType.equals(TypeSymbol.OBJECT.getName())) {
                                    SymbolTable.error(value.getToken().getLine()
                                                    + ":" + (value.getToken().getCharPositionInLine() + 1),
                                            " Type " + TypeSymbol.OBJECT.getName()
                                                    + " of initialization expression of attribute "
                                                    + var.getNameToken().getText()
                                                    + " is incompatible with declared type " + assignType);
                                    return null;
                                }
                            } else if (callName.equals("copy")) {
                                if (!assignType.equals(currentScope.toString())) {
                                    SymbolTable.error(value.getToken().getLine()
                                                    + ":" + (value.getToken().getCharPositionInLine() + 1),
                                            " Type " + TypeSymbol.SELF.getName()
                                                    + " of initialization expression of attribute "
                                                    + var.getNameToken().getText()
                                                    + " is incompatible with declared type " + assignType);
                                    return null;
                                }
                            } else if (callName.equals("in_int")) {
                                if (!assignType.equals(TypeSymbol.INT.getName())) {
                                    SymbolTable.error(value.getToken().getLine()
                                                    + ":" + (value.getToken().getCharPositionInLine() + 1),
                                            " Type " + TypeSymbol.INT.getName()
                                                    + " of initialization expression of attribute "
                                                    + var.getNameToken().getText()
                                                    + " is incompatible with declared type " + assignType);
                                    return null;
                                }
                            } else if (callName.equals("substr")) {
                                if (!assignType.equals(TypeSymbol.STRING.getName())) {
                                    SymbolTable.error(value.getToken().getLine()
                                                    + ":" + (value.getToken().getCharPositionInLine() + 1),
                                            " Type " + TypeSymbol.STRING.getName()
                                                    + " of initialization expression of attribute "
                                                    + var.getNameToken().getText()
                                                    + " is incompatible with declared type " + assignType);
                                    return null;
                                }

                                var firstParam = value.getParams().get(0);
                                if (firstParam instanceof Id) {
                                    TypeSymbol typeSymbol = null;
                                    if (TypeSymbol.isNumeric(firstParam.getToken().getText())) {
                                        typeSymbol = TypeSymbol.INT;
                                    } else {
                                        var pairs = SymbolTable.classesAndVariables.get(classNode);
                                        for (var pair : pairs) {
                                            if (pair.getFirst().equals(firstParam.getToken().getText())) {
                                                typeSymbol = TypeSymbol.getType(pair.getSecond());
                                                if (typeSymbol == null) {
                                                    typeSymbol = new TypeSymbol(pair.getSecond());
                                                }
                                            }
                                        }
                                    }

                                    if (typeSymbol != null) {
                                        if (!typeSymbol.equals(TypeSymbol.INT)) {
                                            // In call to method substr of class String, actual type String of formal parameter l is incompatible with declared type Int
                                            SymbolTable.error(firstParam.getToken().getLine()
                                                            + ":" + (firstParam.getToken().getCharPositionInLine() + 1),
                                                    " In call to method " + callName
                                                            + " of class String, actual type " + typeSymbol
                                                            + " of formal parameter i"
                                                            + " is incompatible with declared type Int");
                                            return null;
                                        }
                                    }
                                }

                                var secondParam = value.getParams().get(1);
                                if (secondParam instanceof Id) {
                                    TypeSymbol typeSymbol = null;
                                    if (TypeSymbol.isNumeric(secondParam.getToken().getText())) {
                                        typeSymbol = TypeSymbol.INT;
                                    } else {
                                        var pairs = SymbolTable.classesAndVariables.get(classNode);
                                        for (var pair : pairs) {
                                            if (pair.getFirst().equals(secondParam.getToken().getText())) {
                                                typeSymbol = TypeSymbol.getType(pair.getSecond());
                                                if (typeSymbol == null) {
                                                    typeSymbol = new TypeSymbol(pair.getSecond());
                                                }
                                            }
                                        }
                                    }

                                    if (typeSymbol != null) {
                                        if (!typeSymbol.equals(TypeSymbol.INT)) {
                                            // In call to method substr of class String, actual type String of formal parameter l is incompatible with declared type Int
                                            SymbolTable.error(secondParam.getToken().getLine()
                                                            + ":" + (secondParam.getToken().getCharPositionInLine() + 1),
                                                    " In call to method " + callName
                                                            + " of class String, actual type " + typeSymbol
                                                            + " of formal parameter l"
                                                            + " is incompatible with declared type Int");
                                            return null;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // checking for variable redefinition
                    Pair<String, String> pair = new Pair<>(var.getNameToken().getText(), var.getTypeToken().getText());
                    var vars = SymbolTable.classesAndVariables.get(parentClass);
                    if (vars != null) {
                        for (var variable : vars) {
                            if (pair.equals(variable)) {
                                SymbolTable.error(var.getNameToken().getLine()
                                                + ":" + (var.getNameToken().getCharPositionInLine() + 1),
                                        " Class " + currentScope.toString() + " redefines inherited attribute "
                                                + pair.getFirst());
                                return null;
                            }
                        }
                    }
                }
            }

            TypeSymbol type1 = null, type2 = null;
            // type 1
            type1 = TypeSymbol.getType(var.getTypeToken().getText());
            if (type1 == null) {
                type1 = new TypeSymbol(var.getTypeToken().getText());
            }

            // type 2
            if (var.getInitExpr() != null) {
                type2 = var.getInitExpr().accept(this);
                if (var.getInitExpr() instanceof Id) {
                    if (TypeSymbol.isNumeric(var.getInitExpr().getToken().getText())) {
                        type2 = TypeSymbol.INT;
                    }
                }
            }

            if (type1 != null && type2 != null) {
                if (TypeSymbol.isPrimitive(type1) && TypeSymbol.isPrimitive(type2)) {
                    if (!type1.equals(type2)) {
                        // Type Bool of initialization expression of identifier y is incompatible with declared type Int
                        SymbolTable.error(var.getInitExpr().getToken().getLine()
                                        + ":" + (var.getInitExpr().getToken().getCharPositionInLine() + 1),
                                " Type " + type2 + " of initialization expression of attribute "
                                        + var.getNameToken().getText()
                                        + " is incompatible with declared type " + type1);
                        return null;
                    }
                }

                if (!TypeSymbol.isPrimitive(type1) && !TypeSymbol.isPrimitive(type2)) {
                    if (!SymbolTable.checkIfParentClass(type2.getName(), type1.getName()) && !type1.equals(type2)) {
                        SymbolTable.error(var.getInitExpr().getToken().getLine()
                                        + ":" + (var.getInitExpr().getToken().getCharPositionInLine() + 1),
                                " Type " + type2 + " of initialization expression of attribute "
                                        + var.getNameToken().getText()
                                        + " is incompatible with declared type " + type1);
                        return null;
                    }
                }

            }

        } else if (var.getType().equals(VarDefType.FORMAL)) {
            // check if formal has existing type
            if (!SymbolTable.checkIfTypeExists(var.getTypeToken().getText())) {
                SymbolTable.error(var.getTypeToken().getLine()
                                + ":" + (var.getTypeToken().getCharPositionInLine() + 1),
                        " Method " + currentScope + " of class " + currentScope.getParent()
                                + " has formal parameter " + var.getNameToken().getText()
                                + " with undefined type " + var.getTypeToken().getText());
                return null;
            }
        }
        return null;
    }

    @Override
    public TypeSymbol visit(AssignmentNode assignmentNode) {
        String variable = assignmentNode.getVarName().getText();
        var assign = assignmentNode.getValue().accept(this);
        // System.out.println(assign);
        var value = assignmentNode.getValue();
        TypeSymbol variableType = null, assignType = null;

        // Cannot assign to self
        if (variable.equals("self")) {
            SymbolTable.error(assignmentNode.getVarName().getLine()
                            + ":" + (assignmentNode.getVarName().getCharPositionInLine() + 1),
                    " Cannot assign to self");
            return null;
        }

        // left type
        if (currentScope instanceof FunctionSymbol) {
            var symbol = currentScope.lookup(variable);
            if (symbol instanceof IdSymbol) {
                variableType = ((IdSymbol) symbol).getType();
            }
        }

        if (currentScope.getParent() instanceof ClassSymbol) {
            String className = currentScope.getParent().toString();
            var pairs = SymbolTable.classesAndVariables.get(className);
            if (pairs != null) {
                for (var pair : pairs) {
                    if (variable.equals(pair.getFirst())) {
                        variableType = TypeSymbol.getType(pair.getSecond());
                        if (variableType == null) {
                            variableType = new TypeSymbol(pair.getSecond());
                        }
                    }
                }
            }
        }

        // right type
        if (value instanceof StringNode) {
            assignType = TypeSymbol.STRING;
        } else if (value instanceof BoolNode) {
            assignType = TypeSymbol.BOOL;
        } else if (value instanceof Id) {
            String id = value.getToken().getText();
            if (TypeSymbol.isNumeric(id)) {
                assignType = TypeSymbol.INT;
            } else {
                String className = currentScope.getParent().toString();
                var pairs = SymbolTable.classesAndVariables.get(className);
                for (var pair : pairs) {
                    if (id.equals(pair.getFirst())) {
                        assignType = TypeSymbol.getType(pair.getSecond());
                        if (assignType == null) {
                            assignType = new TypeSymbol(pair.getSecond());
                        }
                    }
                }
            }
        }

        // System.out.println(assignmentNode.getVarName().getText() + " " + variableType + " " + assignType);

        if (variableType != null && assignType != null) {
            if (TypeSymbol.isPrimitive(variableType) && TypeSymbol.isPrimitive(assignType)) {
                if (!variableType.equals(assignType)) {
                    // Type Int of assigned expression is incompatible with declared type Bool of identifier x
                    SymbolTable.error(value.getToken().getLine()
                                    + ":" + (value.getToken().getCharPositionInLine() + 1),
                            " Type " + assignType.getName() + " of assigned expression is incompatible with declared type "
                                    + variableType.getName() + " of identifier "
                                    + assignmentNode.getVarName().getText());
                    return null;
                }
            }

            if (!TypeSymbol.isPrimitive(variableType) && !TypeSymbol.isPrimitive(assignType)) {
                if (SymbolTable.checkIfParentClass(variableType.getName(), assignType.getName())) {
                    SymbolTable.error(value.getToken().getLine()
                                    + ":" + (value.getToken().getCharPositionInLine() + 1),
                            " Type " + assignType.getName() + " of assigned expression is incompatible with declared type "
                                    + variableType.getName() + " of identifier "
                                    + assignmentNode.getVarName().getText());
                    return null;
                }
            }


        }

        // for new
        if (assign != null && variableType != null) {
            if (!TypeSymbol.isPrimitive(variableType) && !TypeSymbol.isPrimitive(assign)) {
                if (SymbolTable.checkIfParentClass(variableType.getName(), assign.getName())) {
                    SymbolTable.error(assignmentNode.getValue().getToken().getLine()
                                    + ":" + (assignmentNode.getValue().getToken().getCharPositionInLine() + 1),
                            " Type " + assign.getName() + " of assigned expression is incompatible with declared type "
                                    + variableType.getName() + " of identifier "
                                    + assignmentNode.getVarName().getText());
                    return null;
                }
            } else if (!variableType.equals(assign)) {
                SymbolTable.error(assignmentNode.getValue().getToken().getLine()
                                + ":" + (assignmentNode.getValue().getToken().getCharPositionInLine() + 1),
                        " Type " + assign.getName() + " of assigned expression is incompatible with declared type "
                                + variableType.getName() + " of identifier "
                                + assignmentNode.getVarName().getText());
                return null;
            }

        }

        return null;
    }

    @Override
    public TypeSymbol visit(ClassNode classNode) {
        var id = classNode.getName();
        var parent = classNode.getParent();

        if (parent != null) {
            TypeSymbol symbol = new TypeSymbol(parent.getText());
            if (symbol.equals(TypeSymbol.INT)
                    || symbol.equals(TypeSymbol.STRING)
                    || symbol.equals(TypeSymbol.BOOL)
                    || symbol.equals(TypeSymbol.SELF)) {
                SymbolTable.error(classNode.getParent().getLine()
                                + ":" + (classNode.getParent().getCharPositionInLine() + 1),
                        " Class " + id.getText() + " has illegal parent " + parent.getText());
                return null;
            }

            if (!SymbolTable.checkExistingClass(parent.getText())) {
                SymbolTable.error(classNode.getParent().getLine()
                                + ":" + (classNode.getParent().getCharPositionInLine() + 1),
                        " Class " + id.getText() + " has undefined parent " + parent.getText());
                return null;
            }
        }

        if (SymbolTable.checkForInheritanceCycle(id.getText())) {
            SymbolTable.error(classNode.getToken().getLine()
                            + ":" + (classNode.getName().getCharPositionInLine() + 1),
                    " Inheritance cycle for class " + id.getText());
            return null;
        }

        ClassSymbol classSymbol = new ClassSymbol(currentScope, classNode.getName().getText());
        currentScope = classSymbol;

        var definitions = classNode.getDefinitions();
        for (var def : definitions) {
            def.accept(this);
        }

        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public TypeSymbol visit(StringNode stringNode) {
        return TypeSymbol.STRING;
    }

    @Override
    public TypeSymbol visit(WhileNode whileNode) {
        return TypeSymbol.OBJECT;
    }

    @Override
    public TypeSymbol visit(VoidNode voidNode) {
        return null;
    }

    @Override
    public TypeSymbol visit(NewNode newNode) {
        String type = newNode.getType().getText();
        TypeSymbol typeSymbol = new TypeSymbol(type);

        if (!TypeSymbol.isPrimitive(typeSymbol) && !SymbolTable.classesAndParents.containsKey(type)
                && !typeSymbol.equals(TypeSymbol.SELF)) {
            // new is used with undefined type C
            SymbolTable.error(newNode.getType().getLine()
                            + ":" + (newNode.getType().getCharPositionInLine() + 1),
                    " new is used with undefined type " + type);
            return null;
        }

        return typeSymbol;
    }

    @Override
    public TypeSymbol visit(LetNode letNode) {
        LetSymbol letSymbol = new LetSymbol(currentScope, null);
        currentScope = letSymbol;
        var variables = letNode.getVariables();

        List<TypeSymbol> types = new ArrayList<>();

        for (var variable : variables) {
            IdSymbol sym = new IdSymbol(variable.getName().getText());
            TypeSymbol typeSymbol = TypeSymbol.getType(variable.getType().getText());
            if (typeSymbol == null) {
                typeSymbol = new TypeSymbol(variable.getType().getText());
            }
            sym.setType(typeSymbol);
            ((LetSymbol) currentScope).addVar(sym);

            var type = variable.accept(this);
            types.add(type);
        }
        var body = letNode.getBody().accept(this);
        currentScope = currentScope.getParent();

        return body;
    }

    @Override
    public TypeSymbol visit(ListVariables listVariables) {
        // check if type exists

        // Let variable x has undefined type C
        if (!SymbolTable.checkIfTypeExists(listVariables.getType().getText())) {
            SymbolTable.error(listVariables.getType().getLine()
                            + ":" + (listVariables.getType().getCharPositionInLine() + 1),
                    " Let variable " + listVariables.getName().getText()
                            + " has undefined type " + listVariables.getType().getText());
            return null;
        }
        if (listVariables.getExpression() != null) {
            listVariables.getExpression().accept(this);
        }

        TypeSymbol type1 = null, type2 = null;
        type1 = TypeSymbol.getType(listVariables.getType().getText());
        if (type1 == null) {
            type1 = new TypeSymbol(listVariables.getType().getText());
        }

        var value = listVariables.getExpression();

        if (value instanceof BoolNode) {
            type2 = TypeSymbol.BOOL;
        } else if (value instanceof StringNode) {
            type2 = TypeSymbol.STRING;
        } else if (value instanceof Id) {
            String id = value.getToken().getText();
            if (TypeSymbol.isNumeric(id)) {
                type2 = TypeSymbol.INT;
            } else {
                //System.out.println(value.getToken().getText());
                IdSymbol symbol = (IdSymbol) ((LetSymbol) currentScope).lookupVar(value.getToken().getText());
                type2 = symbol.getType();
            }
        } else if (value instanceof NewNode) {
            type2 = new TypeSymbol(((NewNode) value).getType().getText());
        } else if (value instanceof AddSub || value instanceof MultDivNode) {
            type2 = TypeSymbol.INT;
        }

        // System.out.println(type1 + " " + type2);

        if (type1 != null && type2 != null) {
            if (TypeSymbol.isPrimitive(type1) && TypeSymbol.isPrimitive(type2)) {
                if (!type1.equals(type2)) {
                    // Type Bool of initialization expression of identifier y is incompatible with declared type Int
                    SymbolTable.error(listVariables.getExpression().getToken().getLine()
                                    + ":" + (listVariables.getExpression().getToken().getCharPositionInLine() + 1),
                            " Type " + type2 + " of initialization expression of identifier "
                                    + listVariables.getName().getText()
                                    + " is incompatible with declared type " + type1);
                    return null;
                }
            }

            if (!TypeSymbol.isPrimitive(type1) && !TypeSymbol.isPrimitive(type2)) {
                if (!SymbolTable.checkIfParentClass(type2.getName(), type1.getName()) && !type1.equals(type2)) {
                    SymbolTable.error(listVariables.getExpression().getToken().getLine()
                                    + ":" + (listVariables.getExpression().getToken().getCharPositionInLine() + 1),
                            " Type " + type2 + " of initialization expression of identifier "
                                    + listVariables.getName().getText()
                                    + " is incompatible with declared type " + type1);
                    return null;
                }
            }

        }

        return type1;
    }

    @Override
    public TypeSymbol visit(CaseOfNode caseOfNode) {
        caseOfNode.getExpression().accept(this);

        CaseSymbol caseSymbol = new CaseSymbol(currentScope, caseOfNode.getExpression().getToken().getText());
        currentScope = caseSymbol;

        var branches = caseOfNode.getBranches();
        List<TypeSymbol> types = new ArrayList<>();
        TypeSymbol typeSymbol = null;
        for (var branch : branches) {
            var type = branch.accept(this);
            types.add(type);
        }
        currentScope = currentScope.getParent();

        if (types.size() == 1) {
            typeSymbol = types.get(0);
        } else {
            List<TypeSymbol> primitives = new ArrayList<>();
            //System.out.println(types);
            for (var type : types) {
                if (type != null) {
                    if (TypeSymbol.isPrimitive(type)) {
                        primitives.add(type);
                    }
                }
            }

            if (primitives.size() <= types.size() && primitives.size() > 0) {
                typeSymbol = TypeSymbol.OBJECT;
            }

            if (primitives.size() == 0) {
                List<TypeSymbol> parentTypes = new ArrayList<>();
                for (var type : types) {
                    TypeSymbol parent = new TypeSymbol(SymbolTable.findMostParentClass(type.getName()));
                    if (parent == null) {
                        parent = new TypeSymbol(type.getName());
                    }
                    parentTypes.add(parent);
                }

                boolean same = true;
                for (int i = 1; i < parentTypes.size(); i++) {
                    if (!parentTypes.get(0).equals(parentTypes.get(i))) {
                        same = false;
                    }
                }

                if (same) {
                    typeSymbol = parentTypes.get(0);
                }
            }
        }

        return typeSymbol;
    }

    @Override
    public TypeSymbol visit(Branch branch) {
        var value = branch.getExpression().accept(this);

        // check if type exists
        if (!SymbolTable.checkIfTypeExists(branch.getType().getText())) {
            SymbolTable.error(branch.getType().getLine()
                            + ":" + (branch.getType().getCharPositionInLine() + 1),
                    " Case variable " + branch.getName().getText()
                            + " has undefined type " + branch.getType().getText());
            return null;
        }

        // searching for the type of value assigned
        TypeSymbol typeSymbol = null;
        if (branch.getExpression() instanceof BoolNode) {
            typeSymbol = TypeSymbol.BOOL;
        } else if (branch.getExpression() instanceof StringNode) {
            typeSymbol = TypeSymbol.STRING;
        } else if (branch.getExpression() instanceof Id) {
            String id = branch.getExpression().getToken().getText();
            if (TypeSymbol.isNumeric(id)) {
                typeSymbol = TypeSymbol.INT;
            } else {
                var params = ((FunctionSymbol) currentScope.getParent()).getParams();
                for (var param : params.entrySet()) {
                    if (id.equals(param.getKey())) {
                        typeSymbol = ((IdSymbol) param.getValue()).getType();
                    }
                }
            }
        }
        return typeSymbol;
    }

    @Override
    public TypeSymbol visit(BlockNode blockNode) {
        var expressions = blockNode.getExpressions();
        for (var expr : expressions) {
            var type = expr.accept(this);
        }

        TypeSymbol type = null;
        if (expressions.get(expressions.size() - 1) instanceof StringNode) {
            type = TypeSymbol.STRING;
        }

        return type;
    }

    @Override
    public TypeSymbol visit(ParanthesesNode paranthesesNode) {
        var result = paranthesesNode.getExpression().accept(this);
        if (paranthesesNode.getExpression() instanceof AddSub ||
                paranthesesNode.getExpression() instanceof MultDivNode ||
                paranthesesNode.getExpression() instanceof UnaryMinusNode) {
            return TypeSymbol.INT;
        }
        return result;
    }

    @Override
    public TypeSymbol visit(NegationNode negationNode) {
        var result = negationNode.getNested().accept(this);
        if (negationNode.getNested() instanceof Id) {
            String id = negationNode.getNested().getToken().getText();
            if (TypeSymbol.isNumeric(id)) {
                // Cannot compare Int with Bool
                SymbolTable.error(negationNode.getNested().getToken().getLine()
                                + ":" + (negationNode.getNested().getToken().getCharPositionInLine() + 1),
                        " Operand of not has type Int instead of Bool");
                return null;
            } else {
                String className = currentScope.toString();
                var pairs = SymbolTable.classesAndVariables.get(className);
                if (pairs != null) {
                    for (var pair : pairs) {
                        if (id.equals(pair.getFirst())) {
                            if (!pair.getSecond().equals(TypeSymbol.BOOL.getName())) {
                                SymbolTable.error(negationNode.getNested().getToken().getLine()
                                                + ":" + (negationNode.getNested().getToken().getCharPositionInLine() + 1),
                                        " Operand of not has type " + pair.getSecond() + " instead of Bool");
                                return null;
                            }
                        }
                    }
                }
            }
        } else if (negationNode.getNested() instanceof StringNode) {
            SymbolTable.error(negationNode.getNested().getToken().getLine()
                            + ":" + (negationNode.getNested().getToken().getCharPositionInLine() + 1),
                    " Operand of not has type String instead of Bool");
            return null;
        }
        return TypeSymbol.BOOL;
    }
}
