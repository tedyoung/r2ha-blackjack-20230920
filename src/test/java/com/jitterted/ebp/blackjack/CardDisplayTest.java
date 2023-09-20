package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardDisplayTest {

    @Test
    void displayTenAsString() {
        Card card = new Card(Suit.CLUBS, Rank.TEN);

        assertThat(card.display())
                .isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    void displayNonTenAsString() {
        Card card = new Card(Suit.SPADES, Rank.FOUR);

        assertThat(card.display())
                .isEqualTo("[30m┌─────────┐[1B[11D│4        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        4│[1B[11D└─────────┘");
    }
}
