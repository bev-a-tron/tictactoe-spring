package com.swordfish.tictactoe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicTacToeController {

    @RequestMapping("/")
    public String showBoard(){
        return "tictactoe";
    }

}
