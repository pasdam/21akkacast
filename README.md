# 21akkacast
Demo of a blackjack (21) app, implemented with akka, hazelcast intended to be provided as OSGi bundle.

This is just a (pointless) demo of the integration of such technologies: it isn't a fully functional blackjack app, as not all messages are implemented/handled.

Dealer and players are modeled as akka actors. Gameplay messages are exchanged using the akka messaging system.
