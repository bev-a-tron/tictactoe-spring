package com.swordfish.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class Counter {
    private int turnNumber = 1;

    public void increment() {
        turnNumber++;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
