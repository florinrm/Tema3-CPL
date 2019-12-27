package cool.structures;

import cool.compiler.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class DefinitionPassVisitor implements Visitor<Void> {
    private Scope currentScope = null;
    private String calleeId = null;

    @Override
    public Void visit(Id id) {
        var symbol = (IdSymbol) currentScope.lookup(id.getToken().getText());
        id.setScope(currentScope);

        /*
        if (symbol == null) {
            var token = id.getToken();
            SymbolTable.error("line " + token.getLine()
                    + ":" + (token.getCharPositionInLine() + 1)
                    + ", " + token.getText() + " undefined");
        } */
        return null;
    }

    @Override
    public Void visit(Int intt) {
        return null;
    }

    @Override
    public Void visit(If iff) {
        iff.getCond().accept(this);

        if (iff.getCond() instanceof Id) {
            if (currentScope instanceof FunctionSymbol) {
                String varName = iff.getCond().getToken().getText();
                TypeSymbol typeSymbol = null;
                Symbol symbol = ((FunctionSymbol) currentScope).lookupParam(varName);
                if (symbol == null) {
                    var res = SymbolTable.classesAndVariables.get(currentScope.getParent().toString());
                    if (res != null) {
                        for (var pair : res) {
                            if (pair.getFirst().equals(varName)) {
                                typeSymbol = TypeSymbol.getType(pair.getSecond());
                                if (typeSymbol == null) {
                                    typeSymbol = new TypeSymbol(pair.getSecond());
                                }
                            }
                        }
                    }
                } else {
                    IdSymbol idSymbol = (IdSymbol) symbol;
                    typeSymbol = idSymbol.getType();
                }

                if (!typeSymbol.equals(TypeSymbol.BOOL)) {
                    SymbolTable.error(iff.getCond().getToken().getLine()
                                    + ":" + (iff.getCond().getToken().getCharPositionInLine() + 1),
                            " If condition has type " + typeSymbol.getName() + " instead of Bool");
                    return null;
                }
            }
        }

        iff.getThenBranch().accept(this);
        iff.getElseBranch().accept(this);
        return null;
    }

    @Override
    public Void visit(AddSub sum) {
        sum.getLeft().accept(this);
        sum.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(Program prog) {
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
    public Void visit(MultDivNode prod) {
        prod.getLeft().accept(this);
        prod.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(BoolNode bool) {
        return null;
    }

    @Override
    public Void visit(FunctionCall call) {
        String originClass = null; // from what class do they come from
        TypeSymbol type1 = null;
        /*
        // type 1 (type of the assigned variable)
        if (calleeId != null) {
            if (currentScope instanceof FunctionSymbol) {
                var symbolId = (IdSymbol)((FunctionSymbol)currentScope).lookupParam(calleeId);
                if (symbolId != null) {
                    type1 = symbolId.getType();
                }
            }
        }

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

        System.out.println(SymbolTable.classesAndMethods);

        // now gotta find return type of the method
        var methods = SymbolTable.classesAndMethods.get(originClass);
        if (methods != null) {

        }

        */


        for (var arg : call.getParams()) {
            arg.accept(this);
        }
        var id = new Id(call.getName().getToken());
        id.setScope(currentScope);
        return null;
    }

    @Override
    public Void visit(CompareNode comp) {
        var left = comp.getLeft().accept(this);
        var right = comp.getRight().accept(this);
        return null;
    }

    @Override
    public Void visit(FloatNode flt) {
        return null;
    }

    @Override
    public Void visit(FuncDefNode func) {
        var id = func.getNameToken();
        var returnType = func.getRetTypeToken();
        var symbol = new FunctionSymbol(currentScope, id.getText());

        // Semnalăm eroare dacă există deja variabila în scope-ul curent.
        if (!currentScope.add(symbol)) {
            SymbolTable.error(func.getNameToken().getLine()
                            + ":" + (func.getNameToken().getCharPositionInLine() + 1),
                    " Class " + currentScope.toString()
                    + " redefines method " + symbol.getName());
            return null;
        }

        String className = currentScope.toString();

        if (!SymbolTable.classesAndMethods.containsKey(className)) {
            SymbolTable.classesAndMethods.put(className, new ArrayList<FuncDefNode>(Collections.singletonList(func)));
        } else {
            var list = SymbolTable.classesAndMethods.get(className);
            list.add(func);
            SymbolTable.classesAndMethods.put(className, list);
        }

        FunctionSymbol functionSymbol = new FunctionSymbol(currentScope, id.getText());
        currentScope = functionSymbol;

        var formals = func.getParams();
        for (var formal : formals) {
            IdSymbol idSymbol = new IdSymbol(formal.getNameToken().getText());
            TypeSymbol idType = TypeSymbol.getType(formal.getTypeToken().getText());
            if (idType == null) {
                idType = new TypeSymbol(formal.getTypeToken().getText());
            }
            idSymbol.setType(idType);
            ((FunctionSymbol) currentScope).addParam(idSymbol);

            formal.accept(this);
        }

        func.getBody().accept(this);

        currentScope = currentScope.getParent();

        return null;
    }

    @Override
    public Void visit(UnaryMinusNode minus) {
        minus.getNested().accept(this);
        return null;
    }

    @Override
    public Void visit(VarDef var) {
        Id varId = new Id(var.getNameToken());
        if (var.getType().equals(VarDefType.ATTRIBUTE)) {

            // list of classes and their attributes
            String className = currentScope.toString();
            if (!SymbolTable.classesAndVariables.containsKey(className)) {
                Pair<String, String> pair = new Pair<>(var.getNameToken().getText(), var.getTypeToken().getText());
                ArrayList<Pair<String, String>> list = new ArrayList<>(Arrays.asList(pair));
                SymbolTable.classesAndVariables.put(className, list);
            } else {
                ArrayList<Pair<String, String>> list = SymbolTable.classesAndVariables.get(className);
                list.add(new Pair<>(var.getNameToken().getText(), var.getTypeToken().getText()));
                SymbolTable.classesAndVariables.put(className, list);
            }

            // variable mustn't have self as name
            if (var.getNameToken().getText().equals("self")) {
                SymbolTable.error(var.getNameToken().getLine()
                                + ":" + (var.getNameToken().getCharPositionInLine() + 1),
                        " Class " + currentScope.toString() + " has attribute with illegal name self");
                return null;
            }

            // if attribute is redefined
            var symbol = new IdSymbol(var.getNameToken().getText());
            // Semnalăm eroare dacă există deja variabila în scope-ul curent.
            if (!currentScope.add(symbol)) {
                SymbolTable.error(var.getNameToken().getLine()
                                + ":" + (var.getNameToken().getCharPositionInLine() + 1),
                        " Class " + currentScope.toString() + " redefines attribute " + symbol.getName());
                return null;
            }

            // if type exists
            if (!SymbolTable.checkIfTypeExists(var.getTypeToken().getText())) {
                SymbolTable.error(var.getTypeToken().getLine()
                                + ":" + (var.getTypeToken().getCharPositionInLine() + 1),
                        " Class " + currentScope.toString() + " has attribute " + symbol.getName()
                                + " with undefined type " + var.getTypeToken().getText());
                return null;
            }
        } else if (var.getType().equals(VarDefType.FORMAL)) {
            // a formal cannot have be named self
            if (var.getNameToken().getText().equals("self")) {
                // Method self of class B has formal parameter with illegal name self
                SymbolTable.error(var.getNameToken().getLine()
                                + ":" + (var.getNameToken().getCharPositionInLine() + 1),
                        " Method " + currentScope.toString() + " of class " + currentScope.getParent().toString()
                        + " has formal parameter with illegal name self");
                return null;
            }

            // check if method is redefined
            var symbol = new IdSymbol(var.getNameToken().getText());
            if (!currentScope.add(symbol)) {
                SymbolTable.error(var.getNameToken().getLine()
                                + ":" + (var.getNameToken().getCharPositionInLine() + 1),
                        " Method " + currentScope.toString() + " of class " + currentScope.getParent().toString()
                        + " redefines formal parameter " + symbol.getName());
                return null;
            }

            // check if formal has type SELF_TYPE
            if (var.getTypeToken().getText().equals(TypeSymbol.SELF.getName())) {
                // Method self of class B has formal parameter with illegal name self
                SymbolTable.error(var.getTypeToken().getLine()
                                + ":" + (var.getTypeToken().getCharPositionInLine() + 1),
                        " Method " + currentScope.toString() + " of class " + currentScope.getParent().toString()
                        + " has formal parameter " + var.getNameToken().getText()
                        + " with illegal type SELF_TYPE");
                return null;
            }
        }

        varId.accept(this);
        if (var.getInitExpr() != null) {
            var.getInitExpr().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(AssignmentNode assignmentNode) {
        if (assignmentNode.getValue() instanceof FunctionCall) {
            calleeId = assignmentNode.getVarName().getText();
        }
        assignmentNode.getValue().accept(this);
        calleeId = null;
        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        var id = classNode.getName();
        var idType = new TypeSymbol(id.getText());

        if (idType.equals(TypeSymbol.BOOL)
                || idType.equals(TypeSymbol.INT)
                || idType.equals(TypeSymbol.STRING)) {
            SymbolTable.error(id.getLine()
                            + ":" + (id.getCharPositionInLine() + 1),
                    " Class " + id.getText() + " is redefined");
            return null;
        }

        if (idType.equals(TypeSymbol.SELF)) {
            SymbolTable.error(id.getLine()
                            + ":" + (id.getCharPositionInLine() + 1),
                    " Class has illegal name " + idType.name);
            return null;
        }

        ClassSymbol classSymbol = new ClassSymbol(currentScope, classNode.getName().getText());
        currentScope = classSymbol;

        Id classId = new Id(id);
        classId.setScope(currentScope);
        classId.setSymbol(classSymbol);

        // Semnalăm eroare dacă există deja variabila în scope-ul curent.
        if (!currentScope.getParent().add(classSymbol)) {
            SymbolTable.error(id.getLine()
                            + ":" + (id.getCharPositionInLine() + 1),
                    " Class " + id.getText() + " is redefined");
            return null;
        }

        if (classNode.getParent() != null) {
            SymbolTable.classesAndParents.put(classNode.getName().getText(), classNode.getParent().getText());
        } else {
            SymbolTable.classesAndParents.put(classNode.getName().getText(), null);
        }

        var definitions = classNode.getDefinitions();
        for (var def : definitions) {
            def.accept(this);
        }

        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(StringNode stringNode) {
        return null;
    }

    @Override
    public Void visit(WhileNode whileNode) {
        var condition = whileNode.getCondition().accept(this);
        whileNode.getBody().accept(this);

        if (whileNode.getCondition() instanceof Id) {
            if (currentScope instanceof FunctionSymbol) {
                String varName = whileNode.getCondition().getToken().getText();
                TypeSymbol typeSymbol = null;
                Symbol symbol = ((FunctionSymbol) currentScope).lookupParam(varName);
                if (symbol == null) {
                    var res = SymbolTable.classesAndVariables.get(currentScope.getParent().toString());
                    if (res != null) {
                        for (var pair : res) {
                            if (pair.getFirst().equals(varName)) {
                                typeSymbol = TypeSymbol.getType(pair.getSecond());
                                if (typeSymbol == null) {
                                    typeSymbol = new TypeSymbol(pair.getSecond());
                                }
                            }
                        }
                    } else {
                        IdSymbol idSymbol = (IdSymbol) symbol;
                        typeSymbol = idSymbol.getType();
                    }
                }

                if (!typeSymbol.equals(TypeSymbol.BOOL)) {
                    SymbolTable.error(whileNode.getCondition().getToken().getLine()
                                    + ":" + (whileNode.getCondition().getToken().getCharPositionInLine() + 1),
                            " While condition has type " + typeSymbol.getName() + " instead of Bool");
                    return null;
                }
            }
        }

        return null;
    }

    @Override
    public Void visit(VoidNode voidNode) {
        voidNode.getExpression().accept(this);
        return null;
    }

    @Override
    public Void visit(NewNode newNode) {
        return null;
    }

    @Override
    public Void visit(LetNode letNode) {
        LetSymbol letSymbol = new LetSymbol(currentScope, null);
        currentScope = letSymbol;

        var variables = letNode.getVariables();
        for (var variable : variables) {
            IdSymbol sym = new IdSymbol(variable.getName().getText());
            TypeSymbol typeSymbol = TypeSymbol.getType(variable.getType().getText());
            if (typeSymbol == null) {
                typeSymbol = new TypeSymbol(variable.getType().getText());
            }
            sym.setType(typeSymbol);
            ((LetSymbol) currentScope).addVar(sym);
            variable.accept(this);
        }
        letNode.getBody().accept(this);
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(ListVariables listVariables) {
        // check if let variables has name "self"
        if (listVariables.getName().getText().equals("self")) {
            SymbolTable.error(listVariables.getName().getLine()
                            + ":" + (listVariables.getName().getCharPositionInLine() + 1),
                    " Let variable has illegal name " + listVariables.getName().getText());
            return null;
        }
        if (listVariables.getExpression() != null) {
            listVariables.getExpression().accept(this);
        }
        var value = listVariables.getExpression();

        // check if it's variable and if yes, check if it's defined
        if (value instanceof Id && !TypeSymbol.isNumeric(value.getToken().getText())) {
            var symbol = (IdSymbol) currentScope.lookup(value.getToken().getText());
            if (symbol == null
                    && value.getToken().getText().equals(listVariables.getName().getText())) {
                SymbolTable.error(listVariables.getExpression().getToken().getLine()
                                + ":" + (listVariables.getExpression().getToken().getCharPositionInLine() + 1),
                        " Undefined identifier " + listVariables.getExpression().getToken().getText());
                return null;
            }
        }

        return null;
    }

    @Override
    public Void visit(CaseOfNode caseOfNode) {
        caseOfNode.getExpression().accept(this);

        CaseSymbol caseSymbol = new CaseSymbol(currentScope, caseOfNode.getExpression().getToken().getText());
        currentScope = caseSymbol;

        var branches = caseOfNode.getBranches();
        for (var branch : branches) {
            branch.accept(this);
        }

        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visit(Branch branch) {
        branch.getExpression().accept(this);

        // check if name is self
        if (branch.getName().getText().equals("self")) {
            SymbolTable.error(branch.getName().getLine()
                            + ":" + (branch.getName().getCharPositionInLine() + 1),
                    " Case variable has illegal name " + branch.getName().getText());
            return null;
        }

        // check if type is SELF_TYPE
        if (branch.getType().getText().equals(TypeSymbol.SELF.getName())) {
            SymbolTable.error(branch.getType().getLine()
                            + ":" + (branch.getType().getCharPositionInLine() + 1),
                    " Case variable " + branch.getName().getText()
                            + " has illegal type SELF_TYPE");
            return null;
        }

        return null;
    }

    @Override
    public Void visit(BlockNode blockNode) {
        var expressions = blockNode.getExpressions();
        for (var expression : expressions) {
            expression.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(ParanthesesNode paranthesesNode) {
        paranthesesNode.getExpression().accept(this);
        return null;
    }

    @Override
    public Void visit(NegationNode negationNode) {
        negationNode.getNested().accept(this);
        return null;
    }
}
