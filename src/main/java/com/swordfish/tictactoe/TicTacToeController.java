package com.swordfish.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicTacToeController {

    GameManager gameManager;
    Board board;
    Counter counter;

    @Autowired
    public TicTacToeController(Board board, Counter counter, GameManager gameManager) {
        this.board = board;
        this.counter = counter;
        this.gameManager = gameManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showBoard() {
        this.board = new Board();
        this.counter = new Counter();
        this.gameManager = new GameManager();

        ModelAndView mav = new ModelAndView("tictactoe");
        mav.addObject("gameStatus", gameManager.statusMessage());
        mav.addObject("board", board);

        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView makeMove(
            @RequestParam(value = "box-input", required = false) String playerMoveInputName) {
        String error = getError(playerMoveInputName);

        String winner = "";
        if (error.isEmpty()) {
            int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName) - 1;
            String symbol = getSymbol(counter.getTurnNumber());
            board.put(boxToBeUpdatedIndex, symbol);
            winner = gameManager.whoIsTheWinner(board);
            counter.increment();
        }

        String status = "";
        if (winner.equals("")) {
            status = gameManager.statusMessage();
        } else {
            status = winner + " wins!";
        }


        ModelAndView mav = new ModelAndView("tictactoe");

        mav.addObject("errors", error);
        mav.addObject("turnNumber", counter.getTurnNumber());
        mav.addObject("gameStatus", status);
        mav.addObject("board", board);

        return mav;
    }

    private String getSymbol(int turnNumber) {
        if (turnNumber % 2 == 1) {
            return "x";
        } else {
            return "o";
        }
    }

    private String getError(String playerMoveInputName) {
        String error = "";

        if (!isInteger(playerMoveInputName)) {
            error = "Words are not allowed. Please enter a number between 1 and 9.";
        } else if (!isInRange(playerMoveInputName)) {
            error = "Number out of range. Please enter a number between 1 and 9.";
        } else if (!isAvailable(playerMoveInputName)) {
            error = "You can't go there. Choose again.";
        }

        return error;
    }

    private boolean isAvailable(String playerMoveInputName) {
        int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName) - 1;
        return board.get(boxToBeUpdatedIndex).equals("");
    }

    private boolean isInRange(String playerMoveInputName) {
        int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName) - 1;
        return !((boxToBeUpdatedIndex < 0) || (boxToBeUpdatedIndex > 8));
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException numberFormat) {
            return false;
        }
        return true;
    }
}
