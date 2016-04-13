package com.pasdam.gptest.gameengine;

import java.util.ArrayList;

/**
 * Deck of cards
 * 
 * @author paco
 * @version 0.1
 */
public class CardsDeck {
	
	/**
	 * Number of cards in the deck
	 */
	private static final int CARDS_COUNT  = 104;
	
	/**
	 * Number of possible suits of the cards
	 */
	private static final int SUITS_COUNT  = 4;
	
	/**
	 * Number of possible values of the cards
	 */
	private static final int VALUES_COUNT = 13;
	
	/**
	 * Deck used to create shuffled ones
	 */
	private static final ArrayList<Card> DEFAULT_DECK = new ArrayList<Card>(CARDS_COUNT);
	
	static {
		for (int suit = 0; suit < SUITS_COUNT; suit++) {
			for (int value = 0; value < VALUES_COUNT; value++) {
				DEFAULT_DECK.add(new Card(value, suit));
			}
		}
	}
	
	/**
	 * Current shuffled deck
	 */
	private final ArrayList<Card> deck;
	
	/**
	 * Create a deck with the specifed list of cards
	 * 
	 * @param deck
	 *            list of cards
	 */
	private CardsDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	/**
	 * Creates a shuffled deck of cards
	 * 
	 * @return a shuffled deck
	 */
	public static CardsDeck createDeck() {
		@SuppressWarnings("unchecked")
		ArrayList<Card> shuffledDeck = (ArrayList<Card>) DEFAULT_DECK.clone();
		
		// TODO: shuffle deck
		
		return new CardsDeck(shuffledDeck);
	}
	
	/**
	 * Returns the next card in the deck, or null if no cards are left
	 * 
	 * @return the next card in the deck, or null if no cards are left
	 */
	public Card nextCard() {
		if (this.deck.size() > 0) {
			return this.deck.remove(0);
		
		} else {
			return null;
		}
	}
}
