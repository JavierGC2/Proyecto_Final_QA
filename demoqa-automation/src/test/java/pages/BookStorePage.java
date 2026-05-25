package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BookStorePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox   = By.id("searchBox");
    private By bookRows  = By.cssSelector("span[id^='see-book-'] a");

    public BookStorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://demoqa.com/books");
    }

    public void searchBook(String title) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox))
            .sendKeys(title);
    }

    public List<String> getBookTitles() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(bookRows));
        return driver.findElements(bookRows)
                     .stream()
                     .map(e -> e.getText())
                     .filter(t -> !t.isEmpty())
                     .collect(java.util.stream.Collectors.toList());
    }

    public boolean isBookDisplayed(String title) {
        return getBookTitles().stream()
                              .anyMatch(t -> t.contains(title));
    }
}
