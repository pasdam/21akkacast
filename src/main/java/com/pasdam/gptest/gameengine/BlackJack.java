package com.pasdam.gptest.gameengine;

/**
 * Utility class with game engine constraints
 * 
 * @author paco
 * @version 0.1
 */
public class BlackJack {
	
	/**	Seat index of the dealer */
	public static final int DEALER_SEAT_INDEX = -1;

	/**	Max number of players */
	public static final int MAX_PLAYERS = 7;

	/**	Max point value */
	public static final int MAX_POINT = 21;
	
	/**	Minimum dealer point, under which he have to hit */
	public static final int MINIMUM_DEALER_POINT = 16;

	/** Private constructor: prevent instantiation */
	private BlackJack() {}
	
	/**
	 * Returns a string representation of the card
	 * 
	 * @param card
	 *            card to convert to string
	 * @return a string representation of the card
	 */
	public static String cardToString(Card card) {
		String value;
		switch (card.value) {
			case 0:
				value = "A";
				break;
				
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				// 2 to 10
				value = ""+(card.value+1);
				break;
				
			case 10:
				value = "J";
				break;

			case 11:
				value = "Q";
				break;
				
			case 12:
				value = "K";
				break;
	
			default:
				return "";
		}
		
		switch (card.suit) {
			case 0:
				// Hearts
				return value + "H";
			
			case 1:
				// Tiles
				return value + "T";
			
			case 2:
				// Clovers
				return value + "C";
			
			case 3:
				// Pikes
				return value + "P";
	
			default:
				return "";
		}
	}
}
