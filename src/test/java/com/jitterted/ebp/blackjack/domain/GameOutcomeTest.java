package com.jitterted.ebp.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

//    @Test
    void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() {
        Game game = new Game(new ShuffledDeck());
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo("You Busted, so you lose.  💸");
    }
}