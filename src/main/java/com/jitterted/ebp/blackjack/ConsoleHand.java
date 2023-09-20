package com.jitterted.ebp.blackjack;

public class ConsoleHand {

    // DOMAIN --transforms--> I/O, Hand --> String
    static String displayFaceUpCard(Hand hand) {
        return ConsoleCard.display(hand.faceUpCard());
    }
}
