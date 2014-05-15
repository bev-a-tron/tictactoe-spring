package functional.com.swordfish.tictactoe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
    public void shouldEndTheGameWithADraw() throws Exception {
        fillAllTheBoxes();

        showsGameOverMessage();

        clicksPlayAgain();

        checkThatBoardIsEmpty();
    }

    @Test
    public void shouldEndTheGameWithAWinner() throws Exception {
        clickBoxesSoXWins();

        checkThatStatusSaysXWon();

        showsGameOverMessage();
    }

    private void checkThatBoardIsEmpty() {
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

    private void clicksPlayAgain() {
        WebElement playAgainLink = driver.findElement(By.id("play-again-link"));

        playAgainLink.click();
    }

    private void showsGameOverMessage() {
        WebElement endOfGameMessage = driver.findElement(By.id("play-again-message"));

        assertThat(endOfGameMessage.getText(), containsString("Game over."));
    }

    private void fillAllTheBoxes() {
        int[] moveSequenceToEnsureDraw = {0, 1, 2, 4, 3, 5, 7, 6, 8};

        for (int move : moveSequenceToEnsureDraw) {
            String boxButtonName = "box" + move + "-button";
            driver.findElement(By.id(boxButtonName)).click();
        }
    }

    private void checkThatStatusSaysXWon() {
        WebElement winner = driver.findElement(By.id("game-status"));

        assertThat(winner.getText(), containsString("X"));
    }

    private void clickBoxesSoXWins() {
        driver.findElement(By.id("box0-button")).click();
        driver.findElement(By.id("box3-button")).click();
        driver.findElement(By.id("box1-button")).click();
        driver.findElement(By.id("box4-button")).click();
        driver.findElement(By.id("box2-button")).click();
    }
}
