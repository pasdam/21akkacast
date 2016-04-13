package com.pasdam.gptest.messages;

import java.io.Serializable;

import com.pasdam.gptest.gameengine.Turn;

/**
 * Message that indicates that a specific player has the turn
 * 
 * @author paco
 * @version 0.1
 */
public class TurnMessage implements Serializable {
	
	private static final long serialVersionUID = -8655333001739578948L;

	/**
	 * Seat index of the player which has the turn
	 */
	public final int seatIndex;
	
	/**
	 * Turn type
	 */
	public final Turn turn;
	
	/**
	 * Message constructor
	 * 
	 * @param seatIndex
	 *            seat index of the player which has the turn
	 * @param turn
	 *            turn type
	 */
	public TurnMessage(int seatIndex, Turn turn) {
		this.seatIndex = seatIndex;
		this.turn = turn;
	}
}
