package functional.com.swordfish.tictactoe;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;

public class TictactoeTest {

    @Test
    public void shouldDrawBoard() {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("localhost:8080/");
        WebElement element = driver.findElement(By.id("game-board"));
        Assert.assertThat(element.getAttribute("id"), is("game-board"));
        driver.close();
        driver.quit();
    }
}
