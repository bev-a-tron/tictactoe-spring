package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.GameManager;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameManagerTest {
    Board stubBoard;
    GameManager gameManager;

    @Before
    public void setUp() throws Exception {

        stubBoard = mock(Board.class);
        gameManager = new GameManager(stubBoard);

    }

    @Test
    public void shouldReportTurnX() throws Exception {

        String status = gameManager.statusMessage();

        assertThat(status, is("X, it's your turn!"));

    }

    @Test
    public void shouldReportTurnO() throws Exception {

        gameManager.increment();
        String status = gameManager.statusMessage();

        assertThat(status, is("O, it's your turn!"));

    }

    @Test
    public void shouldIncrementCounter() {

        gameManager.increment();

        MatcherAssert.assertThat(gameManager.getTurnNumber(), is(2));
    }

    @Test
    public void shouldIncrementCounterAgain() {

        gameManager.increment();
        gameManager.increment();

        MatcherAssert.assertThat(gameManager.getTurnNumber(), is(3));

    }

    @Test
    public void shouldTellCounterToIncrementWhenPlayerMovesSuccessfully() throws Exception {
        String validPlayerMoveInput = "0";

        gameManager.update(validPlayerMoveInput);

        assertThat(gameManager.getTurnNumber(), is(2));
    }

    @Test
    public void shouldUpdateWithCurrentPlayerSymbol() throws Exception {
        Board board = mock(Board.class);

        GameManager gameManager = new GameManager(board);

        String boxToUpdate = "1";
        gameManager.update(boxToUpdate);

        assertThat(gameManager.currentPlayerSymbol(), is("o"));
        verify(board).drawSymbolInBox(Integer.parseInt(boxToUpdate), "x");
    }
}
