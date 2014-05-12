package com.swordfish.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameManager {

    private int turnNumber = 1;
    public Board board;

    @Autowired
    public GameManager(Board board) {
        this.board = board;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void increment() {
        turnNumber++;
    }

    public String statusMessage() {

        return currentPlayerSymbol().toUpperCase() + ", it's your turn!";
    }

    public String currentPlayerSymbol() {
        if (turnNumber % 2 == 1) {
            return "x";
        } else {
            return "o";
        }
    }
}
