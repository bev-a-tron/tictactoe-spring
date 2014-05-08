package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.Counter;
import com.swordfish.tictactoe.GameManager;
import com.swordfish.tictactoe.TicTacToeController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;
import static org.mockito.Mockito.*;


public class TicTacToeControllerTest {

    private static final String PLAYER_2_SYMBOL="o";
    private static final String PLAYER_1_SYMBOL="x";
    Board board;
    Counter counter;
    GameManager gameManager;

    TicTacToeController ticTacToeController;

    public TicTacToeControllerTest() {
        board = new Board();
        counter = new Counter();
        gameManager = new GameManager();
    }

    @Before
    public void setUp() throws Exception {

        ticTacToeController = new TicTacToeController(board, counter, gameManager);
    }

    @Test
    public void shouldPutXInBoxOne() {

        String playerMoveInput = "1";
        ticTacToeController.makeMove(playerMoveInput);

        assertThat(board.get(0), is(PLAYER_1_SYMBOL));
    }

    @Test
    public void shouldMakeModelAndViewWithBoard() throws Exception {
        String playerMoveInput = "1";

        ModelAndView mav = ticTacToeController.makeMove(playerMoveInput);

        assertEquals("tictactoe", mav.getViewName());
        assertTrue(mav.getModel().containsKey("board"));
    }

    @Test
    public void shouldAddErrorToModelWhenGivenOutOfScopeValue() {

        String outOfScopeValue = "0";

        ModelAndView mav = ticTacToeController.makeMove(outOfScopeValue);

        String error = (String) mav.getModel().get("errors");

        assertThat(error, containsString("Number out of range."));

    }

    @Test
    public void shouldAddStringErrorWhenANonNumericalValueIsPassedIn() {

        String nonIntegerValue = "string";

        ModelAndView mav = ticTacToeController.makeMove(nonIntegerValue);

        String error = (String) mav.getModel().get("errors");

        assertThat(error, containsString("Words are not allowed."));

    }

    @Test
    public void shouldTellCounterToIncrementWhenPlayerMovesSuccessfully() throws Exception {
        GameManager stubGameManager = mock(GameManager.class);
        Counter mockCounter = mock(Counter.class);
        String validPlayerMoveInput = "1";

        TicTacToeController anotherTicTacToeController = new TicTacToeController(board, mockCounter, stubGameManager);

        anotherTicTacToeController.makeMove(validPlayerMoveInput);

        verify(mockCounter).increment();

    }

    @Test
    public void shouldPutOIntoBoardWhenPlayer2Turn() throws Exception {
        Counter mockCounter = mock(Counter.class);
        Board mockBoard = mock(Board.class);
        GameManager stubGameManager = mock(GameManager.class);
        TicTacToeController anotherTicTacToeController = new TicTacToeController(mockBoard, mockCounter, stubGameManager);
        String playerMove = "3";
        int playerMoveIndex = Integer.parseInt(playerMove) - 1;

        int secondPlayerTurnNumber = 2;
        when(mockCounter.getTurnNumber()).thenReturn(secondPlayerTurnNumber);
        when(mockBoard.get(anyInt())).thenReturn("");

        anotherTicTacToeController.makeMove(playerMove);

        verify(mockBoard).put(playerMoveIndex, PLAYER_2_SYMBOL);
    }

    @Test
    public void shouldReceiveErrorWhenMovingToSamePosition() throws Exception {

        String playerMoveInputName = "1";
        ticTacToeController.makeMove(playerMoveInputName);
        ModelAndView mav = ticTacToeController.makeMove(playerMoveInputName);

        String error = (String) mav.getModel().get("errors");

        assertThat(error, containsString("You can't go there."));
    }

    @Test
    public void shouldAddEndOfGameMessageToModel() throws Exception {
        Board stubBoard = mock(Board.class);
        Counter stubCounter = mock(Counter.class);
        GameManager stubGameManager = mock(GameManager.class);

        int numberOfBoxesPlus1 = 10;
        when(stubCounter.getTurnNumber()).thenReturn(numberOfBoxesPlus1);
        when(stubBoard.get(anyInt())).thenReturn("");

        TicTacToeController anotherTicTacToeController = new TicTacToeController(stubBoard, stubCounter, stubGameManager);
        String playerMove = "9";
        ModelAndView mav = anotherTicTacToeController.makeMove(playerMove);

        Integer playAgainMessage = (Integer) mav.getModel().get("turnNumber");

        assertThat(playAgainMessage, is(numberOfBoxesPlus1));
    }

    @Test
    public void shouldAddWinningMessageToModel() throws Exception {
        Board stubBoard = mock(Board.class);
        Counter stubCounter = mock(Counter.class);
        GameManager stubGameManager = mock(GameManager.class);
        TicTacToeController anotherTicTacToeController = new TicTacToeController(stubBoard, stubCounter, stubGameManager);

        int turnNumber = 6;
        when(stubCounter.getTurnNumber()).thenReturn(turnNumber);
        when(stubBoard.get(turnNumber - 1)).thenReturn("");
        when(stubGameManager.whoIsTheWinner(stubBoard)).thenReturn("x");

        String minimumMovesToWin = "6";
        ModelAndView mav = anotherTicTacToeController.makeMove(minimumMovesToWin);

        String winner = (String) mav.getModel().get("gameStatus");
        assertThat(winner, containsString("x wins!"));
    }
}