package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.GameManager;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameManagerTest {
    Board stubBoard;
    GameManager gameManager;

    @Before
    public void setUp() throws Exception {

        stubBoard = mock(Board.class);
        gameManager = new GameManager(stubBoard);

    }

    @Test
    public void shouldCheckIfGameIsOverWhenSomebodyGets3InARow() throws Exception {


        //TODO: Concept missing
        when(stubBoard.get(0)).thenReturn("x");
        when(stubBoard.get(1)).thenReturn("x");
        when(stubBoard.get(2)).thenReturn("x");


        String winner = gameManager.whoIsTheWinner();

        assertThat(winner, is("X"));

    }

    @Test
    public void shouldCheckIfGameIsNotOverWhenNobodyHas3InARow() throws Exception {

        when(stubBoard.get(anyInt())).thenReturn("");

        String winner = gameManager.whoIsTheWinner();

        assertThat(winner, is(""));

    }

    @Test
    public void shouldReportTurnX() throws Exception {

        String status = gameManager.statusMessage();

        assertThat(status, is("X, it's your turn!"));

    }
}
