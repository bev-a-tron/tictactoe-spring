package com.swordfish.tictactoe;

public class Board {

    private String[] boxes;

    public void setBoxes(String[] boxes) {
        this.boxes = boxes;
    }

    public String get(int index) {
        return boxes[index];
    }

    public void put(int boxToBeUpdatedIndex) {
        boxes[boxToBeUpdatedIndex] = "x";
    }
}
