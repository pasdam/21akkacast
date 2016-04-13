package com.pasdam.gptest.service;

import com.pasdam.gptest.actors.DealerActor;

/**
 * OSGi service interface to instantiate a {@link DealerActor}
 * 
 * @author paco
 * @version 0.1
 */
public interface InstantiateDealer {

	/**
	 * Instantiate a {@link DealerActor} with the specified id
	 * 
	 * @param actorSytemName
	 *            name of the actor system
	 * @param dealerId
	 *            name of the dealer actor
	 * @return true in case of successful creation, false otherwise
	 */
	public boolean instantiate(String actorSytemName, String dealerId);
}
