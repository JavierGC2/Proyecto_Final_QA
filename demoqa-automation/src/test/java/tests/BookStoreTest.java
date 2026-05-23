package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookStorePage;
import utils.BaseTest;

public class BookStoreTest extends BaseTest {
    private BookStorePage bookStorePage;

    @BeforeMethod
    public void init() {
        bookStorePage = new BookStorePage(driver);
        bookStorePage.navigateTo();
    }

    // TC-020
    @Test(description = "TC-020: Busqueda de libro existente")
    public void testBusquedaLibroExistente() {
        bookStorePage.searchBook("Git Pocket Guide");
        Assert.assertTrue(bookStorePage.isBookDisplayed("Git Pocket Guide"),
            "El libro 'Git Pocket Guide' deberia aparecer en los resultados");
    }
}
