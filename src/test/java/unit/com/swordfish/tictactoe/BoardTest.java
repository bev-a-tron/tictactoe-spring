package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Board;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BoardTest {

    @Test
    public void shouldCheckIfGameIsOverWhenSomebodyGets3InARow() throws Exception {

        Board board = new Board();
        board.drawSymbolInBox(0, "x");
        board.drawSymbolInBox(1, "x");
        board.drawSymbolInBox(2, "x");

        String winner = board.whoIsTheWinner();

        assertThat(winner, is("X"));

    }

    @Test
    public void shouldCheckIfGameIsNotOverWhenNobodyHas3InARow() throws Exception {

        Board board = new Board();

        String winner = board.whoIsTheWinner();

        assertThat(winner, is(""));

    }

    @Test
    public void shouldCheckIfBoardIsFull() throws Exception {

        Board board = new Board();

        board.drawSymbolInBox(0, "x");
        board.drawSymbolInBox(1, "x");
        board.drawSymbolInBox(2, "x");
        board.drawSymbolInBox(3, "x");
        board.drawSymbolInBox(4, "x");
        board.drawSymbolInBox(5, "x");
        board.drawSymbolInBox(6, "x");
        board.drawSymbolInBox(7, "x");
        board.drawSymbolInBox(8, "x");

        assertThat(board.isFull(), is(true));

    }

    @Test
    public void shouldCheckIfBoardIsNotFull() throws Exception {

        Board board = new Board();

        assertThat(board.isFull(), is(false));

    }
}
