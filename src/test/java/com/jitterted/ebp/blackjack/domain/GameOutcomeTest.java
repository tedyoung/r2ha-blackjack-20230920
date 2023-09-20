package com.jitterted.ebp.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

//    @Test
    void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() {
        Game game = new Game(new Deck());
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo("You Busted, so you lose.  💸");
    }
}