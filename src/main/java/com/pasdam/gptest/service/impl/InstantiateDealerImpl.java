package com.pasdam.gptest.service.impl;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.pasdam.gptest.Constraints;
import com.pasdam.gptest.actors.DealerActor;
import com.pasdam.gptest.service.InstantiateDealer;

import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Implementation of a service that instantiate a {@link DealerActor}
 * 
 * @author paco
 * @version 0.1
 */
public class InstantiateDealerImpl implements InstantiateDealer {

	@Override
	public boolean instantiate(String actorSytemName, String dealerId) {
		// configuring and create hazelcast instance
		Config config = new Config();
		config.setInstanceName(Constraints.HAZELCAST_INSTANCE_NAME);
		Hazelcast.getOrCreateHazelcastInstance(config);
		
		try {
			// create actor
			ActorSystem system = ActorSystem.create(actorSytemName);
			system.actorOf(Props.create(DealerActor.class), dealerId);
			
			return true;
			
		} catch (Exception exception) {
			return false;
		}
	}
}
