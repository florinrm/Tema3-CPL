package cool.compiler;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

public class ASTVisitor extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        var result = new Program();

        for (var def : ctx.classesProgram) {
            ClassNode d = (ClassNode) def.accept(this);
            result.addClass(d);
        }

        return result;
    }

    @Override
    public ASTNode visitLet_variables(CoolParser.Let_variablesContext ctx) {
        if (ctx.expr() == null) {
            return new ListVariables(ctx.declare_type().name,
                    ctx.declare_type().type, ctx.start);
        }
        return new ListVariables(ctx.start, ctx.declare_type().name,
                ctx.declare_type().type, (Expression) visit(ctx.expr()));

    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new Id(ctx.ID().getSymbol());
    }

    @Override
    public ASTNode visitClass_def(CoolParser.Class_defContext ctx) {
        //return super.visitClass_def(ctx);
        ClassNode node = new ClassNode(ctx.class_type,
                ctx.start);
        if (ctx.inheritedClass != null) {
            node.setParent(ctx.inheritedClass);
        }
        for (var def : ctx.definition()) {
            Definition d = (Definition) def.accept(this);
            node.addDefinition(d);
        }
        return node;
    }

    @Override
    public ASTNode visitFunctionDefinition(CoolParser.FunctionDefinitionContext ctx) {
        FuncDefNode node = new FuncDefNode(ctx.nameFunc, ctx.returnType,
                (Expression) visit(ctx.body), ctx.start);
        if (ctx.params != null) {
            var params = ctx.params;
            for (var param : params) {
                var def = new VarDef(param.name, param.type, ctx.start);
                def.setType(VarDefType.FORMAL);
                node.addParam(def);
            }
        }
        return node;
    }

    @Override
    public ASTNode visitVariableDefinition(CoolParser.VariableDefinitionContext ctx) {
        if (ctx.initVal == null)
            return new VarDef(ctx.declare_type().name, ctx.declare_type().type, ctx.start);
        return new VarDef(ctx.declare_type().name, ctx.declare_type().type,
                (Expression) visit(ctx.initVal), ctx.start);
    }

    @Override
    public ASTNode visitDeclare_type(CoolParser.Declare_typeContext ctx) {
        return super.visitDeclare_type(ctx);
    }

    @Override
    public ASTNode visitNegation(CoolParser.NegationContext ctx) {
        return new NegationNode((Expression) visit(ctx.expression), ctx.start);
    }

    @Override
    public ASTNode visitCompare(CoolParser.CompareContext ctx) {
        return new CompareNode((Expression) visit(ctx.leftBranch), ctx.op,
                (Expression) visit(ctx.rightBranch), ctx.start);
    }

    @Override
    public ASTNode visitVariableAssignment(CoolParser.VariableAssignmentContext ctx) {
        return new AssignmentNode(ctx.name, (Expression) visit(ctx.value), ctx.start);
    }

    @Override
    public ASTNode visitVoid(CoolParser.VoidContext ctx) {
        return new VoidNode(ctx.start, (Expression) visit(ctx.expression));
    }

    @Override
    public ASTNode visitString(CoolParser.StringContext ctx) {
        return new StringNode(ctx.STRING().getSymbol());
    }

    @Override
    public ASTNode visitMinusPlus(CoolParser.MinusPlusContext ctx) {
        if (ctx.PLUS() != null) {
            return new AddSub((Expression) visit(ctx.leftBranch), ctx.PLUS().getSymbol(),
                    (Expression) visit(ctx.rightBranch), ctx.start);
        } else {
            return new AddSub((Expression) visit(ctx.leftBranch), ctx.MINUS().getSymbol(),
                    (Expression) visit(ctx.rightBranch), ctx.start);
        }
    }

    @Override
    public ASTNode visitParantheses(CoolParser.ParanthesesContext ctx) {
        return new ParanthesesNode(ctx.start, (Expression) visit(ctx.expression));
    }

    @Override
    public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        return new WhileNode((Expression) visit(ctx.condition), (Expression) visit(ctx.body), ctx.start);
    }

    @Override
    public ASTNode visitBody(CoolParser.BodyContext ctx) {
        BlockNode node = new BlockNode(ctx.start);
        var expressions = ctx.expressions;
        for (var expr : expressions) {
            node.addExpression((Expression) expr.accept(this));
        }
        return node;
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new Int(ctx.INT().getSymbol());
    }

    @Override
    public ASTNode visitMulDiv(CoolParser.MulDivContext ctx) {
        if (ctx.DIV() != null) {
            return new MultDivNode((Expression) visit(ctx.leftBranch), ctx.DIV().getSymbol(),
                    (Expression) visit(ctx.rightBranch), ctx.start);
        } else {
            return new MultDivNode((Expression) visit(ctx.leftBranch), ctx.MUL().getSymbol(),
                    (Expression) visit(ctx.rightBranch), ctx.start);
        }
    }

    @Override
    public ASTNode visitBoolean(CoolParser.BooleanContext ctx) {
        return new BoolNode(ctx.BOOL().getSymbol());
    }

    @Override
    public ASTNode visitFunctionCall(CoolParser.FunctionCallContext ctx) {
        FunctionCall node = new FunctionCall((Expression) visit(ctx.name), ctx.start);
        for (var param : ctx.arguments) {
            node.addParam((Expression) param.accept(this));
        }
        return node;
    }

    @Override
    public ASTNode visitUpCastCall(CoolParser.UpCastCallContext ctx) {
        FunctionCall node = null;
        if (ctx.upcast != null) {
            node = new FunctionCall((Expression) visit(ctx.name),
                    (Expression) visit(ctx.expression), ctx.upcast, ctx.start);
        } else {
            node = new FunctionCall((Expression) visit(ctx.name),
                    (Expression) visit(ctx.expression), null, ctx.start);
        }

        for (var param : ctx.params) {
            node.addParam((Expression) param.accept(this));
        }

        return node;
    }

    @Override
    public ASTNode visitUnaryNegation(CoolParser.UnaryNegationContext ctx) {
        return new UnaryMinusNode((Expression) visit(ctx.expression), ctx.start);
    }

    @Override
    public ASTNode visitLet(CoolParser.LetContext ctx) {
        var variables = ctx.variables;
        LetNode node = new LetNode((Expression) visit(ctx.body), ctx.start);
        for (var variable : variables) {
            node.addVariable((ListVariables) variable.accept(this));
        }
        return node;
    }

    @Override
    public ASTNode visitIf(CoolParser.IfContext ctx) {
        // return super.visitIf(ctx);
        return new If((Expression) visit(ctx.condition),
                (Expression) visit(ctx.then_branch),
                (Expression) visit(ctx.else_branch),
                ctx.start);
    }

    @Override
    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        CaseOfNode node = new CaseOfNode((Expression) visit(ctx.expression), ctx.start);
        var branches = ctx.branches;
        for (var branch : branches) {
            node.addBranch((Branch) branch.accept(this));
        }
        return node;
    }

    @Override
    public ASTNode visitInstantiation(CoolParser.InstantiationContext ctx) {
        return new NewNode(ctx.start, ctx.TYPE_ID().getSymbol());
    }

    @Override
    public ASTNode visitBranch(CoolParser.BranchContext ctx) {
        return new Branch(ctx.start, ctx.declare_type().name,
                ctx.declare_type().type, (Expression) visit(ctx.body));
    }
}
