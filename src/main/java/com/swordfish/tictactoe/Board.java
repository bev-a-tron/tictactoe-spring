package com.swordfish.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class Board {

    private String[] boxes = {"", "", "", "", "", "", "", "", ""};

    public String get(int index) {
        return boxes[index];
    }

    public void put(int boxToBeUpdatedIndex, String symbol) {
        boxes[boxToBeUpdatedIndex] = symbol;
    }
}
