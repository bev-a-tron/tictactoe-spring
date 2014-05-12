package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void shouldCheckIfGameIsOverWhenSomebodyGets3InARow() throws Exception {

        Board board = new Board();
        board.put(0, "x");
        board.put(1, "x");
        board.put(2, "x");

        String winner = board.whoIsTheWinner();

        assertThat(winner, is("X"));

    }

    @Test
    public void shouldCheckIfGameIsNotOverWhenNobodyHas3InARow() throws Exception {

        Board board = new Board();

        String winner = board.whoIsTheWinner();

        assertThat(winner, is(""));

    }

}
