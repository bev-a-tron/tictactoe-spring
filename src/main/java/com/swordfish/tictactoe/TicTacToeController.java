package com.swordfish.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicTacToeController {

    Board board;

    @Autowired
    public TicTacToeController(Board board) {
        this.board = board;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBoard() {
        return "tictactoe";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView makeMove(
            @RequestParam(value = "player-move-input-name", required = false) String playerMoveInputName) {

        boolean integerInput = validateIntegerInput(playerMoveInputName);
        String error = "";
        if (integerInput) {

            int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName) - 1;

            if ((boxToBeUpdatedIndex < 0) || (boxToBeUpdatedIndex > 8)) {
                error = "Number out of range.  Please enter a number between 1 and 9.";
            } else {
                board.put(boxToBeUpdatedIndex);
            }
        } else {
            error = "Words are not allowed. Please enter a number between 1 and 9.";
        }

        ModelAndView mav = new ModelAndView("tictactoe");

        mav.addObject("errors", error);
        mav.addObject("box1", board.get(0));
        mav.addObject("box2", board.get(1));
        mav.addObject("box3", board.get(2));
        mav.addObject("box4", board.get(3));
        mav.addObject("box5", board.get(4));
        mav.addObject("box6", board.get(5));
        mav.addObject("box7", board.get(6));
        mav.addObject("box8", board.get(7));
        mav.addObject("box9", board.get(8));

        return mav;
    }

    private boolean validateIntegerInput(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException numberFormat) {
            return false;
        }
        return true;
    }
}
