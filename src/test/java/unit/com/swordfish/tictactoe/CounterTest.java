package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.Counter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CounterTest {

    @Test
    public void shouldIncrementCounter() {
        Counter counter = new Counter();

        counter.increment();

        assertThat(counter.getTurnNumber(), is(2));
    }

    @Test
    public void shouldIncrementCounterAgain() {
        Counter counter = new Counter();

        counter.increment();
        counter.increment();

        assertThat(counter.getTurnNumber(), is(3));

    }
}
