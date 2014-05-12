package com.swordfish.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class Board {

    private String[] boxes = {"", "", "", "", "", "", "", "", ""};

    public String getBoxContent(int index) {
        return boxes[index];
    }

    public void drawSymbolInBox(int boxToBeUpdatedIndex, String symbol) {
        boxes[boxToBeUpdatedIndex] = symbol;
    }

    public String whoIsTheWinner() {

        String winner = "";
        String box0 = boxes[0];
        String box1 = boxes[1];
        String box2 = boxes[2];
        String box3 = boxes[3];
        String box4 = boxes[4];
        String box5 = boxes[5];
        String box6 = boxes[6];
        String box7 = boxes[7];
        String box8 = boxes[8];

        if (areBoxesEqual(box0, box1, box2)) {
            winner = box0;
        } else if (areBoxesEqual(box3, box4, box5)) {
            winner = box3;
        } else if (areBoxesEqual(box6, box7, box8)) {
            winner = box6;
        } else if (areBoxesEqual(box0, box3, box6)) {
            winner = box0;
        } else if (areBoxesEqual(box1, box4, box7)) {
            winner = box1;
        } else if (areBoxesEqual(box2, box5, box8)) {
            winner = box2;
        } else if (areBoxesEqual(box0, box4, box8)) {
            winner = box0;
        } else if (areBoxesEqual(box2, box4, box6)) {
            winner = box2;
        }
        return winner.toUpperCase();
    }

    private boolean areBoxesEqual(String box1, String box2, String box3) {
        return box1.equals(box2) && box2.equals(box3) && !box1.equals("");
    }

    public Boolean isFull() {
        for (String box : boxes) {
            if (box.equals("")) {
                return false;
            }
        }
        return true;
    }
}
