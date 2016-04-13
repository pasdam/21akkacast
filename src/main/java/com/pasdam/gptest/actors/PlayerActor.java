package com.pasdam.gptest.actors;

import com.hazelcast.core.IAtomicReference;
import com.pasdam.gptest.Constraints;
import com.pasdam.gptest.gameengine.Action;
import com.pasdam.gptest.gameengine.BlackJack;
import com.pasdam.gptest.messages.ActionResponseMessage;
import com.pasdam.gptest.messages.BetResponseMessage;
import com.pasdam.gptest.messages.CardDealtToPlayerMessage;
import com.pasdam.gptest.messages.PlayerSeatedMessage;
import com.pasdam.gptest.messages.ResultMessage;
import com.pasdam.gptest.messages.TurnMessage;

import scala.util.Random;

/**
 * Player actor
 * 
 * @author paco
 * @version 0.1
 */
public class PlayerActor extends AbstractActor {
	
	// TODO: handle player's seat index
	/**	Seat index of the player */
	private int seatIndex = BlackJack.DEALER_SEAT_INDEX-1;

	/** Event ignored */
	@Override
	public void onActionResponseMessage(ActionResponseMessage message) {
		System.err.println("[PLAYER-" + seatIndex + "] Unexpected ActionResponseMessage: ignore it.");
	}

	/** Event ignored */
	@Override
	public void onBetResponseMessage(BetResponseMessage message) {
		System.err.println("[PLAYER-" + seatIndex + "] Unexpected BetResponseMessage: ignore it.");
	}

	@Override
	public void onCardDealtToPlayerMessage(CardDealtToPlayerMessage message) {
		if (message.seatIndex == this.seatIndex) {

			IAtomicReference<Integer> score = getHazelcastInstance().getAtomicReference(Constraints.PLAYER_SCORE_PREFIX + this.seatIndex);
			
			System.out.format("[PLAYER-%d] CardDealtToPlayerMessage {card %s -> score=%d}\n", this.seatIndex, BlackJack.cardToString(message.card), score.get());
		
		} else if (message.seatIndex == BlackJack.DEALER_SEAT_INDEX) {
			System.out.format("[PLAYER-%d] CardDealtToPlayerMessage {card %s to dealer}\n", this.seatIndex, BlackJack.cardToString(message.card));
		
		} else {
			System.out.format("[PLAYER-%d] CardDealtToPlayerMessage {card %s to player %d}\n", this.seatIndex, BlackJack.cardToString(message.card), message.seatIndex);
		}
	}
	
	@Override
	public void onPlayerSeatedMessage(PlayerSeatedMessage message) {
		this.seatIndex = message.seatIndex;
		
		System.out.println("[PLAYER-" + seatIndex + "] PlayerSeatedMessage received");
	}

	@Override
	public void onResultMessage(ResultMessage message) {
		System.out.println("[PLAYER-" + seatIndex + "] ResultMessage received");
		
		// TODO: missing implementation
	}

	@Override
	public void onTurnMessage(TurnMessage message) {
		System.out.format("[PLAYER-%d] TurnMessage {%s}\n", this.seatIndex, message.turn.toString());
		
		switch (message.turn) {
			case BET:
				getSender().tell(new BetResponseMessage(this.seatIndex, 50), getSelf());
				return;
				
			case ACTION:
				IAtomicReference<Integer> score = getHazelcastInstance().getAtomicReference(Constraints.PLAYER_SCORE_PREFIX + this.seatIndex);
				getSender().tell(new ActionResponseMessage(this.seatIndex, score.get() > 12 && !new Random().nextBoolean() ? Action.STAND : Action.HIT), getSelf());
				return;
	
			default:
				return;
		}
	}
}
