package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.domain.port.GameMonitor;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.spy;

class GameMonitorTest {

    @Test
    void playerStandsThenGameIsOverAndResultsSentToMonitor() {
        GameMonitor gameMonitorSpy = spy(GameMonitor.class);
        Game game = new Game(StubDeck.playerStandsAndBeatsDealer(), gameMonitorSpy);
    }
}