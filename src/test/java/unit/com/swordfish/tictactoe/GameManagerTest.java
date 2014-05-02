package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import com.swordfish.tictactoe.GameManager;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameManagerTest {

    @Test
    public void shouldCheckIfGameIsOverWhenSomebodyGets3InARow() throws Exception {

        Board stubBoard = mock(Board.class);

        when(stubBoard.get(0)).thenReturn("x");
        when(stubBoard.get(1)).thenReturn("x");
        when(stubBoard.get(2)).thenReturn("x");

        GameManager gameManager = new GameManager();

        String winner = gameManager.whoIsTheWinner(stubBoard);

        assertThat(winner, is("x"));

    }

    @Test
    public void shouldCheckIfGameIsNotOverWhenNobodyHas3InARow() throws Exception {

        Board stubBoard = mock(Board.class);

        when(stubBoard.get(anyInt())).thenReturn("");

        GameManager gameManager = new GameManager();

        String winner = gameManager.whoIsTheWinner(stubBoard);

        assertThat(winner, is(""));

    }

}
