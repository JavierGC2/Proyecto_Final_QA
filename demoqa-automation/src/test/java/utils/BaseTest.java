package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

    @AfterMethod
    public void attachScreenshot(ITestResult result) {
        byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);

        String label = result.isSuccess() ? "✅ PASS" : "❌ FAIL";
        Allure.addAttachment(label + " - " + result.getName(),
                "image/png",
                new java.io.ByteArrayInputStream(screenshot),
                "png");
    }
}