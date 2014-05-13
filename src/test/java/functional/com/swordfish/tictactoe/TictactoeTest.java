package functional.com.swordfish.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.StringContains.containsString;

public class TicTacToeTest {

    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.navigate().to("localhost:8080/");
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }

    @Test
    public void shouldDrawBoard() {
        //TODO: helper class for low-level webdriver methods. High level API appropriate here?
        WebElement element = driver.findElement(By.id("game-board"));
        assertThat(element.getAttribute("id"), is("game-board"));
    }

    //TODO: Need a clearer way to find out where the failure is in this test
    @Test
    public void shouldMarkBoard() throws Exception {

        driver.findElement(By.id("box0-button")).click();

        WebElement box0 = driver.findElement(By.id("box0"));
        assertThat(box0.getText(), is("x"));
    }

    @Test
    public void shouldMarkBoardWithO() throws Exception {

        driver.findElement(By.id("box0-button")).click();
        driver.findElement(By.id("box1-button")).click();

        WebElement box1 = driver.findElement(By.id("box0"));
        WebElement box2 = driver.findElement(By.id("box1"));
        assertThat(box1.getText(), is("x"));
        assertThat(box2.getText(), is("o"));
    }

    //TODO this passes all the time (even when we don't run the server), useless test?
    @Test(expected = NoSuchElementException.class)
    public void shouldNotLetPlayerChooseBoxThatIsTaken() throws Exception {

        driver.findElement(By.id("box0-button")).click();
        driver.findElement(By.id("box0-button"));

    }

//    TODO: test is testing two things: game over when draw, and play again link takes you to a blank board
    @Test
    public void shouldDisplayMessageWhenBoardIsFull() throws Exception {

        //TODO: This is ugly
        int[] moveSequenceToEnsureDraw = {0, 1, 2, 4, 3, 5, 7, 6, 8};

        for (int move : moveSequenceToEnsureDraw) {
            fillOneBox(move);
        }

        WebElement endOfGameMessage = driver.findElement(By.id("play-again-message"));

        assertThat(endOfGameMessage.getText(), containsString("Game over."));

        WebElement playAgainLink = driver.findElement(By.id("play-again-link"));

        playAgainLink.click();

        WebElement box0 = driver.findElement(By.id("box0"));
        WebElement box1 = driver.findElement(By.id("box1"));
        WebElement box2 = driver.findElement(By.id("box2"));
        WebElement box3 = driver.findElement(By.id("box3"));
        WebElement box4 = driver.findElement(By.id("box4"));
        WebElement box5 = driver.findElement(By.id("box5"));
        WebElement box6 = driver.findElement(By.id("box6"));
        WebElement box7 = driver.findElement(By.id("box7"));
        WebElement box8 = driver.findElement(By.id("box8"));

        assertThat(box0.getText(), is(""));
        assertThat(box1.getText(), is(""));
        assertThat(box2.getText(), is(""));
        assertThat(box3.getText(), is(""));
        assertThat(box4.getText(), is(""));
        assertThat(box5.getText(), is(""));
        assertThat(box6.getText(), is(""));
        assertThat(box7.getText(), is(""));
        assertThat(box8.getText(), is(""));


    }

    //TODO: Private methods in support of tests ok?
    private void fillOneBox(int playerMoveInput) {
        String boxButtonName = "box" + playerMoveInput + "-button";
        driver.findElement(By.id(boxButtonName)).click();
    }

    @Test
    public void shouldDeclarePlayer1TheWinner() throws Exception {

//        TODO: make method called has3InARow, and stub that instead of clicking through a game
        driver.findElement(By.id("box0-button")).click();
        driver.findElement(By.id("box3-button")).click();
        driver.findElement(By.id("box1-button")).click();
        driver.findElement(By.id("box4-button")).click();
        driver.findElement(By.id("box2-button")).click();

        WebElement winner = driver.findElement(By.id("game-status"));

        assertThat(winner.getText(), containsString("X"));

        WebElement playAgainMessage = driver.findElement(By.id("play-again-message"));

        assertThat(playAgainMessage.getText(), containsString("Play again?"));

    }

}
