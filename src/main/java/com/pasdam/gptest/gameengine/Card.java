package com.pasdam.gptest.gameengine;

/**
 * A single card
 * 
 * @author paco
 * @version 0.1
 */
public class Card {

	/**
	 * Card's nominal value
	 */
	public final int value;
	
	/**
	 * Card's suit
	 */
	public final int suit;

	/**
	 * Create a card
	 * 
	 * @param value
	 *            card's nominal value
	 * @param suit
	 *            card's suit
	 */
	public Card(int value, int suit) {
		this.value = value;
		this.suit = suit;
	}
}
