package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TextBoxPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By fullNameField         = By.id("userName");
    private By emailField            = By.id("userEmail");
    private By currentAddressField   = By.id("currentAddress");
    private By permanentAddressField = By.id("permanentAddress");
    private By submitButton          = By.id("submit");
    private By outputName            = By.id("name");
    private By emailFieldLocator     = By.id("userEmail");

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/text-box");
    }

    public void fillForm(String name, String email,
                         String currentAddr, String permanentAddr) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameField))
            .sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(currentAddressField).sendKeys(currentAddr);
        driver.findElement(permanentAddressField).sendKeys(permanentAddr);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public String getOutputName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(outputName))
                   .getText();
    }

    public boolean isEmailInvalid() {
        String classes = driver.findElement(emailFieldLocator).getAttribute("class");
        return classes != null && classes.contains("field-error");
    }
}
