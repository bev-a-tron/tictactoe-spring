package com.swordfish.tictactoe;

import org.springframework.stereotype.Component;

@Component
public class GameManager {

    public String whoIsTheWinner(Board board) {

        String box1 = board.get(0);
        String box2 = board.get(1);
        String box3 = board.get(2);
        String box4 = board.get(3);
        String box5 = board.get(4);
        String box6 = board.get(5);
        String box7 = board.get(6);
        String box8 = board.get(7);
        String box9 = board.get(8);

        if (areBoxesEqual(box1, box2, box3)) {
            return box1;
        } else if (areBoxesEqual(box4, box5, box6)) {
            return box4;
        } else if (areBoxesEqual(box7, box8, box9)) {
            return box7;
        } else if (areBoxesEqual(box1, box4, box7)) {
            return box1;
        } else if (areBoxesEqual(box2, box5, box8)) {
            return box2;
        } else if (areBoxesEqual(box3, box6, box9)) {
            return box3;
        } else if (areBoxesEqual(box1, box5, box9)) {
            return box1;
        } else if (areBoxesEqual(box3, box5, box7)) {
            return box3;
        }
        return "";
    }

    private boolean areBoxesEqual(String box1, String box2, String box3) {
        return box1.equals(box2) && box2.equals(box3) && !box1.equals("");
    }

}
