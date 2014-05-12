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
import static org.junit.internal.matchers.StringContains.containsString;
import static org.mockito.Mockito.*;


public class TicTacToeControllerTest {

    private static final String PLAYER_2_SYMBOL="o";
    private static final String PLAYER_1_SYMBOL="x";
    Board board;
    GameManager gameManager;

    TicTacToeController ticTacToeController;

    public TicTacToeControllerTest() {
        board = new Board();
        gameManager = new GameManager(board);
    }

    @Before
    public void setUp() throws Exception {

        ticTacToeController = new TicTacToeController(board, gameManager);
    }

    @Test
    public void shouldPutXInBoxZero() {

        String playerMoveInput = "0";
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

        String outOfScopeValue = "-1";

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
        String validPlayerMoveInput = "0";
        TicTacToeController anotherTicTacToeController = new TicTacToeController(board, stubGameManager);
        when(stubGameManager.currentPlayerSymbol()).thenReturn("X");

        anotherTicTacToeController.makeMove(validPlayerMoveInput);

        verify(stubGameManager).increment();

    }

    @Test
    public void shouldPutOIntoBoardWhenPlayer2Turn() throws Exception {
        Board mockBoard = mock(Board.class);
        GameManager stubGameManager = mock(GameManager.class);
        TicTacToeController anotherTicTacToeController = new TicTacToeController(mockBoard, stubGameManager);
        int playerMoveIndex = 2;

        int secondPlayerTurnNumber = 2;
        when(stubGameManager.getTurnNumber()).thenReturn(secondPlayerTurnNumber);
        when(mockBoard.get(anyInt())).thenReturn("");
        when(mockBoard.whoIsTheWinner()).thenReturn("");
        when(stubGameManager.currentPlayerSymbol()).thenReturn(PLAYER_2_SYMBOL);

        anotherTicTacToeController.makeMove(Integer.toString(playerMoveIndex));

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
        GameManager stubGameManager = mock(GameManager.class);

        int numberOfBoxesPlus1 = 10;
        when(stubGameManager.getTurnNumber()).thenReturn(numberOfBoxesPlus1);
        when(stubBoard.get(anyInt())).thenReturn("");
        when(stubBoard.whoIsTheWinner()).thenReturn("");

        TicTacToeController anotherTicTacToeController = new TicTacToeController(stubBoard, stubGameManager);
        String playerMoveIndex = "9";
        ModelAndView mav = anotherTicTacToeController.makeMove(playerMoveIndex);

        Integer numberOfTurns = (Integer) mav.getModel().get("turnNumber");

        assertThat(numberOfTurns, is(numberOfBoxesPlus1));
    }

//    TODO: Refactor this test to have fewer stubs
    @Test
    public void shouldAddWinningMessageToModel() throws Exception {
        Board stubBoard = mock(Board.class);
        GameManager stubGameManager = mock(GameManager.class);
        TicTacToeController anotherTicTacToeController = new TicTacToeController(stubBoard, stubGameManager);

        int turnNumber = 6;
        when(stubGameManager.getTurnNumber()).thenReturn(turnNumber);
        when(stubBoard.get(turnNumber)).thenReturn("");
        when(stubBoard.whoIsTheWinner()).thenReturn("x");

        String minimumMovesToWin = "6";
        ModelAndView mav = anotherTicTacToeController.makeMove(minimumMovesToWin);

        String winner = (String) mav.getModel().get("gameStatus");
        assertThat(winner, containsString("x wins!"));
    }
}