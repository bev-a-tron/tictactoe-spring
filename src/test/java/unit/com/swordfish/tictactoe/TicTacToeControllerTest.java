package unit.com.swordfish.tictactoe;

import com.swordfish.tictactoe.TicTacToeController;
import com.swordfish.tictactoe.TicTacToeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TicTacToeControllerTest {

    @Mock
    TicTacToeService mockService;

    @InjectMocks
    TicTacToeController ticTacToeController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldTellServiceToUpdateModel() {
        String playerMoveInput = "1";
        ticTacToeController.makeMove(playerMoveInput);

        verify(mockService).updateModel(playerMoveInput);
    }
}
