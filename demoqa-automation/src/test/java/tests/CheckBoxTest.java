package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CheckBoxPage;
import utils.BaseTest;

public class CheckBoxTest extends BaseTest {
    private CheckBoxPage checkBoxPage;

    @BeforeMethod
    public void init() {
        checkBoxPage = new CheckBoxPage(driver);
        checkBoxPage.navigateTo();
    }

    // TC-010
    @Test(description = "TC-010: Seleccion de checkbox padre Home")
    public void testSeleccionCheckBoxPadre() {
        checkBoxPage.selectHomeCheckBox();
        Assert.assertTrue(checkBoxPage.isResultDisplayed(),
            "Deberia mostrarse el resultado al seleccionar el checkbox padre");
        Assert.assertTrue(checkBoxPage.getResultText().toLowerCase().contains("home"),
            "El resultado deberia incluir 'home'");
    }
}
