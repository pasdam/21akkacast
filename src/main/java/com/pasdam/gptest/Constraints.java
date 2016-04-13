package com.pasdam.gptest;

/**
 * Utility class with application constraints
 * 
 * @author paco
 * @version 0.1
 */
public class Constraints {

	/**
	 * Name of the hazelcast instance
	 */
	public static final String HAZELCAST_INSTANCE_NAME = "hzInstance";
	
	/**
	 * Prefix of the player score, used to retrieve hazelcast atomics
	 */
	public static final String PLAYER_SCORE_PREFIX = "scorePlayer";
	
	/**	Private constructor: prevent instantiation */
	private Constraints() {}
}
