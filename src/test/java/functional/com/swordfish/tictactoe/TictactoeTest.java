package functional.com.swordfish.tictactoe;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
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
        Assert.assertThat(element.getAttribute("id"), is("game-board"));
    }

    @Test
    public void shouldMarkBoard() throws Exception {
        WebElement playerMoveInput = driver.findElement(By.id("player-move-input"));
        playerMoveInput.clear();
        playerMoveInput.sendKeys("1");

        driver.findElement(By.id("move-button")).click();

        WebElement box1 = driver.findElement(By.id("box1"));
        Assert.assertThat(box1.getText().toLowerCase(), is("x"));
    }

    @Test
    public void shouldPrintOutErrors() throws Exception {
        WebElement playerMoveInput = driver.findElement(By.id("player-move-input"));
        playerMoveInput.clear();
        playerMoveInput.sendKeys("0");

        driver.findElement(By.id("move-button")).click();

        WebElement errorMessage = driver.findElement(By.id("error-message"));
        Assert.assertThat(errorMessage.getText(), containsString("Number out of range."));
    }

    @Test
    public void shouldMarkBoardWithO() throws Exception {
        WebElement playerMoveInput = driver.findElement(By.id("player-move-input"));
        playerMoveInput.clear();
        playerMoveInput.sendKeys("1");

        driver.findElement(By.id("move-button")).click();

        playerMoveInput = driver.findElement(By.id("player-move-input"));
        playerMoveInput.clear();
        playerMoveInput.sendKeys("2");

        driver.findElement(By.id("move-button")).click();

        WebElement box1 = driver.findElement(By.id("box1"));
        WebElement box2 = driver.findElement(By.id("box2"));
        Assert.assertThat(box1.getText().toLowerCase(), is("x"));
        Assert.assertThat(box2.getText().toLowerCase(), is("o"));
    }

}
