package com.jitterted.ebp.blackjack.domain;

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

    public String determineOutcome() {
        if (playerHand.isBusted()) {
            return "You Busted, so you lose.  💸";
        } else if (dealerHand.isBusted()) {
            return "Dealer went BUST, Player wins! Yay for you!! 💵";
        } else if (playerHand.beats(dealerHand)) {
            return "You beat the Dealer! 💵";
        } else if (playerHand.pushes(dealerHand)) {
            return "Push: Nobody wins, we'll call it even.";
        } else {
            return "You lost to the Dealer. 💸";
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
