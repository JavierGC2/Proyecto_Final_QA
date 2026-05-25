package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
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
        // Esperar que el boton sea clickable y usar JS click como fallback
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'auto', block: 'center'});", btn);
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }

    public String getErrorMessage() {
        // Intentar múltiples selectores comunes para mensajes de error en DemoQA
        String[] possibleSelectors = {
            "p.mb-1",                              // Selector original
            "div.alert.alert-danger",              // Alert bootstrap
            "div[class*='alert']",                 // Cualquier div con clase alert
            ".mb-1",                               // Solo la clase mb-1
            "p[class*='error']",                   // Párrafo con error en clase
            ".form-control.is-invalid + .invalid-feedback"  // Feedback de validación
        };

        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        for (String selector : possibleSelectors) {
            try {
                By locator = By.cssSelector(selector);
                var element = shortWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                String text = element.getText();
                if (text != null && !text.isEmpty()) {
                    return text;
                }
            } catch (Exception e) {
                // Selector no encontrado, continuar con el siguiente
            }
        }

        // Si no se encuentra en ningún selector, retornar null (no lanzar excepción)
        return null;
    }

    public boolean hasValidationError() {
        // Verificar si hay validación visual en los campos (clase is-invalid)
        String[] fieldLocators = {"firstname", "lastname", "userName", "password"};
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        for (String fieldId : fieldLocators) {
            try {
                var element = shortWait.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldId)));
                String classes = element.getAttribute("class");
                if (classes != null && (classes.contains("is-invalid") || classes.contains("field-error"))) {
                    return true;
                }
            } catch (Exception e) {
                // Campo no encontrado o error ignorado
            }
        }

        // También intentar encontrar cualquier mensaje de error visible
        return getErrorMessage() != null;
    }

    public boolean isFieldInvalid(By locator) {
        String classes = driver.findElement(locator).getAttribute("class");
        return classes != null && classes.contains("field-error");
    }
}
