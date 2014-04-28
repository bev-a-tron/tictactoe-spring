package functional.com.swordfish.tictactoe;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TictactoeTest {

    @Test
    public void shouldDrawBoard() {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("localhost:8080/");
//          Beverly | working on writing test for #1 Draw Board
//        Assert.assertTrue("board should be drawn",
//                driver.getTitle().startsWith("Selenium Simplified"));
//        driver.close();
//        driver.quit();
    }
}
