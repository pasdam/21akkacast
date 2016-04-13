package com.pasdam.gptest.gameengine;

/**
 * Enumeration of all possible actions
 * 
 * @author paco
 * @version 0.1
 */
public enum Action {
	
	/**
	 * Take another card from the dealer and choose again
	 */
	HIT,
	
	/**
	 * Take no more cards: ends of the turn
	 */
	STAND
}