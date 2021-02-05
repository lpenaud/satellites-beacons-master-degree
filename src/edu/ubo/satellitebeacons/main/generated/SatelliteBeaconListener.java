// Generated from antlr/SatelliteBeacon.g4 by ANTLR 4.9.1
package edu.ubo.satellitebeacons.main.generated;

//

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SatelliteBeaconParser}.
 */
public interface SatelliteBeaconListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(SatelliteBeaconParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(SatelliteBeaconParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#globals}.
	 * @param ctx the parse tree
	 */
	void enterGlobals(SatelliteBeaconParser.GlobalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#globals}.
	 * @param ctx the parse tree
	 */
	void exitGlobals(SatelliteBeaconParser.GlobalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#affectation}.
	 * @param ctx the parse tree
	 */
	void enterAffectation(SatelliteBeaconParser.AffectationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#affectation}.
	 * @param ctx the parse tree
	 */
	void exitAffectation(SatelliteBeaconParser.AffectationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#affectationNb}.
	 * @param ctx the parse tree
	 */
	void enterAffectationNb(SatelliteBeaconParser.AffectationNbContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#affectationNb}.
	 * @param ctx the parse tree
	 */
	void exitAffectationNb(SatelliteBeaconParser.AffectationNbContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#affectationString}.
	 * @param ctx the parse tree
	 */
	void enterAffectationString(SatelliteBeaconParser.AffectationStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#affectationString}.
	 * @param ctx the parse tree
	 */
	void exitAffectationString(SatelliteBeaconParser.AffectationStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#affectationInstance}.
	 * @param ctx the parse tree
	 */
	void enterAffectationInstance(SatelliteBeaconParser.AffectationInstanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#affectationInstance}.
	 * @param ctx the parse tree
	 */
	void exitAffectationInstance(SatelliteBeaconParser.AffectationInstanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#newInstance}.
	 * @param ctx the parse tree
	 */
	void enterNewInstance(SatelliteBeaconParser.NewInstanceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#newInstance}.
	 * @param ctx the parse tree
	 */
	void exitNewInstance(SatelliteBeaconParser.NewInstanceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#callable}.
	 * @param ctx the parse tree
	 */
	void enterCallable(SatelliteBeaconParser.CallableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#callable}.
	 * @param ctx the parse tree
	 */
	void exitCallable(SatelliteBeaconParser.CallableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(SatelliteBeaconParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(SatelliteBeaconParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#lastargs}.
	 * @param ctx the parse tree
	 */
	void enterLastargs(SatelliteBeaconParser.LastargsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#lastargs}.
	 * @param ctx the parse tree
	 */
	void exitLastargs(SatelliteBeaconParser.LastargsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SatelliteBeaconParser#method}.
	 * @param ctx the parse tree
	 */
	void enterMethod(SatelliteBeaconParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link SatelliteBeaconParser#method}.
	 * @param ctx the parse tree
	 */
	void exitMethod(SatelliteBeaconParser.MethodContext ctx);
}