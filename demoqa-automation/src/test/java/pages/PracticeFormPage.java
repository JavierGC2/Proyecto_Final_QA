package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PracticeFormPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    private By firstNameField = By.id("firstName");
    private By lastNameField  = By.id("lastName");
    private By emailField     = By.id("userEmail");
    private By maleRadio      = By.cssSelector("label[for='gender-radio-1']");
    private By mobileField    = By.id("userNumber");
    private By submitButton   = By.id("submit");
    private By modalTitle     = By.id("example-modal-sizes-title-lg");
    private By modalTable     = By.cssSelector(".table-responsive");

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    public void fillBasicInfo(String firstName, String lastName,
                               String email, String mobile) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField))
            .sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(maleRadio).click();
        driver.findElement(mobileField).sendKeys(mobile);
    }

    public void submitForm() {
        WebElement btn = driver.findElement(submitButton);
        js.executeScript("arguments[0].click();", btn);
    }

    public boolean isModalVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle))
                   .isDisplayed();
    }

    public String getModalTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle))
                   .getText();
    }

    public boolean isFirstNameInvalid() {
        return !(boolean) js.executeScript(
                "return document.getElementById('firstName').validity.valid;"
        );
    }
}
