package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RadioButtonPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By yesRadioLabel = By.cssSelector("label[for='yesRadio']");
    private By noRadioInput  = By.id("noRadio");
    private By resultText    = By.cssSelector("p.mt-3 span");

    public RadioButtonPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/radio-button");
    }

    public void selectYes() {
        wait.until(ExpectedConditions.elementToBeClickable(yesRadioLabel)).click();
    }

    public String getResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultText))
                   .getText();
    }

    public boolean isNoRadioDisabled() {
        return !driver.findElement(noRadioInput).isEnabled();
    }
}
