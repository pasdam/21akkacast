package com.pasdam.gptest;

import com.pasdam.gptest.service.impl.InstantiateDealerImpl;

/**
 * Basic test of actor system
 * 
 * @author paco
 * @version 0.1
 */
public class TestMain {
	
	public static final String ACTOR_SYSTEM_NAME = "system";
	
	public static void main(String[] args) {
		new InstantiateDealerImpl().instantiate(ACTOR_SYSTEM_NAME, "did");
		
		// TODO: shutdown hazelcast instance
	}
}
