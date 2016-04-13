package com.pasdam.gptest.messages;

/**
 * Interface implemented by dealer/player actors
 * 
 * @author paco
 * @version 0.1
 */
public interface MessageHandler {

	/**
	 * Handles a {@link ActionResponseMessage}
	 * 
	 * @param message
	 *            message received
	 */
	public void onActionResponseMessage(ActionResponseMessage message);

	/**
	 * Handles a {@link BetResponseMessage}
	 * 
	 * @param message
	 *            message received
	 */
	public void onBetResponseMessage(BetResponseMessage message);

	/**
	 * Handles a {@link CardDealtToPlayerMessage}
	 * 
	 * @param message
	 *            message received
	 */
	public void onCardDealtToPlayerMessage(CardDealtToPlayerMessage message);
	
	/**
	 * Handles a {@link PlayerSeatedMessage}
	 * 
	 * @param message
	 *            message received
	 */
	public void onPlayerSeatedMessage(PlayerSeatedMessage message);

	/**
	 * Handles a {@link ResultMessage}
	 * 
	 * @param message
	 *            message received
	 */
	public void onResultMessage(ResultMessage message);

	/**
	 * Handles a {@link TurnMessage}
	 * 
	 * @param message
	 *            message received
	 */
	public void onTurnMessage(TurnMessage message);
}
