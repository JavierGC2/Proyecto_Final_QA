package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ButtonsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By doubleClickBtn  = By.id("doubleClickBtn");
    private By rightClickBtn   = By.id("rightClickBtn");
    private By clickMeBtn      = By.xpath("//button[text()='Click Me']");
    private By doubleClickMsg  = By.id("doubleClickMessage");
    private By rightClickMsg   = By.id("rightClickMessage");
    private By dynamicClickMsg = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/buttons");
    }

    public void performDoubleClick() {
        actions.doubleClick(
            wait.until(ExpectedConditions.elementToBeClickable(doubleClickBtn))
        ).perform();
    }

    public void performRightClick() {
        actions.contextClick(
            wait.until(ExpectedConditions.elementToBeClickable(rightClickBtn))
        ).perform();
    }

    public void performClick() {
        wait.until(ExpectedConditions.elementToBeClickable(clickMeBtn)).click();
    }

    public String getDoubleClickMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(doubleClickMsg))
                   .getText();
    }

    public String getRightClickMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(rightClickMsg))
                   .getText();
    }

    public String getDynamicClickMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicClickMsg))
                   .getText();
    }
}
