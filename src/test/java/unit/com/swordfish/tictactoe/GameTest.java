package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.Game;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameTest {
    Board stubBoard;
    Game game;

    @Before
    public void setUp() throws Exception {

        stubBoard = mock(Board.class);
        game = new Game(stubBoard);

    }

    @Test
    public void shouldReportTurnX() throws Exception {

        String status = game.statusMessage();

        assertThat(status, is("X, it's your turn!"));

    }

    @Test
    public void shouldReportTurnO() throws Exception {

        game.update("0");
        String status = game.statusMessage();

        assertThat(status, is("O, it's your turn!"));

    }

    @Test
    public void shouldIncrementCounter() {

        game.update("0");

        MatcherAssert.assertThat(game.getTurnNumber(), is(2));
    }

    @Test
    public void shouldIncrementCounterAgain() {

        game.update("0");
        game.update("1");

        MatcherAssert.assertThat(game.getTurnNumber(), is(3));

    }

    @Test
    public void shouldTellCounterToIncrementWhenPlayerMovesSuccessfully() throws Exception {
        String validPlayerMoveInput = "0";

        game.update(validPlayerMoveInput);

        //TODO: Extract var
        assertThat(game.getTurnNumber(), is(2));
    }

    //TODO: Method name is not clear. Split into two tests?
    @Test
    public void shouldUpdateWithCurrentPlayerSymbol() throws Exception {
        Board board = mock(Board.class);

        Game game = new Game(board);

        String boxToUpdate = "1";
        game.update(boxToUpdate);

        verify(board).drawSymbolInBox(Integer.parseInt(boxToUpdate), "x");
        assertThat(game.currentPlayerSymbol(), is("o"));
    }
}
