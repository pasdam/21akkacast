package com.pasdam.gptest.messages;

import java.io.Serializable;

import com.pasdam.gptest.gameengine.Card;

/**
 * Message that notify that a card is dealt to a player
 * 
 * @author paco
 * @version 0.1
 */
public class CardDealtToPlayerMessage implements Serializable {

	private static final long serialVersionUID = -5761794651083742025L;

	/**
	 * Seat index of the player
	 */
	public final int seatIndex;
	
	/**
	 * Card dealt to player
	 */
	public final Card card;

	/**
	 * Message constructor
	 * 
	 * @param seatIndex
	 *            seat index of the player
	 * @param card
	 *            card dealt to player
	 */
	public CardDealtToPlayerMessage(int seatIndex, Card card) {
		this.seatIndex = seatIndex;
		this.card = card;
	}
}
