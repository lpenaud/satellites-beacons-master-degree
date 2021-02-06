// Generated from antlr/SatelliteBeacon.g4 by ANTLR 4.9.1
package edu.ubo.satellitebeacons.main.generated;

//

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SatelliteBeaconParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SatelliteBeaconVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(SatelliteBeaconParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(SatelliteBeaconParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#globals}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobals(SatelliteBeaconParser.GlobalsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#affectation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectation(SatelliteBeaconParser.AffectationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#affectationNb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectationNb(SatelliteBeaconParser.AffectationNbContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#affectationString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectationString(SatelliteBeaconParser.AffectationStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#affectationInstance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAffectationInstance(SatelliteBeaconParser.AffectationInstanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#newInstance}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewInstance(SatelliteBeaconParser.NewInstanceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#callable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallable(SatelliteBeaconParser.CallableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(SatelliteBeaconParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(SatelliteBeaconParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SatelliteBeaconParser#method}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(SatelliteBeaconParser.MethodContext ctx);
}