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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, WORD=7, WS=8;
	public static final int
		RULE_script = 0, RULE_command = 1, RULE_globalCallable = 2, RULE_affectation = 3, 
		RULE_affectationNb = 4, RULE_affectationInstance = 5, RULE_callable = 6, 
		RULE_variable = 7, RULE_newInstance = 8, RULE_method = 9, RULE_property = 10, 
		RULE_properties = 11, RULE_args = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"script", "command", "globalCallable", "affectation", "affectationNb", 
			"affectationInstance", "callable", "variable", "newInstance", "method", 
			"property", "properties", "args"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'('", "')'", "'new'", "'.'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "WORD", "WS"
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
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || _la==WORD) {
				{
				{
				setState(26);
				command();
				}
				}
				setState(31);
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
		public AffectationContext affectation() {
			return getRuleContext(AffectationContext.class,0);
		}
		public GlobalCallableContext globalCallable() {
			return getRuleContext(GlobalCallableContext.class,0);
		}
		public MethodContext method() {
			return getRuleContext(MethodContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
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
			setState(37);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(32);
				affectation();
				}
				break;
			case 2:
				{
				setState(33);
				globalCallable();
				}
				break;
			case 3:
				{
				setState(34);
				method();
				}
				break;
			case 4:
				{
				setState(35);
				variable();
				}
				break;
			case 5:
				{
				setState(36);
				properties();
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

	public static class GlobalCallableContext extends ParserRuleContext {
		public CallableContext callable() {
			return getRuleContext(CallableContext.class,0);
		}
		public GlobalCallableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalCallable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterGlobalCallable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitGlobalCallable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitGlobalCallable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalCallableContext globalCallable() throws RecognitionException {
		GlobalCallableContext _localctx = new GlobalCallableContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_globalCallable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
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

	public static class AffectationContext extends ParserRuleContext {
		public AffectationNbContext affectationNb() {
			return getRuleContext(AffectationNbContext.class,0);
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
			setState(43);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(41);
				affectationNb();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
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
		public List<TerminalNode> WORD() { return getTokens(SatelliteBeaconParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(SatelliteBeaconParser.WORD, i);
		}
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
			setState(45);
			match(WORD);
			setState(46);
			match(T__0);
			setState(47);
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
		enterRule(_localctx, 10, RULE_affectationInstance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(WORD);
			setState(50);
			match(T__0);
			setState(51);
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
		enterRule(_localctx, 12, RULE_callable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(WORD);
			setState(54);
			match(T__1);
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WORD) {
				{
				setState(55);
				args();
				}
			}

			setState(58);
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
		enterRule(_localctx, 14, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
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
		enterRule(_localctx, 16, RULE_newInstance);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(T__3);
			setState(63);
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

	public static class MethodContext extends ParserRuleContext {
		public CallableContext callable() {
			return getRuleContext(CallableContext.class,0);
		}
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
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
		enterRule(_localctx, 18, RULE_method);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(65);
				match(WORD);
				}
				break;
			}
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(68);
				match(T__4);
				}
			}

			setState(71);
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

	public static class PropertyContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(T__4);
			setState(74);
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

	public static class PropertiesContext extends ParserRuleContext {
		public TerminalNode WORD() { return getToken(SatelliteBeaconParser.WORD, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SatelliteBeaconListener ) ((SatelliteBeaconListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SatelliteBeaconVisitor ) return ((SatelliteBeaconVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_properties);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(WORD);
			setState(78); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(77);
					property();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(80); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public List<TerminalNode> WORD() { return getTokens(SatelliteBeaconParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(SatelliteBeaconParser.WORD, i);
		}
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
		enterRule(_localctx, 24, RULE_args);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(WORD);
			setState(83);
			match(T__0);
			setState(84);
			match(WORD);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(85);
				match(T__5);
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(86);
						args();
						}
						} 
					}
					setState(91);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\na\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\3\3\3\3\3\3\3"+
		"\3\3\5\3(\n\3\3\4\3\4\3\5\3\5\5\5.\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\5\b;\n\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\5\13E\n\13\3\13"+
		"\5\13H\n\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\6\rQ\n\r\r\r\16\rR\3\16\3\16"+
		"\3\16\3\16\3\16\7\16Z\n\16\f\16\16\16]\13\16\5\16_\n\16\3\16\2\2\17\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\2\2\2_\2\37\3\2\2\2\4\'\3\2\2\2\6)\3\2"+
		"\2\2\b-\3\2\2\2\n/\3\2\2\2\f\63\3\2\2\2\16\67\3\2\2\2\20>\3\2\2\2\22@"+
		"\3\2\2\2\24D\3\2\2\2\26K\3\2\2\2\30N\3\2\2\2\32T\3\2\2\2\34\36\5\4\3\2"+
		"\35\34\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \3\3\2\2\2!\37\3"+
		"\2\2\2\"(\5\b\5\2#(\5\6\4\2$(\5\24\13\2%(\5\20\t\2&(\5\30\r\2\'\"\3\2"+
		"\2\2\'#\3\2\2\2\'$\3\2\2\2\'%\3\2\2\2\'&\3\2\2\2(\5\3\2\2\2)*\5\16\b\2"+
		"*\7\3\2\2\2+.\5\n\6\2,.\5\f\7\2-+\3\2\2\2-,\3\2\2\2.\t\3\2\2\2/\60\7\t"+
		"\2\2\60\61\7\3\2\2\61\62\7\t\2\2\62\13\3\2\2\2\63\64\7\t\2\2\64\65\7\3"+
		"\2\2\65\66\5\22\n\2\66\r\3\2\2\2\678\7\t\2\28:\7\4\2\29;\5\32\16\2:9\3"+
		"\2\2\2:;\3\2\2\2;<\3\2\2\2<=\7\5\2\2=\17\3\2\2\2>?\7\t\2\2?\21\3\2\2\2"+
		"@A\7\6\2\2AB\5\16\b\2B\23\3\2\2\2CE\7\t\2\2DC\3\2\2\2DE\3\2\2\2EG\3\2"+
		"\2\2FH\7\7\2\2GF\3\2\2\2GH\3\2\2\2HI\3\2\2\2IJ\5\16\b\2J\25\3\2\2\2KL"+
		"\7\7\2\2LM\7\t\2\2M\27\3\2\2\2NP\7\t\2\2OQ\5\26\f\2PO\3\2\2\2QR\3\2\2"+
		"\2RP\3\2\2\2RS\3\2\2\2S\31\3\2\2\2TU\7\t\2\2UV\7\3\2\2V^\7\t\2\2W[\7\b"+
		"\2\2XZ\5\32\16\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\_\3\2\2\2]"+
		"[\3\2\2\2^W\3\2\2\2^_\3\2\2\2_\33\3\2\2\2\13\37\'-:DGR[^";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}