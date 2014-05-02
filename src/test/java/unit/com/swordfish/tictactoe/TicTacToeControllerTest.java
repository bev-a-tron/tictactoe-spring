package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.Counter;
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

    TicTacToeController ticTacToeController;

    public TicTacToeControllerTest() {
        board = new Board();
        counter = new Counter();
    }

    @Before
    public void setUp() throws Exception {

        ticTacToeController = new TicTacToeController(board, counter);
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
        assertTrue(mav.getModel().containsKey("box1"));
        assertTrue(mav.getModel().containsKey("box2"));
        assertTrue(mav.getModel().containsKey("box3"));
        assertTrue(mav.getModel().containsKey("box4"));
        assertTrue(mav.getModel().containsKey("box5"));
        assertTrue(mav.getModel().containsKey("box6"));
        assertTrue(mav.getModel().containsKey("box7"));
        assertTrue(mav.getModel().containsKey("box8"));
        assertTrue(mav.getModel().containsKey("box9"));
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

        Counter mockCounter = mock(Counter.class);
        String validPlayerMoveInput = "1";

        TicTacToeController anotherTicTacToeController = new TicTacToeController(board, mockCounter);

        anotherTicTacToeController.makeMove(validPlayerMoveInput);

        verify(mockCounter).increment();

    }

    @Test
    public void shouldPutOIntoBoardWhenPlayer2Turn() throws Exception {
        Counter mockCounter = mock(Counter.class);
        Board mockBoard = mock(Board.class);
        TicTacToeController anotherTicTacToeController = new TicTacToeController(mockBoard, mockCounter);
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

        int numberOfBoxesPlus1 = 10;
        when(stubCounter.getTurnNumber()).thenReturn(numberOfBoxesPlus1);
        when(stubBoard.get(anyInt())).thenReturn("");

        TicTacToeController anotherTicTacToeController = new TicTacToeController(stubBoard, stubCounter);
        String playerMove = "9";
        ModelAndView mav = anotherTicTacToeController.makeMove(playerMove);

        Integer playAgainMessage = (Integer) mav.getModel().get("turnNumber");

        assertThat(playAgainMessage, is(numberOfBoxesPlus1));
    }
}