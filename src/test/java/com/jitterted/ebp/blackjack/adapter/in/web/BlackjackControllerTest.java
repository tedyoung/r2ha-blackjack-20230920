package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.ShuffledDeck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BlackjackControllerTest {

    @Test
    void startGameResultsInTwoCardsDealtToPlayer() {
        Game game = new Game(new ShuffledDeck());
        BlackjackController blackjackController = new BlackjackController(game);

        String redirectPage = blackjackController.startGame();

        assertThat(game.playerHand().cards())
                .hasSize(2);
        assertThat(redirectPage)
                .isEqualTo("redirect:/game");
    }
}