// Generated from antlr/SatelliteBeacon.g4 by ANTLR 4.9.1
package edu.ubo.satellitebeacons.main.generated;

//

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SatelliteBeaconParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, NB=9, 
		WORD=10, STRING=11, WS=12;
	public static final int
		RULE_script = 0, RULE_command = 1, RULE_globals = 2, RULE_affectation = 3, 
		RULE_affectationNb = 4, RULE_affectationString = 5, RULE_affectationInstance = 6, 
		RULE_newInstance = 7, RULE_callable = 8, RULE_variable = 9, RULE_args = 10, 
		RULE_method = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"script", "command", "globals", "affectation", "affectationNb", "affectationString", 
			"affectationInstance", "newInstance", "callable", "variable", "args", 
			"method"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'globals'", "'='", "'\"'", "'new'", "'('", "')'", "','", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "NB", "WORD", "STRING", 
			"WS"
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
	public String getGrammarFileName() { return "SatelliteBeacon.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SatelliteBeaconParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ScriptContext extends ParserRuleContext {
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==WORD) {
				{
				{
				setState(24);
				command();
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class CommandContext extends ParserRuleContext {
		public GlobalsContext globals() {
			return getRuleContext(GlobalsContext.class,0);
		}
		public AffectationContext affectation() {
			return getRuleContext(AffectationContext.class,0);
		}
		public CallableContext callable() {
			return getRuleContext(CallableContext.class,0);
		}
		public MethodContext method() {
			return getRuleContext(MethodContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_command);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(30);
				globals();
				}
				break;
			case 2:
				{
				setState(31);
				affectation();
				}
				break;
			case 3:
				{
				setState(32);
				callable();
				}
				break;
			case 4:
				{
				setState(33);
				method();
				}
				break;
			case 5:
				{
				setState(34);
				variable();
				}
				break;
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

	public static class GlobalsContext extends ParserRuleContext {
		public GlobalsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globals; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterGlobals(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitGlobals(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitGlobals(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalsContext globals() throws RecognitionException {
		GlobalsContext _localctx = new GlobalsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_globals);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(T__0);
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

	public static class AffectationContext extends ParserRuleContext {
		public AffectationNbContext affectationNb() {
			return getRuleContext(AffectationNbContext.class,0);
		}
		public AffectationStringContext affectationString() {
			return getRuleContext(AffectationStringContext.class,0);
		}
		public AffectationInstanceContext affectationInstance() {
			return getRuleContext(AffectationInstanceContext.class,0);
		}
		public AffectationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_affectation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterAffectation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitAffectation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitAffectation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AffectationContext affectation() throws RecognitionException {
		AffectationContext _localctx = new AffectationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_affectation);
		try {
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				affectationNb();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				affectationString();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				affectationInstance();
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

	public static class AffectationNbContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public TerminalNode NB() { return getToken(SatelliteBeaconParser.NB, 0); }
		public AffectationNbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_affectationNb; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterAffectationNb(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitAffectationNb(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitAffectationNb(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AffectationNbContext affectationNb() throws RecognitionException {
		AffectationNbContext _localctx = new AffectationNbContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_affectationNb);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(WORD);
			setState(45);
			match(T__1);
			setState(46);
			match(NB);
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

	public static class AffectationStringContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public TerminalNode STRING() { return getToken(SatelliteBeaconParser.STRING, 0); }
		public AffectationStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_affectationString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterAffectationString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitAffectationString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitAffectationString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AffectationStringContext affectationString() throws RecognitionException {
		AffectationStringContext _localctx = new AffectationStringContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_affectationString);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(WORD);
			setState(49);
			match(T__1);
			setState(50);
			match(T__2);
			setState(51);
			match(STRING);
			setState(52);
			match(T__2);
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

	public static class AffectationInstanceContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public NewInstanceContext newInstance() {
			return getRuleContext(NewInstanceContext.class,0);
		}
		public AffectationInstanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_affectationInstance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterAffectationInstance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitAffectationInstance(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitAffectationInstance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AffectationInstanceContext affectationInstance() throws RecognitionException {
		AffectationInstanceContext _localctx = new AffectationInstanceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_affectationInstance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(WORD);
			setState(55);
			match(T__1);
			setState(56);
			newInstance();
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

	public static class NewInstanceContext extends ParserRuleContext {
		public CallableContext callable() {
			return getRuleContext(CallableContext.class,0);
		}
		public NewInstanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newInstance; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterNewInstance(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitNewInstance(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitNewInstance(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewInstanceContext newInstance() throws RecognitionException {
		NewInstanceContext _localctx = new NewInstanceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_newInstance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(T__3);
			setState(59);
			callable();
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

	public static class CallableContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public CallableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterCallable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitCallable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitCallable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallableContext callable() throws RecognitionException {
		CallableContext _localctx = new CallableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_callable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(WORD);
			setState(62);
			match(T__4);
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WORD) {
				{
				setState(63);
				args();
				}
			}

			setState(66);
			match(T__5);
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(WORD);
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

	public static class ArgsContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public TerminalNode NB() { return getToken(SatelliteBeaconParser.NB, 0); }
		public TerminalNode STRING() { return getToken(SatelliteBeaconParser.STRING, 0); }
		public List<ArgsContext> args() {
			return getRuleContexts(ArgsContext.class);
		}
		public ArgsContext args(int i) {
			return getRuleContext(ArgsContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_args);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(WORD);
			setState(71);
			match(T__1);
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NB:
				{
				setState(72);
				match(NB);
				}
				break;
			case T__2:
				{
				{
				setState(73);
				match(T__2);
				setState(74);
				match(STRING);
				setState(75);
				match(T__2);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(78);
				match(T__6);
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(79);
						args();
						}
						} 
					}
					setState(84);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
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

	public static class MethodContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public CallableContext callable() {
			return getRuleContext(CallableContext.class,0);
		}
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(WORD);
			setState(88);
			match(T__7);
			setState(89);
			callable();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\16^\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\3\3\3\3\3\3\3\3\3\5\3"+
		"&\n\3\3\4\3\4\3\5\3\5\3\5\5\5-\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\5\nC\n\n\3\n\3\n\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\5\fO\n\f\3\f\3\f\7\fS\n\f\f\f\16\fV\13\f\5\f"+
		"X\n\f\3\r\3\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2\\"+
		"\2\35\3\2\2\2\4%\3\2\2\2\6\'\3\2\2\2\b,\3\2\2\2\n.\3\2\2\2\f\62\3\2\2"+
		"\2\168\3\2\2\2\20<\3\2\2\2\22?\3\2\2\2\24F\3\2\2\2\26H\3\2\2\2\30Y\3\2"+
		"\2\2\32\34\5\4\3\2\33\32\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2\35\36\3\2"+
		"\2\2\36\3\3\2\2\2\37\35\3\2\2\2 &\5\6\4\2!&\5\b\5\2\"&\5\22\n\2#&\5\30"+
		"\r\2$&\5\24\13\2% \3\2\2\2%!\3\2\2\2%\"\3\2\2\2%#\3\2\2\2%$\3\2\2\2&\5"+
		"\3\2\2\2\'(\7\3\2\2(\7\3\2\2\2)-\5\n\6\2*-\5\f\7\2+-\5\16\b\2,)\3\2\2"+
		"\2,*\3\2\2\2,+\3\2\2\2-\t\3\2\2\2./\7\f\2\2/\60\7\4\2\2\60\61\7\13\2\2"+
		"\61\13\3\2\2\2\62\63\7\f\2\2\63\64\7\4\2\2\64\65\7\5\2\2\65\66\7\r\2\2"+
		"\66\67\7\5\2\2\67\r\3\2\2\289\7\f\2\29:\7\4\2\2:;\5\20\t\2;\17\3\2\2\2"+
		"<=\7\6\2\2=>\5\22\n\2>\21\3\2\2\2?@\7\f\2\2@B\7\7\2\2AC\5\26\f\2BA\3\2"+
		"\2\2BC\3\2\2\2CD\3\2\2\2DE\7\b\2\2E\23\3\2\2\2FG\7\f\2\2G\25\3\2\2\2H"+
		"I\7\f\2\2IN\7\4\2\2JO\7\13\2\2KL\7\5\2\2LM\7\r\2\2MO\7\5\2\2NJ\3\2\2\2"+
		"NK\3\2\2\2OW\3\2\2\2PT\7\t\2\2QS\5\26\f\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2"+
		"\2TU\3\2\2\2UX\3\2\2\2VT\3\2\2\2WP\3\2\2\2WX\3\2\2\2X\27\3\2\2\2YZ\7\f"+
		"\2\2Z[\7\n\2\2[\\\5\22\n\2\\\31\3\2\2\2\t\35%,BNTW";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}