package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class GameMonitorTest {

    @Test
    void playerStandsThenGameIsOverAndResultsSentToMonitor() {
        Fixture fixture = createGameWithMonitorSpy(StubDeck.playerStandsAndBeatsDealer());

        fixture.game.playerStands();

        verify(fixture.gameMonitorSpy).roundCompleted(fixture.game);
    }

    @Test
    void playerHitsAndGoesBustThenResultsSentToMonitor() {
        Fixture fixture = createGameWithMonitorSpy(StubDeck.playerHitsAndGoesBust());

        fixture.game.playerHits();

        verify(fixture.gameMonitorSpy).roundCompleted(fixture.game);
    }

    @Test
    void playerHitsAndDoesNotBustThenResultsNotSentToMonitor() {
        Fixture fixture = createGameWithMonitorSpy(StubDeck.playerHitsAndDoesNotBust());

        fixture.game.playerHits();

        verify(fixture.gameMonitorSpy, never()).roundCompleted(fixture.game);
    }

    @Test
    void playerDealtBlackjackThenResultsSentToMonitor() {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(StubDeck.playerDealtBlackjackDealerNotDealtBlackjack(), gameMonitorSpy);

        game.initialDeal();

        verify(gameMonitorSpy).roundCompleted(game);
    }


    private Fixture createGameWithMonitorSpy(Deck deck) {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(deck, gameMonitorSpy);
        game.initialDeal();
        return new Fixture(gameMonitorSpy, game);
    }
    private static class Fixture {

        public final GameMonitor gameMonitorSpy;
        public final Game game;

        public Fixture(GameMonitor gameMonitorSpy, Game game) {
            this.gameMonitorSpy = gameMonitorSpy;
            this.game = game;
        }
    }
}