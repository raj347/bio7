// Generated from R.g4 by ANTLR 4.5.1
package com.eco.bio7.reditor.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, HEX=61, INT=62, FLOAT=63, COMPLEX=64, STRING=65, ID=66, USER_OP=67, 
		NL=68, WS=69;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
		"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
		"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
		"T__41", "T__42", "T__43", "T__44", "T__45", "T__46", "T__47", "T__48", 
		"T__49", "T__50", "T__51", "T__52", "T__53", "T__54", "T__55", "T__56", 
		"T__57", "T__58", "T__59", "HEX", "INT", "HEXDIGIT", "FLOAT", "DIGIT", 
		"EXP", "COMPLEX", "STRING", "ESC", "UNICODE_ESCAPE", "OCTAL_ESCAPE", "HEX_ESCAPE", 
		"ID", "LETTER", "USER_OP", "COMMENT", "NL", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'[['", "']'", "'['", "'::'", "':::'", "'$'", "'@'", "'^'", 
		"'-'", "'+'", "':'", "'*'", "'/'", "'>'", "'>='", "'<'", "'<='", "'=='", 
		"'!='", "'!'", "'&'", "'&&'", "'|'", "'||'", "'~'", "'<-'", "'<<-'", "'='", 
		"'->'", "'->>'", "':='", "'{'", "'}'", "'function'", "'('", "')'", "'if'", 
		"'else'", "'for'", "'in'", "'while'", "'repeat'", "'?'", "'next'", "'break'", 
		"'NULL'", "'NA'", "'Inf'", "'NaN'", "'TRUE'", "'FALSE'", "'true'", "'false'", 
		"'null'", "'na'", "'=>'", "'=<'", "','", "'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "HEX", "INT", "FLOAT", "COMPLEX", "STRING", "ID", "USER_OP", "NL", 
		"WS"
	};
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


	public RLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "R.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2G\u0232\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35"+
		"\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3\"\3\"\3#\3"+
		"#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3(\3(\3(\3(\3"+
		")\3)\3)\3)\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3-\3-\3.\3"+
		".\3.\3.\3.\3/\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3"+
		"\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\65\3"+
		"\65\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66\3\67\3\67\3\67\3\67\3"+
		"\67\3\67\38\38\38\38\38\39\39\39\3:\3:\3:\3;\3;\3;\3<\3<\3=\3=\3=\3=\3"+
		">\3>\3>\6>\u0168\n>\r>\16>\u0169\3>\5>\u016d\n>\3?\6?\u0170\n?\r?\16?"+
		"\u0171\3?\5?\u0175\n?\3@\3@\3A\6A\u017a\nA\rA\16A\u017b\3A\3A\7A\u0180"+
		"\nA\fA\16A\u0183\13A\3A\5A\u0186\nA\3A\5A\u0189\nA\3A\6A\u018c\nA\rA\16"+
		"A\u018d\3A\5A\u0191\nA\3A\5A\u0194\nA\3A\3A\6A\u0198\nA\rA\16A\u0199\3"+
		"A\5A\u019d\nA\3A\5A\u01a0\nA\5A\u01a2\nA\3B\3B\3C\3C\5C\u01a8\nC\3C\3"+
		"C\3D\3D\3D\3D\3D\3D\5D\u01b2\nD\3E\3E\3E\7E\u01b7\nE\fE\16E\u01ba\13E"+
		"\3E\3E\3E\3E\7E\u01c0\nE\fE\16E\u01c3\13E\3E\3E\3E\3E\7E\u01c9\nE\fE\16"+
		"E\u01cc\13E\3E\5E\u01cf\nE\3F\3F\3F\3F\3F\5F\u01d6\nF\3G\3G\3G\3G\3G\3"+
		"G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u01e8\nG\3H\3H\3H\3H\3H\3H\3H\3H\3"+
		"H\5H\u01f3\nH\3I\3I\3I\5I\u01f8\nI\3J\3J\3J\5J\u01fd\nJ\3J\3J\3J\7J\u0202"+
		"\nJ\fJ\16J\u0205\13J\3J\3J\3J\3J\7J\u020b\nJ\fJ\16J\u020e\13J\3J\5J\u0211"+
		"\nJ\3K\3K\3L\3L\7L\u0217\nL\fL\16L\u021a\13L\3L\3L\3M\3M\7M\u0220\nM\f"+
		"M\16M\u0223\13M\3M\3M\3N\5N\u0228\nN\3N\3N\3O\6O\u022d\nO\rO\16O\u022e"+
		"\3O\3O\6\u01b8\u01c1\u01ca\u0218\2P\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\60_\61a\62c\63e\64g\65i\66k\67m8o9q:s;u<w=y>{?}@\177\2\u0081A\u0083"+
		"\2\u0085\2\u0087B\u0089C\u008b\2\u008d\2\u008f\2\u0091\2\u0093D\u0095"+
		"\2\u0097E\u0099\2\u009bF\u009dG\3\2\20\4\2ZZzz\4\2NNnn\5\2\62;CHch\4\2"+
		"GGgg\4\2--//\4\2$$^^\4\2))^^\13\2$$))^^cdhhppttvvxx\3\2\62\65\3\2\629"+
		"\4\2\60\60aa\4\2C\\c|\4\2\f\f\17\17\4\2\13\13\"\"\u0257\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2"+
		"\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2"+
		"K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3"+
		"\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2"+
		"\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2"+
		"q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3"+
		"\2\2\2\2\u0081\3\2\2\2\2\u0087\3\2\2\2\2\u0089\3\2\2\2\2\u0093\3\2\2\2"+
		"\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\3\u009f"+
		"\3\2\2\2\5\u00a1\3\2\2\2\7\u00a4\3\2\2\2\t\u00a6\3\2\2\2\13\u00a8\3\2"+
		"\2\2\r\u00ab\3\2\2\2\17\u00af\3\2\2\2\21\u00b1\3\2\2\2\23\u00b3\3\2\2"+
		"\2\25\u00b5\3\2\2\2\27\u00b7\3\2\2\2\31\u00b9\3\2\2\2\33\u00bb\3\2\2\2"+
		"\35\u00bd\3\2\2\2\37\u00bf\3\2\2\2!\u00c1\3\2\2\2#\u00c4\3\2\2\2%\u00c6"+
		"\3\2\2\2\'\u00c9\3\2\2\2)\u00cc\3\2\2\2+\u00cf\3\2\2\2-\u00d1\3\2\2\2"+
		"/\u00d3\3\2\2\2\61\u00d6\3\2\2\2\63\u00d8\3\2\2\2\65\u00db\3\2\2\2\67"+
		"\u00dd\3\2\2\29\u00e0\3\2\2\2;\u00e4\3\2\2\2=\u00e6\3\2\2\2?\u00e9\3\2"+
		"\2\2A\u00ed\3\2\2\2C\u00f0\3\2\2\2E\u00f2\3\2\2\2G\u00f4\3\2\2\2I\u00fd"+
		"\3\2\2\2K\u00ff\3\2\2\2M\u0101\3\2\2\2O\u0104\3\2\2\2Q\u0109\3\2\2\2S"+
		"\u010d\3\2\2\2U\u0110\3\2\2\2W\u0116\3\2\2\2Y\u011d\3\2\2\2[\u011f\3\2"+
		"\2\2]\u0124\3\2\2\2_\u012a\3\2\2\2a\u012f\3\2\2\2c\u0132\3\2\2\2e\u0136"+
		"\3\2\2\2g\u013a\3\2\2\2i\u013f\3\2\2\2k\u0145\3\2\2\2m\u014a\3\2\2\2o"+
		"\u0150\3\2\2\2q\u0155\3\2\2\2s\u0158\3\2\2\2u\u015b\3\2\2\2w\u015e\3\2"+
		"\2\2y\u0160\3\2\2\2{\u0164\3\2\2\2}\u016f\3\2\2\2\177\u0176\3\2\2\2\u0081"+
		"\u01a1\3\2\2\2\u0083\u01a3\3\2\2\2\u0085\u01a5\3\2\2\2\u0087\u01b1\3\2"+
		"\2\2\u0089\u01ce\3\2\2\2\u008b\u01d5\3\2\2\2\u008d\u01e7\3\2\2\2\u008f"+
		"\u01f2\3\2\2\2\u0091\u01f4\3\2\2\2\u0093\u0210\3\2\2\2\u0095\u0212\3\2"+
		"\2\2\u0097\u0214\3\2\2\2\u0099\u021d\3\2\2\2\u009b\u0227\3\2\2\2\u009d"+
		"\u022c\3\2\2\2\u009f\u00a0\7=\2\2\u00a0\4\3\2\2\2\u00a1\u00a2\7]\2\2\u00a2"+
		"\u00a3\7]\2\2\u00a3\6\3\2\2\2\u00a4\u00a5\7_\2\2\u00a5\b\3\2\2\2\u00a6"+
		"\u00a7\7]\2\2\u00a7\n\3\2\2\2\u00a8\u00a9\7<\2\2\u00a9\u00aa\7<\2\2\u00aa"+
		"\f\3\2\2\2\u00ab\u00ac\7<\2\2\u00ac\u00ad\7<\2\2\u00ad\u00ae\7<\2\2\u00ae"+
		"\16\3\2\2\2\u00af\u00b0\7&\2\2\u00b0\20\3\2\2\2\u00b1\u00b2\7B\2\2\u00b2"+
		"\22\3\2\2\2\u00b3\u00b4\7`\2\2\u00b4\24\3\2\2\2\u00b5\u00b6\7/\2\2\u00b6"+
		"\26\3\2\2\2\u00b7\u00b8\7-\2\2\u00b8\30\3\2\2\2\u00b9\u00ba\7<\2\2\u00ba"+
		"\32\3\2\2\2\u00bb\u00bc\7,\2\2\u00bc\34\3\2\2\2\u00bd\u00be\7\61\2\2\u00be"+
		"\36\3\2\2\2\u00bf\u00c0\7@\2\2\u00c0 \3\2\2\2\u00c1\u00c2\7@\2\2\u00c2"+
		"\u00c3\7?\2\2\u00c3\"\3\2\2\2\u00c4\u00c5\7>\2\2\u00c5$\3\2\2\2\u00c6"+
		"\u00c7\7>\2\2\u00c7\u00c8\7?\2\2\u00c8&\3\2\2\2\u00c9\u00ca\7?\2\2\u00ca"+
		"\u00cb\7?\2\2\u00cb(\3\2\2\2\u00cc\u00cd\7#\2\2\u00cd\u00ce\7?\2\2\u00ce"+
		"*\3\2\2\2\u00cf\u00d0\7#\2\2\u00d0,\3\2\2\2\u00d1\u00d2\7(\2\2\u00d2."+
		"\3\2\2\2\u00d3\u00d4\7(\2\2\u00d4\u00d5\7(\2\2\u00d5\60\3\2\2\2\u00d6"+
		"\u00d7\7~\2\2\u00d7\62\3\2\2\2\u00d8\u00d9\7~\2\2\u00d9\u00da\7~\2\2\u00da"+
		"\64\3\2\2\2\u00db\u00dc\7\u0080\2\2\u00dc\66\3\2\2\2\u00dd\u00de\7>\2"+
		"\2\u00de\u00df\7/\2\2\u00df8\3\2\2\2\u00e0\u00e1\7>\2\2\u00e1\u00e2\7"+
		">\2\2\u00e2\u00e3\7/\2\2\u00e3:\3\2\2\2\u00e4\u00e5\7?\2\2\u00e5<\3\2"+
		"\2\2\u00e6\u00e7\7/\2\2\u00e7\u00e8\7@\2\2\u00e8>\3\2\2\2\u00e9\u00ea"+
		"\7/\2\2\u00ea\u00eb\7@\2\2\u00eb\u00ec\7@\2\2\u00ec@\3\2\2\2\u00ed\u00ee"+
		"\7<\2\2\u00ee\u00ef\7?\2\2\u00efB\3\2\2\2\u00f0\u00f1\7}\2\2\u00f1D\3"+
		"\2\2\2\u00f2\u00f3\7\177\2\2\u00f3F\3\2\2\2\u00f4\u00f5\7h\2\2\u00f5\u00f6"+
		"\7w\2\2\u00f6\u00f7\7p\2\2\u00f7\u00f8\7e\2\2\u00f8\u00f9\7v\2\2\u00f9"+
		"\u00fa\7k\2\2\u00fa\u00fb\7q\2\2\u00fb\u00fc\7p\2\2\u00fcH\3\2\2\2\u00fd"+
		"\u00fe\7*\2\2\u00feJ\3\2\2\2\u00ff\u0100\7+\2\2\u0100L\3\2\2\2\u0101\u0102"+
		"\7k\2\2\u0102\u0103\7h\2\2\u0103N\3\2\2\2\u0104\u0105\7g\2\2\u0105\u0106"+
		"\7n\2\2\u0106\u0107\7u\2\2\u0107\u0108\7g\2\2\u0108P\3\2\2\2\u0109\u010a"+
		"\7h\2\2\u010a\u010b\7q\2\2\u010b\u010c\7t\2\2\u010cR\3\2\2\2\u010d\u010e"+
		"\7k\2\2\u010e\u010f\7p\2\2\u010fT\3\2\2\2\u0110\u0111\7y\2\2\u0111\u0112"+
		"\7j\2\2\u0112\u0113\7k\2\2\u0113\u0114\7n\2\2\u0114\u0115\7g\2\2\u0115"+
		"V\3\2\2\2\u0116\u0117\7t\2\2\u0117\u0118\7g\2\2\u0118\u0119\7r\2\2\u0119"+
		"\u011a\7g\2\2\u011a\u011b\7c\2\2\u011b\u011c\7v\2\2\u011cX\3\2\2\2\u011d"+
		"\u011e\7A\2\2\u011eZ\3\2\2\2\u011f\u0120\7p\2\2\u0120\u0121\7g\2\2\u0121"+
		"\u0122\7z\2\2\u0122\u0123\7v\2\2\u0123\\\3\2\2\2\u0124\u0125\7d\2\2\u0125"+
		"\u0126\7t\2\2\u0126\u0127\7g\2\2\u0127\u0128\7c\2\2\u0128\u0129\7m\2\2"+
		"\u0129^\3\2\2\2\u012a\u012b\7P\2\2\u012b\u012c\7W\2\2\u012c\u012d\7N\2"+
		"\2\u012d\u012e\7N\2\2\u012e`\3\2\2\2\u012f\u0130\7P\2\2\u0130\u0131\7"+
		"C\2\2\u0131b\3\2\2\2\u0132\u0133\7K\2\2\u0133\u0134\7p\2\2\u0134\u0135"+
		"\7h\2\2\u0135d\3\2\2\2\u0136\u0137\7P\2\2\u0137\u0138\7c\2\2\u0138\u0139"+
		"\7P\2\2\u0139f\3\2\2\2\u013a\u013b\7V\2\2\u013b\u013c\7T\2\2\u013c\u013d"+
		"\7W\2\2\u013d\u013e\7G\2\2\u013eh\3\2\2\2\u013f\u0140\7H\2\2\u0140\u0141"+
		"\7C\2\2\u0141\u0142\7N\2\2\u0142\u0143\7U\2\2\u0143\u0144\7G\2\2\u0144"+
		"j\3\2\2\2\u0145\u0146\7v\2\2\u0146\u0147\7t\2\2\u0147\u0148\7w\2\2\u0148"+
		"\u0149\7g\2\2\u0149l\3\2\2\2\u014a\u014b\7h\2\2\u014b\u014c\7c\2\2\u014c"+
		"\u014d\7n\2\2\u014d\u014e\7u\2\2\u014e\u014f\7g\2\2\u014fn\3\2\2\2\u0150"+
		"\u0151\7p\2\2\u0151\u0152\7w\2\2\u0152\u0153\7n\2\2\u0153\u0154\7n\2\2"+
		"\u0154p\3\2\2\2\u0155\u0156\7p\2\2\u0156\u0157\7c\2\2\u0157r\3\2\2\2\u0158"+
		"\u0159\7?\2\2\u0159\u015a\7@\2\2\u015at\3\2\2\2\u015b\u015c\7?\2\2\u015c"+
		"\u015d\7>\2\2\u015dv\3\2\2\2\u015e\u015f\7.\2\2\u015fx\3\2\2\2\u0160\u0161"+
		"\7\60\2\2\u0161\u0162\7\60\2\2\u0162\u0163\7\60\2\2\u0163z\3\2\2\2\u0164"+
		"\u0165\7\62\2\2\u0165\u0167\t\2\2\2\u0166\u0168\5\177@\2\u0167\u0166\3"+
		"\2\2\2\u0168\u0169\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u016c\3\2\2\2\u016b\u016d\t\3\2\2\u016c\u016b\3\2\2\2\u016c\u016d\3\2"+
		"\2\2\u016d|\3\2\2\2\u016e\u0170\5\u0083B\2\u016f\u016e\3\2\2\2\u0170\u0171"+
		"\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0174\3\2\2\2\u0173"+
		"\u0175\t\3\2\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175~\3\2\2\2"+
		"\u0176\u0177\t\4\2\2\u0177\u0080\3\2\2\2\u0178\u017a\5\u0083B\2\u0179"+
		"\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2"+
		"\2\2\u017c\u017d\3\2\2\2\u017d\u0181\7\60\2\2\u017e\u0180\5\u0083B\2\u017f"+
		"\u017e\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2"+
		"\2\2\u0182\u0185\3\2\2\2\u0183\u0181\3\2\2\2\u0184\u0186\5\u0085C\2\u0185"+
		"\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0188\3\2\2\2\u0187\u0189\t\3"+
		"\2\2\u0188\u0187\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u01a2\3\2\2\2\u018a"+
		"\u018c\5\u0083B\2\u018b\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018b"+
		"\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u0190\3\2\2\2\u018f\u0191\5\u0085C"+
		"\2\u0190\u018f\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2\2\2\u0192\u0194"+
		"\t\3\2\2\u0193\u0192\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u01a2\3\2\2\2\u0195"+
		"\u0197\7\60\2\2\u0196\u0198\5\u0083B\2\u0197\u0196\3\2\2\2\u0198\u0199"+
		"\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019c\3\2\2\2\u019b"+
		"\u019d\5\u0085C\2\u019c\u019b\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019f"+
		"\3\2\2\2\u019e\u01a0\t\3\2\2\u019f\u019e\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0"+
		"\u01a2\3\2\2\2\u01a1\u0179\3\2\2\2\u01a1\u018b\3\2\2\2\u01a1\u0195\3\2"+
		"\2\2\u01a2\u0082\3\2\2\2\u01a3\u01a4\4\62;\2\u01a4\u0084\3\2\2\2\u01a5"+
		"\u01a7\t\5\2\2\u01a6\u01a8\t\6\2\2\u01a7\u01a6\3\2\2\2\u01a7\u01a8\3\2"+
		"\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01aa\5}?\2\u01aa\u0086\3\2\2\2\u01ab\u01ac"+
		"\5}?\2\u01ac\u01ad\7k\2\2\u01ad\u01b2\3\2\2\2\u01ae\u01af\5\u0081A\2\u01af"+
		"\u01b0\7k\2\2\u01b0\u01b2\3\2\2\2\u01b1\u01ab\3\2\2\2\u01b1\u01ae\3\2"+
		"\2\2\u01b2\u0088\3\2\2\2\u01b3\u01b8\7$\2\2\u01b4\u01b7\5\u008bF\2\u01b5"+
		"\u01b7\n\7\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b5\3\2\2\2\u01b7\u01ba\3\2"+
		"\2\2\u01b8\u01b9\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01bb\3\2\2\2\u01ba"+
		"\u01b8\3\2\2\2\u01bb\u01cf\7$\2\2\u01bc\u01c1\7)\2\2\u01bd\u01c0\5\u008b"+
		"F\2\u01be\u01c0\n\b\2\2\u01bf\u01bd\3\2\2\2\u01bf\u01be\3\2\2\2\u01c0"+
		"\u01c3\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2\u01c4\3\2"+
		"\2\2\u01c3\u01c1\3\2\2\2\u01c4\u01cf\7)\2\2\u01c5\u01ca\7b\2\2\u01c6\u01c9"+
		"\5\u008bF\2\u01c7\u01c9\n\b\2\2\u01c8\u01c6\3\2\2\2\u01c8\u01c7\3\2\2"+
		"\2\u01c9\u01cc\3\2\2\2\u01ca\u01cb\3\2\2\2\u01ca\u01c8\3\2\2\2\u01cb\u01cd"+
		"\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cd\u01cf\7b\2\2\u01ce\u01b3\3\2\2\2\u01ce"+
		"\u01bc\3\2\2\2\u01ce\u01c5\3\2\2\2\u01cf\u008a\3\2\2\2\u01d0\u01d1\7^"+
		"\2\2\u01d1\u01d6\t\t\2\2\u01d2\u01d6\5\u008dG\2\u01d3\u01d6\5\u0091I\2"+
		"\u01d4\u01d6\5\u008fH\2\u01d5\u01d0\3\2\2\2\u01d5\u01d2\3\2\2\2\u01d5"+
		"\u01d3\3\2\2\2\u01d5\u01d4\3\2\2\2\u01d6\u008c\3\2\2\2\u01d7\u01d8\7^"+
		"\2\2\u01d8\u01d9\7w\2\2\u01d9\u01da\5\177@\2\u01da\u01db\5\177@\2\u01db"+
		"\u01dc\5\177@\2\u01dc\u01dd\5\177@\2\u01dd\u01e8\3\2\2\2\u01de\u01df\7"+
		"^\2\2\u01df\u01e0\7w\2\2\u01e0\u01e1\7}\2\2\u01e1\u01e2\5\177@\2\u01e2"+
		"\u01e3\5\177@\2\u01e3\u01e4\5\177@\2\u01e4\u01e5\5\177@\2\u01e5\u01e6"+
		"\7\177\2\2\u01e6\u01e8\3\2\2\2\u01e7\u01d7\3\2\2\2\u01e7\u01de\3\2\2\2"+
		"\u01e8\u008e\3\2\2\2\u01e9\u01ea\7^\2\2\u01ea\u01eb\t\n\2\2\u01eb\u01ec"+
		"\t\13\2\2\u01ec\u01f3\t\13\2\2\u01ed\u01ee\7^\2\2\u01ee\u01ef\t\13\2\2"+
		"\u01ef\u01f3\t\13\2\2\u01f0\u01f1\7^\2\2\u01f1\u01f3\t\13\2\2\u01f2\u01e9"+
		"\3\2\2\2\u01f2\u01ed\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f3\u0090\3\2\2\2\u01f4"+
		"\u01f5\7^\2\2\u01f5\u01f7\5\177@\2\u01f6\u01f8\5\177@\2\u01f7\u01f6\3"+
		"\2\2\2\u01f7\u01f8\3\2\2\2\u01f8\u0092\3\2\2\2\u01f9\u01fc\7\60\2\2\u01fa"+
		"\u01fd\5\u0095K\2\u01fb\u01fd\t\f\2\2\u01fc\u01fa\3\2\2\2\u01fc\u01fb"+
		"\3\2\2\2\u01fd\u0203\3\2\2\2\u01fe\u0202\5\u0095K\2\u01ff\u0202\5\u0083"+
		"B\2\u0200\u0202\t\f\2\2\u0201\u01fe\3\2\2\2\u0201\u01ff\3\2\2\2\u0201"+
		"\u0200\3\2\2\2\u0202\u0205\3\2\2\2\u0203\u0201\3\2\2\2\u0203\u0204\3\2"+
		"\2\2\u0204\u0211\3\2\2\2\u0205\u0203\3\2\2\2\u0206\u020c\5\u0095K\2\u0207"+
		"\u020b\5\u0095K\2\u0208\u020b\5\u0083B\2\u0209\u020b\t\f\2\2\u020a\u0207"+
		"\3\2\2\2\u020a\u0208\3\2\2\2\u020a\u0209\3\2\2\2\u020b\u020e\3\2\2\2\u020c"+
		"\u020a\3\2\2\2\u020c\u020d\3\2\2\2\u020d\u0211\3\2\2\2\u020e\u020c\3\2"+
		"\2\2\u020f\u0211\7\60\2\2\u0210\u01f9\3\2\2\2\u0210\u0206\3\2\2\2\u0210"+
		"\u020f\3\2\2\2\u0211\u0094\3\2\2\2\u0212\u0213\t\r\2\2\u0213\u0096\3\2"+
		"\2\2\u0214\u0218\7\'\2\2\u0215\u0217\13\2\2\2\u0216\u0215\3\2\2\2\u0217"+
		"\u021a\3\2\2\2\u0218\u0219\3\2\2\2\u0218\u0216\3\2\2\2\u0219\u021b\3\2"+
		"\2\2\u021a\u0218\3\2\2\2\u021b\u021c\7\'\2\2\u021c\u0098\3\2\2\2\u021d"+
		"\u0221\7%\2\2\u021e\u0220\n\16\2\2\u021f\u021e\3\2\2\2\u0220\u0223\3\2"+
		"\2\2\u0221\u021f\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0224\3\2\2\2\u0223"+
		"\u0221\3\2\2\2\u0224\u0225\bM\2\2\u0225\u009a\3\2\2\2\u0226\u0228\7\17"+
		"\2\2\u0227\u0226\3\2\2\2\u0227\u0228\3\2\2\2\u0228\u0229\3\2\2\2\u0229"+
		"\u022a\7\f\2\2\u022a\u009c\3\2\2\2\u022b\u022d\t\17\2\2\u022c\u022b\3"+
		"\2\2\2\u022d\u022e\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f"+
		"\u0230\3\2\2\2\u0230\u0231\bO\3\2\u0231\u009e\3\2\2\2)\2\u0169\u016c\u0171"+
		"\u0174\u017b\u0181\u0185\u0188\u018d\u0190\u0193\u0199\u019c\u019f\u01a1"+
		"\u01a7\u01b1\u01b6\u01b8\u01bf\u01c1\u01c8\u01ca\u01ce\u01d5\u01e7\u01f2"+
		"\u01f7\u01fc\u0201\u0203\u020a\u020c\u0210\u0218\u0221\u0227\u022e\4\t"+
		"F\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}