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
        WebElement element = driver.findElement(By.id("game-board"));
        assertThat(element.getAttribute("id"), is("game-board"));
    }

    @Test
    public void shouldMarkBoard() throws Exception {

        driver.findElement(By.id("box1-button")).click();

        WebElement box1 = driver.findElement(By.id("box1"));
        assertThat(box1.getText().toLowerCase(), is("x"));
    }

    @Test
    public void shouldMarkBoardWithO() throws Exception {

        driver.findElement(By.id("box1-button")).click();
        driver.findElement(By.id("box2-button")).click();

        WebElement box1 = driver.findElement(By.id("box1"));
        WebElement box2 = driver.findElement(By.id("box2"));
        assertThat(box1.getText().toLowerCase(), is("x"));
        assertThat(box2.getText().toLowerCase(), is("o"));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldNotLetPlayerChooseBoxThatIsTaken() throws Exception {

        driver.findElement(By.id("box1-button")).click();
        driver.findElement(By.id("box1-button"));

    }

    @Test
    public void shouldDisplayMessageWhenBoardIsFull() throws Exception {

        for (int playerMoveInput = 1; playerMoveInput < 10; playerMoveInput++) {
            fillOneBox(playerMoveInput);
        }

        WebElement endOfGameMessage = driver.findElement(By.id("play-again-message"));

        assertThat(endOfGameMessage.getText(), containsString("Game over."));

        WebElement playAgainLink = driver.findElement(By.id("play-again-link"));

        playAgainLink.click();

        WebElement box1 = driver.findElement(By.id("box1"));
        WebElement box2 = driver.findElement(By.id("box2"));
        WebElement box3 = driver.findElement(By.id("box3"));
        WebElement box4 = driver.findElement(By.id("box4"));
        WebElement box5 = driver.findElement(By.id("box5"));
        WebElement box6 = driver.findElement(By.id("box6"));
        WebElement box7 = driver.findElement(By.id("box7"));
        WebElement box8 = driver.findElement(By.id("box8"));
        WebElement box9 = driver.findElement(By.id("box9"));

        assertThat(box1.getText().toLowerCase(), is(""));
        assertThat(box2.getText().toLowerCase(), is(""));
        assertThat(box3.getText().toLowerCase(), is(""));
        assertThat(box4.getText().toLowerCase(), is(""));
        assertThat(box5.getText().toLowerCase(), is(""));
        assertThat(box6.getText().toLowerCase(), is(""));
        assertThat(box7.getText().toLowerCase(), is(""));
        assertThat(box8.getText().toLowerCase(), is(""));
        assertThat(box9.getText().toLowerCase(), is(""));


    }

    private void fillOneBox(int playerMoveInput) {
        String boxButtonName = "box" + playerMoveInput + "-button";
        driver.findElement(By.id(boxButtonName)).click();
    }

    @Test
    public void shouldDeclarePlayer1TheWinner() throws Exception {

        driver.findElement(By.id("box1-button")).click();
        driver.findElement(By.id("box4-button")).click();
        driver.findElement(By.id("box2-button")).click();
        driver.findElement(By.id("box5-button")).click();
        driver.findElement(By.id("box3-button")).click();

        WebElement winner = driver.findElement(By.id("winner"));

        assertThat(winner.getText(), containsString("X"));

        WebElement playAgainMessage = driver.findElement(By.id("play-again-message"));

        assertThat(playAgainMessage.getText(), containsString("Play again?"));

    }

}
