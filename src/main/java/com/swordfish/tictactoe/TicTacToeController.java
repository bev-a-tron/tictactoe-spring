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

    public TicTacToeController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBoard() {
        return "tictactoe";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView makeMove(
            @RequestParam(value = "player-move-input-name", required = false) String playerMoveInputName) {

        int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName) - 1;

        board.put(boxToBeUpdatedIndex);
        ModelAndView mav = new ModelAndView("tictactoe");
        mav.addObject("board", board);

        return mav;
    }


}
