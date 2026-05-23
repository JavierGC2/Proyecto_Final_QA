package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AlertsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By simpleAlertBtn = By.id("alertButton");

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/alerts");
    }

    public void clickSimpleAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(simpleAlertBtn)).click();
    }

    public String acceptAlertAndGetText() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean isAlertDismissed() {
        try {
            driver.switchTo().alert();
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
