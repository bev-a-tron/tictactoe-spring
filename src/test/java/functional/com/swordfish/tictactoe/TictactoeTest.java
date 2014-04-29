package functional.com.swordfish.tictactoe;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;

public class TictactoeTest {

    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        driver.quit();
    }

    @Test
    public void shouldDrawBoard() {
        driver.navigate().to("localhost:8080/");
        WebElement element = driver.findElement(By.id("game-board"));
        Assert.assertThat(element.getAttribute("id"), is("game-board"));
    }

    @Ignore("WIP #2 | Kyle, Eliza, & Bev | adding logic for the player move")
    @Test
    public void shouldMarkBoard() throws Exception {
        driver.navigate().to("localhost:8080/");

        WebElement playerMoveInput = driver.findElement(By.id("player-move-input"));
        playerMoveInput.clear();
        playerMoveInput.sendKeys("1");

        driver.findElement(By.id("move-button")).click();

        WebElement box1 = driver.findElement(By.id("box1"));
        Assert.assertThat(box1.getText().toLowerCase(), is("x"));
    }

}
