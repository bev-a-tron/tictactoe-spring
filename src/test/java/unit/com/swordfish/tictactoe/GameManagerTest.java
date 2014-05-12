package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.GameManager;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

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
}
