package com.pasdam.gptest.messages;

import java.io.Serializable;

/**
 * Message sent from the player
 * 
 * @author paco
 * @version 0.1
 */
class PlayerMessage implements Serializable {

	private static final long serialVersionUID = -6361741298544384373L;

	/**
	 * Id of the player
	 */
	public final int playerId;

	/**
	 * Message constructor
	 * 
	 * @param playerId
	 *            id of the player
	 */
	public PlayerMessage(int playerId) {
		this.playerId = playerId;
	}
}