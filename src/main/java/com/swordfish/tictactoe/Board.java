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

    public String whoIsTheWinner() {

        String winner = "";
        String box1 = boxes[0];
        String box2 = boxes[1];
        String box3 = boxes[2];
        String box4 = boxes[3];
        String box5 = boxes[4];
        String box6 = boxes[5];
        String box7 = boxes[6];
        String box8 = boxes[7];
        String box9 = boxes[8];

        if (areBoxesEqual(box1, box2, box3)) {
            winner = box1;
        } else if (areBoxesEqual(box4, box5, box6)) {
            winner = box4;
        } else if (areBoxesEqual(box7, box8, box9)) {
            winner = box7;
        } else if (areBoxesEqual(box1, box4, box7)) {
            winner = box1;
        } else if (areBoxesEqual(box2, box5, box8)) {
            winner = box2;
        } else if (areBoxesEqual(box3, box6, box9)) {
            winner = box3;
        } else if (areBoxesEqual(box1, box5, box9)) {
            winner = box1;
        } else if (areBoxesEqual(box3, box5, box7)) {
            winner = box3;
        }
        return winner.toUpperCase();
    }

    private boolean areBoxesEqual(String box1, String box2, String box3) {
        return box1.equals(box2) && box2.equals(box3) && !box1.equals("");
    }
}
