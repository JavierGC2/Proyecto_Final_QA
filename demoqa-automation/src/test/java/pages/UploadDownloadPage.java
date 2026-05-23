package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UploadDownloadPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By uploadInput  = By.id("uploadFile");
    private By uploadedPath = By.id("uploadedFilePath");

    public UploadDownloadPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/upload-download");
    }

    public void uploadFile(String filePath) {
        driver.findElement(uploadInput).sendKeys(filePath);
    }

    public String getUploadedFileName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(uploadedPath))
                   .getText();
    }
}
