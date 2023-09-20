package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.adapter.in.console.ConsoleCard;
import com.jitterted.ebp.blackjack.adapter.in.console.ConsoleHand;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    private boolean playerDone;

    public Game() {
        deck = new Deck();
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public void determineOutcome() {
        if (playerHand.isBusted()) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (dealerHand.isBusted()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
        } else if (playerHand.beats(dealerHand)) {
            System.out.println("You beat the Dealer! 💵");
        } else if (playerHand.pushes(dealerHand)) {
            System.out.println("Push: Nobody wins, we'll call it even.");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    public void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
    }

    public void displayGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(ConsoleHand.displayFaceUpCard(dealerHand()));

        // second card is the hole card, which is hidden, or "face down"
        ConsoleCard.displayBackOfCard();

        System.out.println();
        System.out.println("Player has: ");
        System.out.println(ConsoleHand.cardsAsString(playerHand()));
        System.out.println(" (" + playerHand().value() + ")");
    }

    public void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(ConsoleHand.cardsAsString(dealerHand()));
        System.out.println(" (" + dealerHand().value() + ")");

        System.out.println();
        System.out.println("Player has: ");
        System.out.println(ConsoleHand.cardsAsString(playerHand()));
        System.out.println(" (" + playerHand().value() + ")");
    }

    // 1. Return Hand instance field - breaks encapsulation (violates integrity of Game)
    // 1a. Return a COPY of Hand - however, it's a mutable type, misleads the caller
    // 1b. Return an interface "view" of the Hand, e.g., interface HandView - violates snapshot unless you return a copy
    // 2. Give up
    // 3. Hand.toString() - keep (user-facing) I/O separated from Domain code
    // 4. Stream<Card> - necessary, but not sufficient (need the Hand's value as well)
    // 5. New Type just holding Cards and Value
    // 5a. HandDto - getters for Cards & Value - DTOs live only in Adapters not the Domain
    // **==> 5b. HandValue - Value Object (part of the Domain) that has only Cards and Value
    //   ==> Similar to Snapshot pattern
    public Hand playerHand() {
        return playerHand;
    }

    public Hand dealerHand() {
        return dealerHand;
    }


    public void playerHits() {
        playerHand.drawFrom(deck);
        playerDone = playerHand.isBusted();
    }

    public void playerStands() {
        playerDone = true;
    }

    public boolean isPlayerDone() {
        return playerDone;
    }

}
