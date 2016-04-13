package com.pasdam.gptest.messages;

/**
 * Response message to a bet request
 * 
 * @author paco
 * @version 0.1
 */
public class BetResponseMessage extends PlayerMessage {

	private static final long serialVersionUID = 4469406059154744785L;

	/**
	 * Amount of the bet
	 */
	public final int amount;
	
	/**
	 * Message constructor
	 * 
	 * @param playerId
	 *            id of the player
	 * @param amount
	 *            amount of the bet
	 */
	public BetResponseMessage(int playerId, int amount) {
		super(playerId);
		this.amount   = amount;
	}
}
