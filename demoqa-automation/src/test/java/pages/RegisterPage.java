package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By firstNameField = By.id("firstname");
    private By lastNameField  = By.id("lastname");
    private By usernameField  = By.id("userName");
    private By passwordField  = By.id("password");
    private By registerButton = By.id("register");
    private By errorMessage   = By.cssSelector("p.mb-1");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/register");
    }

    public void fillForm(String firstName, String lastName,
                         String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField))
            .sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage))
                   .getText();
    }

    public boolean isFieldInvalid(By locator) {
        String classes = driver.findElement(locator).getAttribute("class");
        return classes != null && classes.contains("field-error");
    }
}
