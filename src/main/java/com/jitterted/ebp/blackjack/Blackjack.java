package com.jitterted.ebp.blackjack;

// this is the COMPOSITION ROOT
// also called the CONFIGURATOR or ASSEMBLER
public class Blackjack {

    // Create, Assemble & Configure objects
    // Transient
    public static void main(String[] args) {
        Game game = new Game(); // Entity-like object - live inside the Hexagon
        ConsoleGame consoleGame = new ConsoleGame(game); // in general: Entities aren't directly passed in to Adapters
        consoleGame.start();
    }

}
