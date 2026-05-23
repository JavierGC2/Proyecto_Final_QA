package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; // <-- Nueva importación
import org.openqa.selenium.JavascriptExecutor; // <-- Nueva importación
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By expandAllBtn = By.cssSelector("button.rct-option-expand-all, button[title='Expand all'], button[aria-label='Expand all']");
    private By homeCheckBox = By.cssSelector("label[for='tree-node-home']");
    private By resultText = By.id("result");

    public CheckBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/checkbox");
    }

    public void expandAll() {
        // Wait for tree container to load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("react-checkbox-tree")));
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(expandAllBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void selectHomeCheckBox() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(homeCheckBox));
        // Scroll to checkbox
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isResultDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultText))
                .isDisplayed();
    }

    public String getResultText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resultText))
                .getText();
    }
}
