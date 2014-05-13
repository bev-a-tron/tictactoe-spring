package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.GameManager;
import com.swordfish.tictactoe.TicTacToeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class TicTacToeControllerTest {

    private static final String PLAYER_1_SYMBOL="x";

    Board board;
    GameManager gameManager;
    TicTacToeController ticTacToeController;

    @Before
    public void setUp() throws Exception {

        board = new Board();
        gameManager = new GameManager(board);
        ticTacToeController = new TicTacToeController(board, gameManager);
    }

    @Test
    public void shouldPutXInBoxZero() {

        String playerMoveInput = "0";
        ticTacToeController.makeMove(playerMoveInput);

        assertThat(board.getBoxContent(0), is(PLAYER_1_SYMBOL));
    }

    @Test
    public void shouldMakeModelAndViewWithBoard() throws Exception {
        String playerMoveInput = "1";

        ModelAndView mav = ticTacToeController.makeMove(playerMoveInput);

        assertEquals("tictactoe", mav.getViewName());
        assertTrue(mav.getModel().containsKey("board"));
    }

    //TODO: Too many stubs. Checking too much?
    @Test
    public void shouldUpdateTheBoardAndGetTheWinner() throws Exception {
        Board board = mock(Board.class);
        GameManager gameManager = mock(GameManager.class);
        TicTacToeController anotherTicTacToeController = new TicTacToeController(board, gameManager);
        int playerMoveIndex = 2;

        int secondPlayerTurnNumber = 2;
        when(gameManager.getTurnNumber()).thenReturn(secondPlayerTurnNumber);
        when(gameManager.update(anyString())).thenReturn(board);
        when(board.getBoxContent(anyInt())).thenReturn("");
        when(board.whoIsTheWinner()).thenReturn("");

        anotherTicTacToeController.makeMove(Integer.toString(playerMoveIndex));

        verify(gameManager).update(Integer.toString(playerMoveIndex));
        verify(board, times(2)).whoIsTheWinner();
    }

    @Test
    public void shouldTellOThatItIsHisTurn() throws Exception {

        String playerMoveInputName = "0";

        ModelAndView mav = ticTacToeController.makeMove(playerMoveInputName);

        assertThat((String) mav.getModel().get("gameStatus"), is("O, it's your turn!"));

    }

    @Test
    public void shouldTellXThatItIsHisTurn() throws Exception {

        String playerMoveInputName = "0";
        String anotherPlayerMoveInputName = "1";

        ticTacToeController.makeMove(playerMoveInputName);
        ModelAndView mav = ticTacToeController.makeMove(anotherPlayerMoveInputName);

        assertThat((String) mav.getModel().get("gameStatus"), is("X, it's your turn!"));

    }

    @Test
    public void shouldTellXThatSheWon() throws Exception {

        Board xWonBoard = new Board();
        xWonBoard.drawSymbolInBox(0, "x");
        xWonBoard.drawSymbolInBox(1, "x");

        GameManager xWonGameManager = new GameManager(xWonBoard);

        TicTacToeController xWonTicTacToeController = new TicTacToeController(xWonBoard, xWonGameManager);
        ModelAndView mav = xWonTicTacToeController.makeMove("2");

        assertThat((String) mav.getModel().get("gameStatus"), is("X wins!"));

    }


    //TODO: looks large, reduce stub
    @Test
    public void shouldShowEndOfGameMessageWhen9BoxesFull() throws Exception {
        Board fullBoard = mock(Board.class);

        when(fullBoard.isFull()).thenReturn(true);
        when(fullBoard.getBoxContent(8)).thenReturn("x");
        when(fullBoard.whoIsTheWinner()).thenReturn("");
        GameManager fullBoardGameManager = new GameManager(fullBoard);

        TicTacToeController anotherTicTacToeController = new TicTacToeController(fullBoard, fullBoardGameManager);

        String playerMoveIndex = "8";

        ModelAndView mav = anotherTicTacToeController.makeMove(playerMoveIndex);

        assertThat((Boolean) mav.getModel().get("isGameOver"), is(true));
    }

    @Test
    public void shouldNotShowEndOfGameMessageWhenBoxesAreNotFull() throws Exception {
        Board board = mock(Board.class);

        when(board.isFull()).thenReturn(false);
        when(board.getBoxContent(anyInt())).thenReturn("x");
        when(board.whoIsTheWinner()).thenReturn("");
        GameManager gameManager = new GameManager(board);

        TicTacToeController ticTacToeController = new TicTacToeController(board, gameManager);

        String playerMoveIndex = "8";

        ModelAndView mav = ticTacToeController.makeMove(playerMoveIndex);

        assertThat((Boolean) mav.getModel().get("isGameOver"), is(false));
    }

    @Test
    public void shouldGetGameStatusMessage() throws Exception {
        Board board = mock(Board.class);
        GameManager gameManager = mock(GameManager.class);

        TicTacToeController anotherTicTacToeController = new TicTacToeController(board, gameManager);

        int turnNumber = 6;
        when(gameManager.getTurnNumber()).thenReturn(turnNumber);
        when(board.getBoxContent(turnNumber)).thenReturn("");
        when(board.whoIsTheWinner()).thenReturn("x");
        when(gameManager.update(Integer.toString(turnNumber))).thenReturn(board);
        when(gameManager.gameStatusMessage("x")).thenReturn("x wins!");

        String minimumMovesToWin = "6";
        anotherTicTacToeController.makeMove(minimumMovesToWin);

        verify(gameManager).gameStatusMessage("x");
    }
}