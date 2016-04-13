package com.pasdam.gptest.messages;

import com.pasdam.gptest.gameengine.Action;

/**
 * Response message to an action request 
 * 
 * @author paco
 * @version 0.1
 */
public class ActionResponseMessage extends PlayerMessage {
	
	private static final long serialVersionUID = -4932897315071497120L;

	/**
	 * Action chosen by player
	 */
	public final Action action;
	
	/**
	 * Message constructor
	 * 
	 * @param playerId
	 *            id of the player
	 * @param action
	 *            action chosen by player
	 */
	public ActionResponseMessage(int playerId, Action action) {
		super(playerId);
		this.action = action;
	}
}
