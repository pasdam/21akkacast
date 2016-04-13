package com.pasdam.gptest.messages;

import java.io.Serializable;

/**
 * Message that indicates that a player has sat
 * 
 * @author paco
 * @version 0.1
 */
public class PlayerSeatedMessage implements Serializable {

	private static final long serialVersionUID = 6073784267018697982L;

	/**
	 * Index of the seat taken by the player
	 */
	public final int seatIndex;

	/**
	 * Message constructor
	 * 
	 * @param seatIndex
	 *            index of the seat taken by the player
	 */
	public PlayerSeatedMessage(int seatIndex) {
		this.seatIndex = seatIndex;
	}
}
