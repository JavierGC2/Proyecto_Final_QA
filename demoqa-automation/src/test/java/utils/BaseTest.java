package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://demoqa.com/");
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
