// Generated from C:/Users/Administrator/Desktop/CPL/Teme/Tema2/src/cool/lexer\CoolLexer.g4 by ANTLR 4.7.2
package cool.lexer;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "FI", "NEGATION", "NOT", "NEW", "CLASS", "INHERITS", 
			"LET", "IN", "SELFTYPE", "WHILE", "LOOP", "POOL", "CASE", "OF", "ESAC", 
			"ISVOID", "BOOL", "NULLCHAR", "UNTERMINATEDSTRING", "SPECIALCHARS", "STRING", 
			"TYPE", "UPPERLETTER", "LOWERCASE", "LETTER", "DIGIT", "INT", "TYPE_ID", 
			"ID", "DIGITS", "EXPONENT", "FLOAT", "DOT", "ANNOTATION", "SEMI", "COLON", 
			"COMMA", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", 
			"MUL", "DIV", "RESULTS", "EQUAL", "LT", "LE", "NEW_LINE", "LINE_COMMENT", 
			"BLOCK_COMMENT", "BLOCK_COMMENT_ERROR", "BLOCK_COMMENT_END_OF_LINE", 
			"WS", "CHAR_ERR"
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


	    int total_chars = 0;
	    final int charsLimit = 1024;
	    final String tooLong = "String constant too long";
	    final String unterminatedString = "Unterminated string constant";
	    final String eofInString = "EOF in string constant";
	    final String hasNullChars = "String contains null character";
	    final String eofComment = "EOF in comment";
	    final String unmatchedComment = "Unmatched *)";
	    final String invalidChar = "Invalid character: ";
	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 23:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 56:
			BLOCK_COMMENT_ERROR_action((RuleContext)_localctx, actionIndex);
			break;
		case 57:
			BLOCK_COMMENT_END_OF_LINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 59:
			CHAR_ERR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			                total_chars++;
			            
			break;
		case 1:

			                raiseError(hasNullChars);
			            
			break;
		case 2:
			 total_chars++; 
			break;
		case 3:

			                if(total_chars > charsLimit)
			                    raiseError(tooLong);
			                total_chars = 0;
			            
			break;
		case 4:

			                raiseError(unterminatedString);
			            
			break;
		case 5:

			                raiseError(eofInString);
			                total_chars = 0;
			            
			break;
		}
	}
	private void BLOCK_COMMENT_ERROR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 raiseError(unmatchedComment); 
			break;
		}
	}
	private void BLOCK_COMMENT_END_OF_LINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:

			            raiseError(eofComment);
			       
			break;
		}
	}
	private void CHAR_ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:

			    setCharPositionInLine(getCharPositionInLine() - 1);
			    raiseError(invalidChar + getText());

			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\63\u01c9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00e3\n\25\3\26\3\26"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\5\30\u00f6\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\7\31\u0105\n\31\f\31\16\31\u0108\13\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\5\31\u0111\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u0125\n\32"+
		"\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\6\37\u0130\n\37\r\37\16"+
		"\37\u0131\3 \3 \3 \3 \3 \3 \7 \u013a\n \f \16 \u013d\13 \5 \u013f\n \3"+
		"!\3!\3!\3!\7!\u0145\n!\f!\16!\u0148\13!\3\"\6\"\u014b\n\"\r\"\16\"\u014c"+
		"\3#\3#\5#\u0151\n#\3#\3#\3$\3$\3$\5$\u0158\n$\5$\u015a\n$\3$\3$\5$\u015e"+
		"\n$\3$\5$\u0161\n$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3*\3*\3+\3+\3,\3"+
		",\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\63\3\64"+
		"\3\64\3\65\3\65\3\66\3\66\3\66\3\67\5\67\u018b\n\67\3\67\3\67\38\38\3"+
		"8\38\78\u0193\n8\f8\168\u0196\138\38\38\58\u019a\n8\38\38\39\39\39\39"+
		"\39\79\u01a3\n9\f9\169\u01a6\139\39\39\39\39\39\3:\3:\3:\3:\3:\3;\3;\3"+
		";\3;\3;\3;\7;\u01b8\n;\f;\16;\u01bb\13;\3;\3;\3;\3<\6<\u01c1\n<\r<\16"+
		"<\u01c2\3<\3<\3=\3=\3=\6\u0106\u0194\u01a4\u01b9\2>\3\4\5\5\7\6\t\7\13"+
		"\b\r\t\17\n\21\13\23\f\25\r\27\16\31\2\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\2-\2/\2\61\27\63\2\65\2\67\29\2;\2=\30?\31A\32C\2E\2G\33I\34"+
		"K\35M\36O\37Q S!U\"W#Y$[%]&_\'a(c)e*g+i,k-m\2o.q/s\60u\61w\62y\63\3\2"+
		"\t\3\2C\\\3\2c|\4\2C\\c|\3\2\62;\4\2--//\3\2+,\5\2\13\f\17\17\"\"\2\u01e3"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2\61\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2"+
		"\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\3"+
		"{\3\2\2\2\5~\3\2\2\2\7\u0083\3\2\2\2\t\u0088\3\2\2\2\13\u008b\3\2\2\2"+
		"\r\u008d\3\2\2\2\17\u0091\3\2\2\2\21\u0095\3\2\2\2\23\u009b\3\2\2\2\25"+
		"\u00a4\3\2\2\2\27\u00a8\3\2\2\2\31\u00ab\3\2\2\2\33\u00b5\3\2\2\2\35\u00bb"+
		"\3\2\2\2\37\u00c0\3\2\2\2!\u00c5\3\2\2\2#\u00ca\3\2\2\2%\u00cd\3\2\2\2"+
		"\'\u00d2\3\2\2\2)\u00e2\3\2\2\2+\u00e4\3\2\2\2-\u00e6\3\2\2\2/\u00f5\3"+
		"\2\2\2\61\u00f7\3\2\2\2\63\u0124\3\2\2\2\65\u0126\3\2\2\2\67\u0128\3\2"+
		"\2\29\u012a\3\2\2\2;\u012c\3\2\2\2=\u012f\3\2\2\2?\u013e\3\2\2\2A\u0140"+
		"\3\2\2\2C\u014a\3\2\2\2E\u014e\3\2\2\2G\u015d\3\2\2\2I\u0162\3\2\2\2K"+
		"\u0164\3\2\2\2M\u0166\3\2\2\2O\u0168\3\2\2\2Q\u016a\3\2\2\2S\u016c\3\2"+
		"\2\2U\u016f\3\2\2\2W\u0171\3\2\2\2Y\u0173\3\2\2\2[\u0175\3\2\2\2]\u0177"+
		"\3\2\2\2_\u0179\3\2\2\2a\u017b\3\2\2\2c\u017d\3\2\2\2e\u017f\3\2\2\2g"+
		"\u0182\3\2\2\2i\u0184\3\2\2\2k\u0186\3\2\2\2m\u018a\3\2\2\2o\u018e\3\2"+
		"\2\2q\u019d\3\2\2\2s\u01ac\3\2\2\2u\u01b1\3\2\2\2w\u01c0\3\2\2\2y\u01c6"+
		"\3\2\2\2{|\7k\2\2|}\7h\2\2}\4\3\2\2\2~\177\7v\2\2\177\u0080\7j\2\2\u0080"+
		"\u0081\7g\2\2\u0081\u0082\7p\2\2\u0082\6\3\2\2\2\u0083\u0084\7g\2\2\u0084"+
		"\u0085\7n\2\2\u0085\u0086\7u\2\2\u0086\u0087\7g\2\2\u0087\b\3\2\2\2\u0088"+
		"\u0089\7h\2\2\u0089\u008a\7k\2\2\u008a\n\3\2\2\2\u008b\u008c\7\u0080\2"+
		"\2\u008c\f\3\2\2\2\u008d\u008e\7p\2\2\u008e\u008f\7q\2\2\u008f\u0090\7"+
		"v\2\2\u0090\16\3\2\2\2\u0091\u0092\7p\2\2\u0092\u0093\7g\2\2\u0093\u0094"+
		"\7y\2\2\u0094\20\3\2\2\2\u0095\u0096\7e\2\2\u0096\u0097\7n\2\2\u0097\u0098"+
		"\7c\2\2\u0098\u0099\7u\2\2\u0099\u009a\7u\2\2\u009a\22\3\2\2\2\u009b\u009c"+
		"\7k\2\2\u009c\u009d\7p\2\2\u009d\u009e\7j\2\2\u009e\u009f\7g\2\2\u009f"+
		"\u00a0\7t\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7v\2\2\u00a2\u00a3\7u\2\2"+
		"\u00a3\24\3\2\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7"+
		"v\2\2\u00a7\26\3\2\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa\7p\2\2\u00aa\30"+
		"\3\2\2\2\u00ab\u00ac\7U\2\2\u00ac\u00ad\7G\2\2\u00ad\u00ae\7N\2\2\u00ae"+
		"\u00af\7H\2\2\u00af\u00b0\7a\2\2\u00b0\u00b1\7V\2\2\u00b1\u00b2\7[\2\2"+
		"\u00b2\u00b3\7R\2\2\u00b3\u00b4\7G\2\2\u00b4\32\3\2\2\2\u00b5\u00b6\7"+
		"y\2\2\u00b6\u00b7\7j\2\2\u00b7\u00b8\7k\2\2\u00b8\u00b9\7n\2\2\u00b9\u00ba"+
		"\7g\2\2\u00ba\34\3\2\2\2\u00bb\u00bc\7n\2\2\u00bc\u00bd\7q\2\2\u00bd\u00be"+
		"\7q\2\2\u00be\u00bf\7r\2\2\u00bf\36\3\2\2\2\u00c0\u00c1\7r\2\2\u00c1\u00c2"+
		"\7q\2\2\u00c2\u00c3\7q\2\2\u00c3\u00c4\7n\2\2\u00c4 \3\2\2\2\u00c5\u00c6"+
		"\7e\2\2\u00c6\u00c7\7c\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7g\2\2\u00c9"+
		"\"\3\2\2\2\u00ca\u00cb\7q\2\2\u00cb\u00cc\7h\2\2\u00cc$\3\2\2\2\u00cd"+
		"\u00ce\7g\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d0\7c\2\2\u00d0\u00d1\7e\2\2"+
		"\u00d1&\3\2\2\2\u00d2\u00d3\7k\2\2\u00d3\u00d4\7u\2\2\u00d4\u00d5\7x\2"+
		"\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7k\2\2\u00d7\u00d8\7f\2\2\u00d8(\3\2"+
		"\2\2\u00d9\u00da\7v\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7w\2\2\u00dc\u00e3"+
		"\7g\2\2\u00dd\u00de\7h\2\2\u00de\u00df\7c\2\2\u00df\u00e0\7n\2\2\u00e0"+
		"\u00e1\7u\2\2\u00e1\u00e3\7g\2\2\u00e2\u00d9\3\2\2\2\u00e2\u00dd\3\2\2"+
		"\2\u00e3*\3\2\2\2\u00e4\u00e5\7\2\2\2\u00e5,\3\2\2\2\u00e6\u00e7\7\17"+
		"\2\2\u00e7\u00e8\7\f\2\2\u00e8.\3\2\2\2\u00e9\u00ea\7^\2\2\u00ea\u00f6"+
		"\7$\2\2\u00eb\u00ec\7^\2\2\u00ec\u00f6\7d\2\2\u00ed\u00ee\7^\2\2\u00ee"+
		"\u00f6\7v\2\2\u00ef\u00f0\7^\2\2\u00f0\u00f6\7p\2\2\u00f1\u00f2\7^\2\2"+
		"\u00f2\u00f6\7h\2\2\u00f3\u00f4\7^\2\2\u00f4\u00f6\7^\2\2\u00f5\u00e9"+
		"\3\2\2\2\u00f5\u00eb\3\2\2\2\u00f5\u00ed\3\2\2\2\u00f5\u00ef\3\2\2\2\u00f5"+
		"\u00f1\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\60\3\2\2\2\u00f7\u0106\7$\2\2"+
		"\u00f8\u00f9\5/\30\2\u00f9\u00fa\b\31\2\2\u00fa\u0105\3\2\2\2\u00fb\u00fc"+
		"\7^\2\2\u00fc\u00fd\7\17\2\2\u00fd\u0105\7\f\2\2\u00fe\u0105\7^\2\2\u00ff"+
		"\u0100\5+\26\2\u0100\u0101\b\31\3\2\u0101\u0105\3\2\2\2\u0102\u0103\13"+
		"\2\2\2\u0103\u0105\b\31\4\2\u0104\u00f8\3\2\2\2\u0104\u00fb\3\2\2\2\u0104"+
		"\u00fe\3\2\2\2\u0104\u00ff\3\2\2\2\u0104\u0102\3\2\2\2\u0105\u0108\3\2"+
		"\2\2\u0106\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0110\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0109\u010a\7$\2\2\u010a\u0111\b\31\5\2\u010b\u010c\5-"+
		"\27\2\u010c\u010d\b\31\6\2\u010d\u0111\3\2\2\2\u010e\u010f\7\2\2\3\u010f"+
		"\u0111\b\31\7\2\u0110\u0109\3\2\2\2\u0110\u010b\3\2\2\2\u0110\u010e\3"+
		"\2\2\2\u0111\62\3\2\2\2\u0112\u0113\7K\2\2\u0113\u0114\7p\2\2\u0114\u0125"+
		"\7v\2\2\u0115\u0116\7H\2\2\u0116\u0117\7n\2\2\u0117\u0118\7q\2\2\u0118"+
		"\u0119\7c\2\2\u0119\u0125\7v\2\2\u011a\u011b\7D\2\2\u011b\u011c\7q\2\2"+
		"\u011c\u011d\7q\2\2\u011d\u0125\7n\2\2\u011e\u011f\7U\2\2\u011f\u0120"+
		"\7v\2\2\u0120\u0121\7t\2\2\u0121\u0122\7k\2\2\u0122\u0123\7p\2\2\u0123"+
		"\u0125\7i\2\2\u0124\u0112\3\2\2\2\u0124\u0115\3\2\2\2\u0124\u011a\3\2"+
		"\2\2\u0124\u011e\3\2\2\2\u0125\64\3\2\2\2\u0126\u0127\t\2\2\2\u0127\66"+
		"\3\2\2\2\u0128\u0129\t\3\2\2\u01298\3\2\2\2\u012a\u012b\t\4\2\2\u012b"+
		":\3\2\2\2\u012c\u012d\t\5\2\2\u012d<\3\2\2\2\u012e\u0130\5;\36\2\u012f"+
		"\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2"+
		"\2\2\u0132>\3\2\2\2\u0133\u013f\5\63\32\2\u0134\u013f\5\31\r\2\u0135\u013b"+
		"\5\65\33\2\u0136\u013a\59\35\2\u0137\u013a\7a\2\2\u0138\u013a\5;\36\2"+
		"\u0139\u0136\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u0138\3\2\2\2\u013a\u013d"+
		"\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013f\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013e\u0133\3\2\2\2\u013e\u0134\3\2\2\2\u013e\u0135\3\2"+
		"\2\2\u013f@\3\2\2\2\u0140\u0146\5\67\34\2\u0141\u0145\59\35\2\u0142\u0145"+
		"\7a\2\2\u0143\u0145\5;\36\2\u0144\u0141\3\2\2\2\u0144\u0142\3\2\2\2\u0144"+
		"\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2\2\2\u0146\u0147\3\2"+
		"\2\2\u0147B\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014b\5;\36\2\u014a\u0149"+
		"\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"D\3\2\2\2\u014e\u0150\7g\2\2\u014f\u0151\t\6\2\2\u0150\u014f\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\5C\"\2\u0153F\3\2\2\2\u0154"+
		"\u0159\5C\"\2\u0155\u0157\7\60\2\2\u0156\u0158\5C\"\2\u0157\u0156\3\2"+
		"\2\2\u0157\u0158\3\2\2\2\u0158\u015a\3\2\2\2\u0159\u0155\3\2\2\2\u0159"+
		"\u015a\3\2\2\2\u015a\u015e\3\2\2\2\u015b\u015c\7\60\2\2\u015c\u015e\5"+
		"C\"\2\u015d\u0154\3\2\2\2\u015d\u015b\3\2\2\2\u015e\u0160\3\2\2\2\u015f"+
		"\u0161\5E#\2\u0160\u015f\3\2\2\2\u0160\u0161\3\2\2\2\u0161H\3\2\2\2\u0162"+
		"\u0163\7\60\2\2\u0163J\3\2\2\2\u0164\u0165\7B\2\2\u0165L\3\2\2\2\u0166"+
		"\u0167\7=\2\2\u0167N\3\2\2\2\u0168\u0169\7<\2\2\u0169P\3\2\2\2\u016a\u016b"+
		"\7.\2\2\u016bR\3\2\2\2\u016c\u016d\7>\2\2\u016d\u016e\7/\2\2\u016eT\3"+
		"\2\2\2\u016f\u0170\7*\2\2\u0170V\3\2\2\2\u0171\u0172\7+\2\2\u0172X\3\2"+
		"\2\2\u0173\u0174\7}\2\2\u0174Z\3\2\2\2\u0175\u0176\7\177\2\2\u0176\\\3"+
		"\2\2\2\u0177\u0178\7-\2\2\u0178^\3\2\2\2\u0179\u017a\7/\2\2\u017a`\3\2"+
		"\2\2\u017b\u017c\7,\2\2\u017cb\3\2\2\2\u017d\u017e\7\61\2\2\u017ed\3\2"+
		"\2\2\u017f\u0180\7?\2\2\u0180\u0181\7@\2\2\u0181f\3\2\2\2\u0182\u0183"+
		"\7?\2\2\u0183h\3\2\2\2\u0184\u0185\7>\2\2\u0185j\3\2\2\2\u0186\u0187\7"+
		">\2\2\u0187\u0188\7?\2\2\u0188l\3\2\2\2\u0189\u018b\7\17\2\2\u018a\u0189"+
		"\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\7\f\2\2\u018d"+
		"n\3\2\2\2\u018e\u018f\7/\2\2\u018f\u0190\7/\2\2\u0190\u0194\3\2\2\2\u0191"+
		"\u0193\13\2\2\2\u0192\u0191\3\2\2\2\u0193\u0196\3\2\2\2\u0194\u0195\3"+
		"\2\2\2\u0194\u0192\3\2\2\2\u0195\u0199\3\2\2\2\u0196\u0194\3\2\2\2\u0197"+
		"\u019a\5m\67\2\u0198\u019a\7\2\2\3\u0199\u0197\3\2\2\2\u0199\u0198\3\2"+
		"\2\2\u019a\u019b\3\2\2\2\u019b\u019c\b8\b\2\u019cp\3\2\2\2\u019d\u019e"+
		"\7*\2\2\u019e\u019f\7,\2\2\u019f\u01a4\3\2\2\2\u01a0\u01a3\5q9\2\u01a1"+
		"\u01a3\13\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3\u01a6\3"+
		"\2\2\2\u01a4\u01a5\3\2\2\2\u01a4\u01a2\3\2\2\2\u01a5\u01a7\3\2\2\2\u01a6"+
		"\u01a4\3\2\2\2\u01a7\u01a8\7,\2\2\u01a8\u01a9\7+\2\2\u01a9\u01aa\3\2\2"+
		"\2\u01aa\u01ab\b9\b\2\u01abr\3\2\2\2\u01ac\u01ad\7,\2\2\u01ad\u01ae\7"+
		"+\2\2\u01ae\u01af\3\2\2\2\u01af\u01b0\b:\t\2\u01b0t\3\2\2\2\u01b1\u01b2"+
		"\7*\2\2\u01b2\u01b3\7,\2\2\u01b3\u01b9\3\2\2\2\u01b4\u01b8\5q9\2\u01b5"+
		"\u01b6\n\7\2\2\u01b6\u01b8\13\2\2\2\u01b7\u01b4\3\2\2\2\u01b7\u01b5\3"+
		"\2\2\2\u01b8\u01bb\3\2\2\2\u01b9\u01ba\3\2\2\2\u01b9\u01b7\3\2\2\2\u01ba"+
		"\u01bc\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc\u01bd\7\2\2\3\u01bd\u01be\b;"+
		"\n\2\u01bev\3\2\2\2\u01bf\u01c1\t\b\2\2\u01c0\u01bf\3\2\2\2\u01c1\u01c2"+
		"\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2\2\2\u01c4"+
		"\u01c5\b<\b\2\u01c5x\3\2\2\2\u01c6\u01c7\13\2\2\2\u01c7\u01c8\b=\13\2"+
		"\u01c8z\3\2\2\2\35\2\u00e2\u00f5\u0104\u0106\u0110\u0124\u0131\u0139\u013b"+
		"\u013e\u0144\u0146\u014c\u0150\u0157\u0159\u015d\u0160\u018a\u0194\u0199"+
		"\u01a2\u01a4\u01b7\u01b9\u01c2\f\3\31\2\3\31\3\3\31\4\3\31\5\3\31\6\3"+
		"\31\7\b\2\2\3:\b\3;\t\3=\n";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}