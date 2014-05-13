package com.swordfish.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: What is gameManager?

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

//    TODO: make method names be verbs
    public String currentPlayerSymbol() {
        if (turnNumber % 2 == 1) {
            return "x";
        } else {
            return "o";
        }
    }

    //TODO: method is doing too many things. Rename method.
    public Board update(String playerMoveInputName) {

        //TODO: Controller should handle input formatting?
        //TODO: Make all lines the same level of abstraction
        int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName);
        String symbol = currentPlayerSymbol();
        board.drawSymbolInBox(boxToBeUpdatedIndex, symbol);
        increment();

        return board;
    }

    //TODO: polymorphism?
    public String gameStatusMessage(String winner) {
        if (winner.equals("")) {
            return statusMessage();
        } else {
            return winner + " wins!";
        }
    }
}
