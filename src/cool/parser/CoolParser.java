// Generated from C:/Users/Administrator/Desktop/CPL/Teme/Tema2/src/cool/parser\CoolParser.g4 by ANTLR 4.7.2
package cool.parser;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, IF=2, THEN=3, ELSE=4, FI=5, NEGATION=6, NOT=7, NEW=8, CLASS=9, 
		INHERITS=10, LET=11, IN=12, WHILE=13, LOOP=14, POOL=15, CASE=16, OF=17, 
		ESAC=18, ISVOID=19, BOOL=20, STRING=21, INT=22, TYPE_ID=23, ID=24, FLOAT=25, 
		DOT=26, ANNOTATION=27, SEMI=28, COLON=29, COMMA=30, ASSIGN=31, LPAREN=32, 
		RPAREN=33, LBRACE=34, RBRACE=35, PLUS=36, MINUS=37, MUL=38, DIV=39, RESULTS=40, 
		EQUAL=41, LT=42, LE=43, LINE_COMMENT=44, BLOCK_COMMENT=45, BLOCK_COMMENT_ERROR=46, 
		BLOCK_COMMENT_END_OF_LINE=47, WS=48, CHAR_ERR=49;
	public static final int
		RULE_program = 0, RULE_class_def = 1, RULE_definition = 2, RULE_declare_type = 3, 
		RULE_let_variables = 4, RULE_branch = 5, RULE_expr = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "class_def", "definition", "declare_type", "let_variables", 
			"branch", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'if'", "'then'", "'else'", "'fi'", "'~'", "'not'", "'new'", 
			"'class'", "'inherits'", "'let'", "'in'", "'while'", "'loop'", "'pool'", 
			"'case'", "'of'", "'esac'", "'isvoid'", null, null, null, null, null, 
			null, "'.'", "'@'", "';'", "':'", "','", "'<-'", "'('", "')'", "'{'", 
			"'}'", "'+'", "'-'", "'*'", "'/'", "'=>'", "'='", "'<'", "'<='", null, 
			null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "IF", "THEN", "ELSE", "FI", "NEGATION", "NOT", "NEW", 
			"CLASS", "INHERITS", "LET", "IN", "WHILE", "LOOP", "POOL", "CASE", "OF", 
			"ESAC", "ISVOID", "BOOL", "STRING", "INT", "TYPE_ID", "ID", "FLOAT", 
			"DOT", "ANNOTATION", "SEMI", "COLON", "COMMA", "ASSIGN", "LPAREN", "RPAREN", 
			"LBRACE", "RBRACE", "PLUS", "MINUS", "MUL", "DIV", "RESULTS", "EQUAL", 
			"LT", "LE", "LINE_COMMENT", "BLOCK_COMMENT", "BLOCK_COMMENT_ERROR", "BLOCK_COMMENT_END_OF_LINE", 
			"WS", "CHAR_ERR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public Class_defContext class_def;
		public List<Class_defContext> classesProgram = new ArrayList<Class_defContext>();
		public TerminalNode EOF() { return getToken(CoolParser.EOF, 0); }
		public List<TerminalNode> SEMI() { return getTokens(CoolParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CoolParser.SEMI, i);
		}
		public List<Class_defContext> class_def() {
			return getRuleContexts(Class_defContext.class);
		}
		public Class_defContext class_def(int i) {
			return getRuleContext(Class_defContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(14);
				((ProgramContext)_localctx).class_def = class_def();
				((ProgramContext)_localctx).classesProgram.add(((ProgramContext)_localctx).class_def);
				setState(15);
				match(SEMI);
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			setState(21);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Class_defContext extends ParserRuleContext {
		public Token class_type;
		public Token inheritedClass;
		public DefinitionContext definition;
		public List<DefinitionContext> definitions = new ArrayList<DefinitionContext>();
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> TYPE_ID() { return getTokens(CoolParser.TYPE_ID); }
		public TerminalNode TYPE_ID(int i) {
			return getToken(CoolParser.TYPE_ID, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public List<TerminalNode> SEMI() { return getTokens(CoolParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CoolParser.SEMI, i);
		}
		public List<DefinitionContext> definition() {
			return getRuleContexts(DefinitionContext.class);
		}
		public DefinitionContext definition(int i) {
			return getRuleContext(DefinitionContext.class,i);
		}
		public Class_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClass_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClass_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClass_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Class_defContext class_def() throws RecognitionException {
		Class_defContext _localctx = new Class_defContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_class_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(CLASS);
			setState(24);
			((Class_defContext)_localctx).class_type = match(TYPE_ID);
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(25);
				match(INHERITS);
				setState(26);
				((Class_defContext)_localctx).inheritedClass = match(TYPE_ID);
				}
			}

			setState(29);
			match(LBRACE);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(30);
				((Class_defContext)_localctx).definition = definition();
				((Class_defContext)_localctx).definitions.add(((Class_defContext)_localctx).definition);
				setState(31);
				match(SEMI);
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinitionContext extends ParserRuleContext {
		public DefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definition; }
	 
		public DefinitionContext() { }
		public void copyFrom(DefinitionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FunctionDefinitionContext extends DefinitionContext {
		public Token nameFunc;
		public Declare_typeContext declare_type;
		public List<Declare_typeContext> params = new ArrayList<Declare_typeContext>();
		public Token returnType;
		public ExprContext body;
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<Declare_typeContext> declare_type() {
			return getRuleContexts(Declare_typeContext.class);
		}
		public Declare_typeContext declare_type(int i) {
			return getRuleContext(Declare_typeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public FunctionDefinitionContext(DefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFunctionDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFunctionDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFunctionDefinition(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableDefinitionContext extends DefinitionContext {
		public ExprContext initVal;
		public Declare_typeContext declare_type() {
			return getRuleContext(Declare_typeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VariableDefinitionContext(DefinitionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterVariableDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitVariableDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitVariableDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinitionContext definition() throws RecognitionException {
		DefinitionContext _localctx = new DefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_definition);
		int _la;
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new FunctionDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				((FunctionDefinitionContext)_localctx).nameFunc = match(ID);
				setState(41);
				match(LPAREN);
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(42);
					((FunctionDefinitionContext)_localctx).declare_type = declare_type();
					((FunctionDefinitionContext)_localctx).params.add(((FunctionDefinitionContext)_localctx).declare_type);
					setState(47);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(43);
						match(COMMA);
						setState(44);
						((FunctionDefinitionContext)_localctx).declare_type = declare_type();
						((FunctionDefinitionContext)_localctx).params.add(((FunctionDefinitionContext)_localctx).declare_type);
						}
						}
						setState(49);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(52);
				match(RPAREN);
				setState(53);
				match(COLON);
				setState(54);
				((FunctionDefinitionContext)_localctx).returnType = match(TYPE_ID);
				setState(55);
				match(LBRACE);
				setState(56);
				((FunctionDefinitionContext)_localctx).body = expr(0);
				setState(57);
				match(RBRACE);
				}
				break;
			case 2:
				_localctx = new VariableDefinitionContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				declare_type();
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(60);
					match(ASSIGN);
					setState(61);
					((VariableDefinitionContext)_localctx).initVal = expr(0);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Declare_typeContext extends ParserRuleContext {
		public Token name;
		public Token type;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public Declare_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declare_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterDeclare_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitDeclare_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitDeclare_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Declare_typeContext declare_type() throws RecognitionException {
		Declare_typeContext _localctx = new Declare_typeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declare_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			((Declare_typeContext)_localctx).name = match(ID);
			setState(67);
			match(COLON);
			setState(68);
			((Declare_typeContext)_localctx).type = match(TYPE_ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Let_variablesContext extends ParserRuleContext {
		public Declare_typeContext declare_type() {
			return getRuleContext(Declare_typeContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Let_variablesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_let_variables; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLet_variables(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLet_variables(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLet_variables(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Let_variablesContext let_variables() throws RecognitionException {
		Let_variablesContext _localctx = new Let_variablesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_let_variables);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			declare_type();
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(71);
				match(ASSIGN);
				setState(72);
				expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BranchContext extends ParserRuleContext {
		public ExprContext body;
		public Declare_typeContext declare_type() {
			return getRuleContext(Declare_typeContext.class,0);
		}
		public TerminalNode RESULTS() { return getToken(CoolParser.RESULTS, 0); }
		public TerminalNode SEMI() { return getToken(CoolParser.SEMI, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchContext branch() throws RecognitionException {
		BranchContext _localctx = new BranchContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_branch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			declare_type();
			setState(76);
			match(RESULTS);
			setState(77);
			((BranchContext)_localctx).body = expr(0);
			setState(78);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NegationContext extends ExprContext {
		public ExprContext expression;
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NegationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompareContext extends ExprContext {
		public ExprContext leftBranch;
		public Token op;
		public ExprContext rightBranch;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LE() { return getToken(CoolParser.LE, 0); }
		public TerminalNode LT() { return getToken(CoolParser.LT, 0); }
		public TerminalNode EQUAL() { return getToken(CoolParser.EQUAL, 0); }
		public CompareContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCompare(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCompare(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCompare(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VariableAssignmentContext extends ExprContext {
		public Token name;
		public ExprContext value;
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VariableAssignmentContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterVariableAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitVariableAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitVariableAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VoidContext extends ExprContext {
		public ExprContext expression;
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public VoidContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterVoid(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitVoid(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitVoid(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringContext extends ExprContext {
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public StringContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MinusPlusContext extends ExprContext {
		public ExprContext leftBranch;
		public Token op;
		public ExprContext rightBranch;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public MinusPlusContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMinusPlus(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMinusPlus(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMinusPlus(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParanthesesContext extends ExprContext {
		public ExprContext expression;
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParanthesesContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterParantheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitParantheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitParantheses(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileContext extends ExprContext {
		public ExprContext condition;
		public ExprContext body;
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhileContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BodyContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> expressions = new ArrayList<ExprContext>();
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> SEMI() { return getTokens(CoolParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CoolParser.SEMI, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BodyContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ExprContext {
		public TerminalNode INT() { return getToken(CoolParser.INT, 0); }
		public IntContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MulDivContext extends ExprContext {
		public ExprContext leftBranch;
		public Token op;
		public ExprContext rightBranch;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(CoolParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(CoolParser.DIV, 0); }
		public MulDivContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanContext extends ExprContext {
		public TerminalNode BOOL() { return getToken(CoolParser.BOOL, 0); }
		public BooleanContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UpCastCallContext extends ExprContext {
		public ExprContext expression;
		public Token upcast;
		public ExprContext name;
		public ExprContext expr;
		public List<ExprContext> params = new ArrayList<ExprContext>();
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ANNOTATION() { return getToken(CoolParser.ANNOTATION, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public UpCastCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterUpCastCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitUpCastCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitUpCastCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionCallContext extends ExprContext {
		public ExprContext name;
		public ExprContext expr;
		public List<ExprContext> arguments = new ArrayList<ExprContext>();
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public FunctionCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryNegationContext extends ExprContext {
		public ExprContext expression;
		public TerminalNode NEGATION() { return getToken(CoolParser.NEGATION, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public UnaryNegationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterUnaryNegation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitUnaryNegation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitUnaryNegation(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LetContext extends ExprContext {
		public Let_variablesContext let_variables;
		public List<Let_variablesContext> variables = new ArrayList<Let_variablesContext>();
		public ExprContext body;
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public List<Let_variablesContext> let_variables() {
			return getRuleContexts(Let_variablesContext.class);
		}
		public Let_variablesContext let_variables(int i) {
			return getRuleContext(Let_variablesContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public LetContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLet(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdContext extends ExprContext {
		public TerminalNode ID() { return getToken(CoolParser.ID, 0); }
		public IdContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfContext extends ExprContext {
		public ExprContext condition;
		public ExprContext then_branch;
		public ExprContext else_branch;
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CaseContext extends ExprContext {
		public ExprContext expression;
		public BranchContext branch;
		public List<BranchContext> branches = new ArrayList<BranchContext>();
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<BranchContext> branch() {
			return getRuleContexts(BranchContext.class);
		}
		public BranchContext branch(int i) {
			return getRuleContext(BranchContext.class,i);
		}
		public CaseContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCase(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InstantiationContext extends ExprContext {
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPE_ID() { return getToken(CoolParser.TYPE_ID, 0); }
		public InstantiationContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterInstantiation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitInstantiation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitInstantiation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

    public static final String _serializedATN =
            "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\63\u00c6\4\2\t\2" +
                    "\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\3\2\3\2\6\2\24\n" +
                    "\2\r\2\16\2\25\3\2\3\2\3\3\3\3\3\3\3\3\5\3\36\n\3\3\3\3\3\3\3\3\3\7\3" +
                    "$\n\3\f\3\16\3\'\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4" +
                    "\63\13\4\5\4\65\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4A\n\4\5" +
                    "\4C\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6L\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3" +
                    "\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b" +
                    "\6\bf\n\b\r\b\16\bg\3\b\3\b\3\b\3\b\3\b\3\b\7\bp\n\b\f\b\16\bs\13\b\3" +
                    "\b\3\b\3\b\3\b\3\b\3\b\3\b\6\b|\n\b\r\b\16\b}\3\b\3\b\3\b\3\b\3\b\3\b" +
                    "\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0095" +
                    "\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a3\n\b\3\b" +
                    "\3\b\3\b\3\b\3\b\3\b\7\b\u00ab\n\b\f\b\16\b\u00ae\13\b\5\b\u00b0\n\b\3" +
                    "\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u00b9\n\b\f\b\16\b\u00bc\13\b\5\b\u00be" +
                    "\n\b\3\b\7\b\u00c1\n\b\f\b\16\b\u00c4\13\b\3\b\2\3\16\t\2\4\6\b\n\f\16" +
                    "\2\5\3\2()\3\2&\'\3\2+-\2\u00e1\2\23\3\2\2\2\4\31\3\2\2\2\6B\3\2\2\2\b" +
                    "D\3\2\2\2\nH\3\2\2\2\fM\3\2\2\2\16\u0094\3\2\2\2\20\21\5\4\3\2\21\22\7" +
                    "\36\2\2\22\24\3\2\2\2\23\20\3\2\2\2\24\25\3\2\2\2\25\23\3\2\2\2\25\26" +
                    "\3\2\2\2\26\27\3\2\2\2\27\30\7\2\2\3\30\3\3\2\2\2\31\32\7\13\2\2\32\35" +
                    "\7\31\2\2\33\34\7\f\2\2\34\36\7\31\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36" +
                    "\37\3\2\2\2\37%\7$\2\2 !\5\6\4\2!\"\7\36\2\2\"$\3\2\2\2# \3\2\2\2$\'\3" +
                    "\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2\2\2\'%\3\2\2\2()\7%\2\2)\5\3\2\2\2*+" +
                    "\7\32\2\2+\64\7\"\2\2,\61\5\b\5\2-.\7 \2\2.\60\5\b\5\2/-\3\2\2\2\60\63" +
                    "\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\64,\3\2" +
                    "\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\7#\2\2\678\7\37\2\289\7\31\2\2" +
                    "9:\7$\2\2:;\5\16\b\2;<\7%\2\2<C\3\2\2\2=@\5\b\5\2>?\7!\2\2?A\5\16\b\2" +
                    "@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B*\3\2\2\2B=\3\2\2\2C\7\3\2\2\2DE\7\32\2" +
                    "\2EF\7\37\2\2FG\7\31\2\2G\t\3\2\2\2HK\5\b\5\2IJ\7!\2\2JL\5\16\b\2KI\3" +
                    "\2\2\2KL\3\2\2\2L\13\3\2\2\2MN\5\b\5\2NO\7*\2\2OP\5\16\b\2PQ\7\36\2\2" +
                    "Q\r\3\2\2\2RS\b\b\1\2ST\7\4\2\2TU\5\16\b\2UV\7\5\2\2VW\5\16\b\2WX\7\6" +
                    "\2\2XY\5\16\b\2YZ\7\7\2\2Z\u0095\3\2\2\2[\\\7\17\2\2\\]\5\16\b\2]^\7\20" +
                    "\2\2^_\5\16\b\2_`\7\21\2\2`\u0095\3\2\2\2ae\7$\2\2bc\5\16\b\2cd\7\36\2" +
                    "\2df\3\2\2\2eb\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\7%\2" +
                    "\2j\u0095\3\2\2\2kl\7\r\2\2lq\5\n\6\2mn\7 \2\2np\5\n\6\2om\3\2\2\2ps\3" +
                    "\2\2\2qo\3\2\2\2qr\3\2\2\2rt\3\2\2\2sq\3\2\2\2tu\7\16\2\2uv\5\16\b\21" +
                    "v\u0095\3\2\2\2wx\7\22\2\2xy\5\16\b\2y{\7\23\2\2z|\5\f\7\2{z\3\2\2\2|" +
                    "}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\7\24\2\2\u0080\u0095" +
                    "\3\2\2\2\u0081\u0082\7\n\2\2\u0082\u0095\7\31\2\2\u0083\u0084\7\b\2\2" +
                    "\u0084\u0095\5\16\b\16\u0085\u0086\7\32\2\2\u0086\u0087\7!\2\2\u0087\u0095" +
                    "\5\16\b\13\u0088\u0089\7\25\2\2\u0089\u0095\5\16\b\n\u008a\u008b\7\t\2" +
                    "\2\u008b\u0095\5\16\b\b\u008c\u008d\7\"\2\2\u008d\u008e\5\16\b\2\u008e" +
                    "\u008f\7#\2\2\u008f\u0095\3\2\2\2\u0090\u0095\7\32\2\2\u0091\u0095\7\26" +
                    "\2\2\u0092\u0095\7\27\2\2\u0093\u0095\7\30\2\2\u0094R\3\2\2\2\u0094[\3" +
                    "\2\2\2\u0094a\3\2\2\2\u0094k\3\2\2\2\u0094w\3\2\2\2\u0094\u0081\3\2\2" +
                    "\2\u0094\u0083\3\2\2\2\u0094\u0085\3\2\2\2\u0094\u0088\3\2\2\2\u0094\u008a" +
                    "\3\2\2\2\u0094\u008c\3\2\2\2\u0094\u0090\3\2\2\2\u0094\u0091\3\2\2\2\u0094" +
                    "\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u00c2\3\2\2\2\u0096\u0097\f\r" +
                    "\2\2\u0097\u0098\t\2\2\2\u0098\u00c1\5\16\b\16\u0099\u009a\f\f\2\2\u009a" +
                    "\u009b\t\3\2\2\u009b\u00c1\5\16\b\r\u009c\u009d\f\t\2\2\u009d\u009e\t" +
                    "\4\2\2\u009e\u00c1\5\16\b\n\u009f\u00a2\f\26\2\2\u00a0\u00a1\7\35\2\2" +
                    "\u00a1\u00a3\7\31\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4" +
                    "\3\2\2\2\u00a4\u00a5\7\34\2\2\u00a5\u00a6\5\16\b\2\u00a6\u00af\7\"\2\2" +
                    "\u00a7\u00ac\5\16\b\2\u00a8\u00a9\7 \2\2\u00a9\u00ab\5\16\b\2\u00aa\u00a8" +
                    "\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad" +
                    "\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00a7\3\2\2\2\u00af\u00b0\3\2" +
                    "\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\7#\2\2\u00b2\u00c1\3\2\2\2\u00b3" +
                    "\u00b4\f\25\2\2\u00b4\u00bd\7\"\2\2\u00b5\u00ba\5\16\b\2\u00b6\u00b7\7" +
                    " \2\2\u00b7\u00b9\5\16\b\2\u00b8\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba" +
                    "\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2" +
                    "\2\2\u00bd\u00b5\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf" +
                    "\u00c1\7#\2\2\u00c0\u0096\3\2\2\2\u00c0\u0099\3\2\2\2\u00c0\u009c\3\2" +
                    "\2\2\u00c0\u009f\3\2\2\2\u00c0\u00b3\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2" +
                    "\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\17\3\2\2\2\u00c4\u00c2\3\2\2" +
                    "\2\25\25\35%\61\64@BKgq}\u0094\u00a2\u00ac\u00af\u00ba\u00bd\u00c0\u00c2";

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 6:
                return expr_sempred((ExprContext) _localctx, predIndex);
        }
        return true;
    }

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new IfContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(81);
				match(IF);
				setState(82);
				((IfContext)_localctx).condition = expr(0);
				setState(83);
				match(THEN);
				setState(84);
				((IfContext)_localctx).then_branch = expr(0);
				setState(85);
				match(ELSE);
				setState(86);
				((IfContext)_localctx).else_branch = expr(0);
				setState(87);
				match(FI);
				}
				break;
			case 2:
				{
				_localctx = new WhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(89);
				match(WHILE);
				setState(90);
				((WhileContext)_localctx).condition = expr(0);
				setState(91);
				match(LOOP);
				setState(92);
				((WhileContext)_localctx).body = expr(0);
				setState(93);
				match(POOL);
				}
				break;
			case 3:
				{
				_localctx = new BodyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(95);
				match(LBRACE);
                    setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(96);
					((BodyContext)_localctx).expr = expr(0);
					((BodyContext)_localctx).expressions.add(((BodyContext)_localctx).expr);
					setState(97);
					match(SEMI);
					}
					}
                    setState(101);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << NEGATION) | (1L << NOT) | (1L << NEW) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << ISVOID) | (1L << BOOL) | (1L << STRING) | (1L << INT) | (1L << ID) | (1L << LPAREN) | (1L << LBRACE))) != 0) );
				setState(103);
				match(RBRACE);
				}
				break;
			case 4:
				{
				_localctx = new LetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(105);
				match(LET);
				setState(106);
				((LetContext)_localctx).let_variables = let_variables();
				((LetContext)_localctx).variables.add(((LetContext)_localctx).let_variables);
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(107);
					match(COMMA);
					setState(108);
					((LetContext)_localctx).let_variables = let_variables();
					((LetContext)_localctx).variables.add(((LetContext)_localctx).let_variables);
					}
					}
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(114);
				match(IN);
				setState(115);
				((LetContext)_localctx).body = expr(15);
				}
				break;
			case 5:
				{
				_localctx = new CaseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(117);
				match(CASE);
				setState(118);
				((CaseContext)_localctx).expression = expr(0);
				setState(119);
				match(OF);
                    setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120);
					((CaseContext)_localctx).branch = branch();
					((CaseContext)_localctx).branches.add(((CaseContext)_localctx).branch);
					}
					}
                    setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ID );
				setState(125);
				match(ESAC);
				}
				break;
			case 6:
				{
				_localctx = new InstantiationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(127);
				match(NEW);
				setState(128);
				match(TYPE_ID);
				}
				break;
			case 7:
				{
                    _localctx = new UnaryNegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
                    match(NEGATION);
				setState(130);
                    ((UnaryNegationContext) _localctx).expression = expr(12);
				}
				break;
			case 8:
				{
                    _localctx = new VariableAssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
                    setState(131);
                    ((VariableAssignmentContext) _localctx).name = match(ID);
				setState(132);
                    match(ASSIGN);
				setState(133);
                    ((VariableAssignmentContext) _localctx).value = expr(9);
				}
				break;
			case 9:
				{
				_localctx = new VoidContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(ISVOID);
				setState(135);
				((VoidContext)_localctx).expression = expr(8);
				}
				break;
			case 10:
				{
				_localctx = new NegationContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(NOT);
				setState(137);
				((NegationContext)_localctx).expression = expr(6);
				}
				break;
			case 11:
				{
				_localctx = new ParanthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(LPAREN);
				setState(139);
				((ParanthesesContext)_localctx).expression = expr(0);
				setState(140);
				match(RPAREN);
				}
				break;
			case 12:
				{
				_localctx = new IdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				match(ID);
				}
				break;
			case 13:
				{
				_localctx = new BooleanContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(BOOL);
				}
				break;
			case 14:
				{
				_localctx = new StringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(STRING);
				}
				break;
			case 15:
				{
				_localctx = new IntContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(INT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(192);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(190);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new MulDivContext(new ExprContext(_parentctx, _parentState));
						((MulDivContext)_localctx).leftBranch = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(148);
                            if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(149);
						((MulDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(150);
                            ((MulDivContext) _localctx).rightBranch = expr(12);
						}
						break;
					case 2:
						{
						_localctx = new MinusPlusContext(new ExprContext(_parentctx, _parentState));
						((MinusPlusContext)_localctx).leftBranch = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(151);
                            if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(152);
						((MinusPlusContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((MinusPlusContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(153);
                            ((MinusPlusContext) _localctx).rightBranch = expr(11);
						}
						break;
					case 3:
						{
						_localctx = new CompareContext(new ExprContext(_parentctx, _parentState));
						((CompareContext)_localctx).leftBranch = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(154);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(155);
						((CompareContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQUAL) | (1L << LT) | (1L << LE))) != 0)) ) {
							((CompareContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(156);
						((CompareContext)_localctx).rightBranch = expr(8);
						}
						break;
					case 4:
						{
						_localctx = new UpCastCallContext(new ExprContext(_parentctx, _parentState));
						((UpCastCallContext)_localctx).expression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(157);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(160);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ANNOTATION) {
							{
							setState(158);
							match(ANNOTATION);
							setState(159);
							((UpCastCallContext)_localctx).upcast = match(TYPE_ID);
							}
						}

						setState(162);
						match(DOT);
						setState(163);
						((UpCastCallContext)_localctx).name = expr(0);
						setState(164);
						match(LPAREN);
						setState(173);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << NEGATION) | (1L << NOT) | (1L << NEW) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << ISVOID) | (1L << BOOL) | (1L << STRING) | (1L << INT) | (1L << ID) | (1L << LPAREN) | (1L << LBRACE))) != 0)) {
							{
							setState(165);
							((UpCastCallContext)_localctx).expr = expr(0);
							((UpCastCallContext)_localctx).params.add(((UpCastCallContext)_localctx).expr);
							setState(170);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(166);
								match(COMMA);
								setState(167);
								((UpCastCallContext)_localctx).expr = expr(0);
								((UpCastCallContext)_localctx).params.add(((UpCastCallContext)_localctx).expr);
								}
								}
								setState(172);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(175);
						match(RPAREN);
						}
						break;
					case 5:
						{
						_localctx = new FunctionCallContext(new ExprContext(_parentctx, _parentState));
						((FunctionCallContext)_localctx).name = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(177);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(178);
						match(LPAREN);
						setState(187);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IF) | (1L << NEGATION) | (1L << NOT) | (1L << NEW) | (1L << LET) | (1L << WHILE) | (1L << CASE) | (1L << ISVOID) | (1L << BOOL) | (1L << STRING) | (1L << INT) | (1L << ID) | (1L << LPAREN) | (1L << LBRACE))) != 0)) {
							{
							setState(179);
							((FunctionCallContext)_localctx).expr = expr(0);
							((FunctionCallContext)_localctx).arguments.add(((FunctionCallContext)_localctx).expr);
							setState(184);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(180);
								match(COMMA);
								setState(181);
								((FunctionCallContext)_localctx).expr = expr(0);
								((FunctionCallContext)_localctx).arguments.add(((FunctionCallContext)_localctx).expr);
								}
								}
								setState(186);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(189);
						match(RPAREN);
						}
						break;
					}
                    }
				}
				setState(194);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
            case 1:
                return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 20);
		case 4:
			return precpred(_ctx, 19);
		}
		return true;
	}
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}