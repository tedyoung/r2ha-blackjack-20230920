package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() {
        Game game = createGameAndDoInitialDeal(StubDeck.playerHitsAndGoesBust());

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo("You Busted, so you lose.  💸");
    }

    @Test
    void playerDealtBetterHandThanDealerAndStandsThenPlayerBeatsDealer() {
        Game game = createGameAndDoInitialDeal(StubDeck.playerStandsAndBeatsDealer());

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualTo("You beat the Dealer! 💵");
    }

    @Test
    void playerDealtHandWithSameValueAsDealerThenPlayerPushesDealer() {
        Game game = createGameAndDoInitialDeal(StubDeck.playerPushesDealer());

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualTo("Push: Nobody wins, we'll call it even.");
    }


    private static Game createGameAndDoInitialDeal(Deck deck) {
        Game game = new Game(deck);
        game.initialDeal();
        return game;
    }

}