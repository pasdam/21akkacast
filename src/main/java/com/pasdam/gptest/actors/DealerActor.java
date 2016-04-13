package com.pasdam.gptest.actors;

import java.util.ArrayList;
import java.util.List;

import com.hazelcast.core.IAtomicReference;
import com.pasdam.gptest.Constraints;
import com.pasdam.gptest.gameengine.BlackJack;
import com.pasdam.gptest.gameengine.Card;
import com.pasdam.gptest.gameengine.CardsDeck;
import com.pasdam.gptest.gameengine.Turn;
import com.pasdam.gptest.messages.ActionResponseMessage;
import com.pasdam.gptest.messages.BetResponseMessage;
import com.pasdam.gptest.messages.CardDealtToPlayerMessage;
import com.pasdam.gptest.messages.PlayerSeatedMessage;
import com.pasdam.gptest.messages.ResultMessage;
import com.pasdam.gptest.messages.TurnMessage;

import akka.actor.ActorRef;
import akka.actor.Props;

/**
 * Dealer actor
 * 
 * @author paco
 * @version 0.1
 */
public class DealerActor extends AbstractActor {
	
	/** Prefix of players names */
	private static final String PLAYER_NAME_PREFIX = "player_";
	
	/** Indicates the current player with turn */
	private int currentPlayer = -1;
	
	/**	Indicates how many bets are received */
	private int betReceived = 0;
	
	/**	Deck of cards used in the current hand */
	private CardsDeck deck;
	
	/**	Score of the dealer */
	private int ownScore = 0;
	
	// TODO: save a list of cards, in order to evaluate ace values each turn
	/**	Scores of the players */
	private int[] playersPoints = new int[BlackJack.MAX_PLAYERS];

	// TODO: get actors reference dynamically
	/**	List of players */
	private List<ActorRef> players = new ArrayList<ActorRef>(BlackJack.MAX_PLAYERS);
	
	@Override
	public void preStart() {
		this.deck = CardsDeck.createDeck();
		
		// deal the first card to himself
		addOwnCard(this.deck.nextCard());
		
		// create player actors
		for (int i = 0; i < BlackJack.MAX_PLAYERS; i++) {
			// create a player actor
		    final ActorRef playerActor = getContext().actorOf(Props.create(PlayerActor.class), PLAYER_NAME_PREFIX + i);
		    this.players.add(i, playerActor);
		    // tell it seat index
		    playerActor.tell(new PlayerSeatedMessage(i), getSelf());
		    // ask to place a bet
		    playerActor.tell(new TurnMessage(i, Turn.BET), getSelf());
		}
	}

	@Override
	public void onActionResponseMessage(ActionResponseMessage message) {
		if (message.playerId == this.currentPlayer) {
			System.out.format("[DEALER] ActionResponseMessage {%d, %s}\n", message.playerId, message.action);
			
			switch (message.action) {
				case HIT:
					Card playerCard = this.deck.nextCard();
					updatePlayerScore(this.currentPlayer, playerCard);
					this.players.get(this.currentPlayer).tell(new CardDealtToPlayerMessage(this.currentPlayer, playerCard), getSelf());
					
					if (this.playersPoints[this.currentPlayer] >= BlackJack.MAX_POINT) {
						if (this.currentPlayer < BlackJack.MAX_PLAYERS - 1) {
							this.currentPlayer++;
						} else {
							break;
						}
					}
					
					this.players.get(this.currentPlayer).tell(new TurnMessage(this.currentPlayer, Turn.ACTION), getSelf());
					return;

				case STAND:
					// next player
					this.currentPlayer++;
					if (this.currentPlayer < BlackJack.MAX_PLAYERS) {
						this.players.get(this.currentPlayer).tell(new TurnMessage(this.currentPlayer, Turn.ACTION), getSelf());
						return;

					} else {
						break;
					}
			}

			while (this.ownScore <= BlackJack.MINIMUM_DEALER_POINT) {
				Card card = this.deck.nextCard();
				addOwnCard(card);

				for (int i = 0; i < BlackJack.MAX_PLAYERS; i++) {
					this.players.get(i).tell(new CardDealtToPlayerMessage(BlackJack.DEALER_SEAT_INDEX, card), getSelf());
				}
			}
			
			System.out.println("Dealer: " + this.ownScore);
			for (int i = 0; i < this.playersPoints.length; i++) {
				System.out.format("Player %d: %d\n", i, this.playersPoints[i]);
			}
			// TODO: evaluate points
			
			// shutdown the actor system
			getContext().system().shutdown();
			
		} else {
			System.err.format("[DEALER] ActionResponseMessage received not from the current player (%d): ignore it.", this.currentPlayer);
		}
	}

	@Override
	public void onBetResponseMessage(BetResponseMessage message) {
		System.out.println("[DEALER] BetResponseMessage received from player " + message.playerId);
		
		// TODO: save the bet
		
		this.betReceived++;
		if (this.betReceived == BlackJack.MAX_PLAYERS) {
			// TODO: send cards only to players in game
			Card playerCard;
			
			for (int i = 0; i < BlackJack.MAX_PLAYERS; i++) {
				playerCard = this.deck.nextCard();
				updatePlayerScore(i, playerCard);
				this.players.get(i).tell(new CardDealtToPlayerMessage(i, playerCard), getSelf());
			}
		
			// deal a card to himself
			Card dealerCard = this.deck.nextCard();
			addOwnCard(dealerCard);
			
			for (int i = 0; i < BlackJack.MAX_PLAYERS; i++) {
				this.players.get(i).tell(new CardDealtToPlayerMessage(BlackJack.DEALER_SEAT_INDEX, dealerCard), getSelf());
				
				playerCard = this.deck.nextCard();
				updatePlayerScore(i, playerCard);
				this.players.get(i).tell(new CardDealtToPlayerMessage(i, playerCard), getSelf());
				// TODO: broadcast the last event
			}
			
			// TODO: sort players by total points
			
			this.currentPlayer = 0;
			this.players.get(this.currentPlayer).tell(new TurnMessage(this.currentPlayer, Turn.ACTION), getSelf());
			// TODO: broadcast the last event
		}
	}

	/** Event ignored */
	@Override
	public void onCardDealtToPlayerMessage(CardDealtToPlayerMessage message) {
		System.err.println("[DEALER] Unexpected CardDealtToPlayerMessage receied: ignore it.");
	}
	
	/** Event ignored */
	@Override
	public void onPlayerSeatedMessage(PlayerSeatedMessage message) {
		System.err.println("[DEALER] Unexpected PlayerSeatedMessage receied: ignore it.");
	}
	
	/** Event ignored */
	@Override
	public void onResultMessage(ResultMessage message) {
		System.err.println("[DEALER] Unexpected ResultMessage receied: ignore it.");
	}

	/** Event ignored */
	@Override
	public void onTurnMessage(TurnMessage message) {
		System.err.println("[DEALER] Unexpected TurnMessage receied: ignore it.");
	}

	/**
	 * Adds the specified card to the dealer's score
	 * 
	 * @param card
	 *            card to add
	 */
	private void addOwnCard(Card card) {
		this.ownScore += pointValue(this.ownScore, card);
	}
	
	/**
	 * Update the score of the specified player
	 * 
	 * @param seatIndex
	 *            index of the player
	 * @param card
	 *            card to add
	 */
	private void updatePlayerScore(int seatIndex, Card card) {
		this.playersPoints[seatIndex] += pointValue(this.playersPoints[seatIndex], card);
		
		IAtomicReference<Integer> score = getHazelcastInstance().getAtomicReference(Constraints.PLAYER_SCORE_PREFIX + seatIndex);
		score.set(this.playersPoints[seatIndex]);
	}
	
	/**
	 * Returns the value of the specified card
	 * 
	 * @param playerScore
	 *            current score of the player
	 * @param card
	 *            card to get value of
	 * @return the value of the card (if card is an ace the value is determined
	 *         based on the current players score)
	 */
	public static int pointValue(int playerScore, Card card) {
		switch (card.value) {
			case 0:
				// Ace
				return playerScore+11 > BlackJack.MAX_POINT ? 1 : 11;
				
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
				// 2 to 9
				return card.value+1;
				
			case 9:
			case 10:
			case 11:
			case 12:
				// 10, J, Q, K
				return 10;
	
			default:
				return 0;
		}
	}
}
