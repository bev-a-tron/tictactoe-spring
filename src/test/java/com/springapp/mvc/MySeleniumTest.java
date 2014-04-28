package com.springapp.mvc;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MySeleniumTest {
    @Test
    public void startWebDriver() {
        WebDriver driver = new FirefoxDriver();
        driver.navigate().to("http://seleniumsimplified.com");
        Assert.assertTrue("title should start with Selenium Simplified",
                driver.getTitle().startsWith("Selenium Simplified"));
        driver.close();
        driver.quit();
    }
}

