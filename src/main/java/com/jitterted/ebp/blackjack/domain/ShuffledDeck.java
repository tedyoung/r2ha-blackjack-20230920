package com.jitterted.ebp.blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledDeck implements Deck {
    private final List<Card> cards = new ArrayList<>();

    public ShuffledDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    @Override
    public Card draw() {
        return cards.remove(0);
    }
}
