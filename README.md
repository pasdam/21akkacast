# 21akkacast
Demo of a blackjack (21) app, implemented with akka, hazelcast intended to be provided as OSGi bundle.

This is just a (pointless) demo of the integration of such technologies: it isn't a fully functional blackjack app, as not all messages are implemented/handled.

Dealer and players are modeled as akka actors. Gameplay messages are exchanged using the akka messaging system.

Dependencies:
* [akka-actor_2.10-2.3.15.jar](http://mvnrepository.com/artifact/com.typesafe.akka/akka-actor_2.10/2.3.15);
* [scala-library-2.10.4.jar](http://mvnrepository.com/artifact/org.scala-lang/scala-library/2.10.4);
* [config-1.2.1.jar](http://mvnrepository.com/artifact/com.typesafe/config/1.2.1);
* [hazelcast-all-3.6.1.jar](http://mvnrepository.com/artifact/com.hazelcast/hazelcast-all/3.6.1).
This jar has to be copied into the bundle folder of the container.

The bundle was tested in the [Apache Felix](https://felix.apache.org) OSGi container.
