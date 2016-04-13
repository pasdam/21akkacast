package com.pasdam.gptest.actors;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.pasdam.gptest.Constraints;
import com.pasdam.gptest.messages.ActionResponseMessage;
import com.pasdam.gptest.messages.BetResponseMessage;
import com.pasdam.gptest.messages.CardDealtToPlayerMessage;
import com.pasdam.gptest.messages.MessageHandler;
import com.pasdam.gptest.messages.PlayerSeatedMessage;
import com.pasdam.gptest.messages.ResultMessage;
import com.pasdam.gptest.messages.TurnMessage;

import akka.actor.UntypedActor;

/**
 * Abstract actor 
 * 
 * @author paco
 * @version 0.1
 */
abstract class AbstractActor extends UntypedActor implements MessageHandler {
	
	/**
	 * Hazelcast instance used to store/load atomics
	 */
	private static HazelcastInstance hazelcastInstance;
	
	@Override
	public void onReceive(Object message) throws Exception {
		if (message instanceof ActionResponseMessage) {
			onActionResponseMessage((ActionResponseMessage) message);
		
		} else if (message instanceof BetResponseMessage) {
			onBetResponseMessage((BetResponseMessage) message);
		
		} else if (message instanceof CardDealtToPlayerMessage) {
			onCardDealtToPlayerMessage((CardDealtToPlayerMessage) message);

		} else if (message instanceof PlayerSeatedMessage) {
			onPlayerSeatedMessage((PlayerSeatedMessage) message);
		
		} else if (message instanceof ResultMessage) {
			onResultMessage((ResultMessage) message);
		
		} else if (message instanceof TurnMessage) {
			onTurnMessage((TurnMessage) message);
		}
	}
	
	/**
	 * Returns the hazelcast singleton instance
	 * 
	 * @return the hazelcast singleton instance
	 */
	protected static HazelcastInstance getHazelcastInstance() {
		if (hazelcastInstance == null) {
			hazelcastInstance = Hazelcast.getHazelcastInstanceByName(Constraints.HAZELCAST_INSTANCE_NAME);
		}
		return hazelcastInstance;
	}
}
