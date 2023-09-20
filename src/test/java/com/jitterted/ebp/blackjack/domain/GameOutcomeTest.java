package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() {
        Game game = createGameWithInitialDeal(StubDeck.playerHitsAndGoesBust());

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo("You Busted, so you lose.  💸");
    }

    @Test
    void playerDealtBetterHandThanDealerAndStandsThenPlayerBeatsDealer() {
        Game game = createGameWithInitialDeal(StubDeck.playerStandsAndBeatsDealer());

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualTo("You beat the Dealer! 💵");
    }

    private static Game createGameWithInitialDeal(Deck deck) {
        Game game = new Game(deck);
        game.initialDeal();
        return game;
    }

}