// Generated from C:/Users/Administrator/Desktop/CPL/Teme/Tema2/src/cool/parser\CoolParser.g4 by ANTLR 4.7.2
package cool.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoolParser}.
 */
public interface CoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#class_def}.
	 * @param ctx the parse tree
	 */
	void enterClass_def(CoolParser.Class_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#class_def}.
	 * @param ctx the parse tree
	 */
	void exitClass_def(CoolParser.Class_defContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionDefinition}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(CoolParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionDefinition}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(CoolParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableDefinition}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterVariableDefinition(CoolParser.VariableDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableDefinition}
	 * labeled alternative in {@link CoolParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitVariableDefinition(CoolParser.VariableDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#declare_type}.
	 * @param ctx the parse tree
	 */
	void enterDeclare_type(CoolParser.Declare_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#declare_type}.
	 * @param ctx the parse tree
	 */
	void exitDeclare_type(CoolParser.Declare_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#let_variables}.
	 * @param ctx the parse tree
	 */
	void enterLet_variables(CoolParser.Let_variablesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#let_variables}.
	 * @param ctx the parse tree
	 */
	void exitLet_variables(CoolParser.Let_variablesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(CoolParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(CoolParser.BranchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negation}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegation(CoolParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negation}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegation(CoolParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompare(CoolParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compare}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompare(CoolParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variableAssignment}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssignment(CoolParser.VariableAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variableAssignment}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssignment(CoolParser.VariableAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code void}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVoid(CoolParser.VoidContext ctx);
	/**
	 * Exit a parse tree produced by the {@code void}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVoid(CoolParser.VoidContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterString(CoolParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitString(CoolParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code minusPlus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMinusPlus(CoolParser.MinusPlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code minusPlus}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMinusPlus(CoolParser.MinusPlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parantheses}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParantheses(CoolParser.ParanthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parantheses}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParantheses(CoolParser.ParanthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhile(CoolParser.WhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code while}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhile(CoolParser.WhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code body}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBody(CoolParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code body}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBody(CoolParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(CoolParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(CoolParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(CoolParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDiv}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(CoolParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(CoolParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(CoolParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code upCastCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUpCastCall(CoolParser.UpCastCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code upCastCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUpCastCall(CoolParser.UpCastCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(CoolParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(CoolParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryNegation}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryNegation(CoolParser.UnaryNegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryNegation}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryNegation(CoolParser.UnaryNegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLet(CoolParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code let}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLet(CoolParser.LetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(CoolParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(CoolParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIf(CoolParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIf(CoolParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCase(CoolParser.CaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code case}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCase(CoolParser.CaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code instantiation}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInstantiation(CoolParser.InstantiationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code instantiation}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInstantiation(CoolParser.InstantiationContext ctx);
}