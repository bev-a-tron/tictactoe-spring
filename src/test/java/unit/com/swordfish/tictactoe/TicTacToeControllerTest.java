package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.TicTacToeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TicTacToeControllerTest {

    Board board;

    TicTacToeController ticTacToeController;

    public TicTacToeControllerTest() {
        board = new Board();
    }

    @Before
    public void setUp() throws Exception {

        ticTacToeController = new TicTacToeController(board);
    }

    @Test
    public void shouldPutXInBoxOne() {

        String playerMoveInput = "1";
        ticTacToeController.makeMove(playerMoveInput);

        assertThat(board.get(0), is("x"));
    }

    @Test
    public void shouldMakeModelAndViewWithBoard() throws Exception {
        String playerMoveInput = "1";

        ModelAndView mav = ticTacToeController.makeMove(playerMoveInput);

        assertEquals("tictactoe", mav.getViewName());
        assertTrue(mav.getModel().containsKey("board"));
    }
}
