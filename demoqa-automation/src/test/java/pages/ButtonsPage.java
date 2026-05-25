package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ButtonsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By doubleClickBtn = By.id("doubleClickBtn");
    private By rightClickBtn = By.id("rightClickBtn");
    private By clickMeBtn = By.xpath("//button[text()='Click Me']");
    private By doubleClickMsg = By.id("doubleClickMessage");
    private By rightClickMsg = By.id("rightClickMessage");
    private By dynamicClickMsg = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // this.actions = new Actions(driver);
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/buttons");
    }

    public void performDoubleClick() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(doubleClickBtn));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('data-last-y');" +
                        "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                btn);
        wait.until(d -> (Boolean) ((JavascriptExecutor) d).executeScript(
                "var el = arguments[0];" +
                        "var lastY = el.getAttribute('data-last-y');" +
                        "var curY = el.getBoundingClientRect().top;" +
                        "el.setAttribute('data-last-y', curY);" +
                        "return lastY !== null && Math.abs(curY - parseFloat(lastY)) < 1;",
                btn));
        new Actions(driver).moveToElement(btn).doubleClick().perform();
    }

    public void performRightClick() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(rightClickBtn));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('data-last-y');" +
                        "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                btn);
        wait.until(d -> (Boolean) ((JavascriptExecutor) d).executeScript(
                "var el = arguments[0];" +
                        "var lastY = el.getAttribute('data-last-y');" +
                        "var curY = el.getBoundingClientRect().top;" +
                        "el.setAttribute('data-last-y', curY);" +
                        "return lastY !== null && Math.abs(curY - parseFloat(lastY)) < 1;",
                btn));
        new Actions(driver).moveToElement(btn).contextClick().perform();
    }

    public void performClick() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(clickMeBtn));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('data-last-y');" +
                        "arguments[0].scrollIntoView({behavior: 'instant', block: 'center'});",
                btn);
        wait.until(d -> (Boolean) ((JavascriptExecutor) d).executeScript(
                "var el = arguments[0];" +
                        "var lastY = el.getAttribute('data-last-y');" +
                        "var curY = el.getBoundingClientRect().top;" +
                        "el.setAttribute('data-last-y', curY);" +
                        "return lastY !== null && Math.abs(curY - parseFloat(lastY)) < 1;",
                btn));
        btn.click();
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
