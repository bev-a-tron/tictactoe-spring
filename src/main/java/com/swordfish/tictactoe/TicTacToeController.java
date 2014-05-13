package com.swordfish.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TicTacToeController {

    private GameManager game;
    //TODO: controller and gamemanager both have a board.
    private Board board;

    @Autowired
    public TicTacToeController(Board board, GameManager game) {
        this.board = board;
        this.game = game;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showBoard() {
        this.board = new Board();
        this.game = new GameManager(board);

        ModelAndView mav = new ModelAndView("tictactoe");
        mav.addObject("gameStatus", game.statusMessage());
        mav.addObject("board", board);

        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView makeMove(
            @RequestParam(value = "box-input", required = false) String playerMoveInputName) {
            //TODO: change name of box-input, playerMoveInputName

        board = game.update(playerMoveInputName);

        ModelAndView mav = new ModelAndView("tictactoe");

        mav.addObject("turnNumber", game.getTurnNumber());
        mav.addObject("gameStatus", game.gameStatusMessage(board.whoIsTheWinner()));
        mav.addObject("board", board);

        mav.addObject("isGameOver", isGameOver());

        return mav;
    }

    private boolean isGameOver() {
        return board.isFull() || !board.whoIsTheWinner().equals("");
    }

}
