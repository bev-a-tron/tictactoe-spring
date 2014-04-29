package com.swordfish.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicTacToeController {

    @Autowired
    TicTacToeService ticTacToeService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showBoard() {
        return "tictactoe";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String makeMove(
            @RequestParam(value = "player-move-input-name", required = false) String playerMoveInputName) {
        ticTacToeService.updateModel(playerMoveInputName);
        return "tictactoe";
    }


}
