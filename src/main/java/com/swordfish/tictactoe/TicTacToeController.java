package com.swordfish.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicTacToeController {

    private GameManager gameManager;
    private Board board;

    @Autowired
    public TicTacToeController(Board board, GameManager gameManager) {
        this.board = board;
        this.gameManager = gameManager;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showBoard() {
        this.board = new Board();
        this.gameManager = new GameManager(board);

        ModelAndView mav = new ModelAndView("tictactoe");
        mav.addObject("gameStatus", gameManager.statusMessage());
        mav.addObject("board", board);

        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView makeMove(
            @RequestParam(value = "box-input", required = false) String playerMoveInputName) {

        String error = getError(playerMoveInputName);

        if (error.isEmpty()) {
            board = gameManager.update(playerMoveInputName);

        }

        ModelAndView mav = new ModelAndView("tictactoe");

        mav.addObject("errors", error);
        mav.addObject("turnNumber", gameManager.getTurnNumber());
        mav.addObject("gameStatus", gameManager.gameStatusMessage(board.whoIsTheWinner()));
        mav.addObject("board", board);
        mav.addObject("isGameOver", isGameOver());

        return mav;
    }

    private Boolean isGameOver() {
        if (board.isFull() || !board.whoIsTheWinner().equals("")) {
            return true;
        }
        return false;
    }

    private String getError(String playerMoveInputName) {
        String error = "";

        if (!isInteger(playerMoveInputName)) {
            error = "Words are not allowed.";
        } else if (!isInRange(playerMoveInputName)) {
            error = "Number out of range.";
        } else if (!isAvailable(playerMoveInputName)) {
            error = "You can't go there. Choose again.";
        }

        return error;
    }

    private boolean isAvailable(String playerMoveInputName) {
        int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName);
        return board.getBoxContent(boxToBeUpdatedIndex).equals("");
    }

    private boolean isInRange(String playerMoveInputName) {
        int boxToBeUpdatedIndex = Integer.parseInt(playerMoveInputName);
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
