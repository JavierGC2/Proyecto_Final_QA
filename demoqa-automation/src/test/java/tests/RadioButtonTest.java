package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RadioButtonPage;
import utils.BaseTest;

public class RadioButtonTest extends BaseTest {
    private RadioButtonPage radioButtonPage;

    @BeforeMethod
    public void init() {
        radioButtonPage = new RadioButtonPage(driver);
        radioButtonPage.navigateTo();
    }

    // TC-011
    @Test(description = "TC-011: Seleccion de opcion Yes")
    public void testSeleccionYes() {
        radioButtonPage.selectYes();
        Assert.assertEquals(
            radioButtonPage.getResultText(),
            "Yes",
            "El resultado deberia mostrar 'Yes'"
        );
    }

    // TC-012
    @Test(description = "TC-012: Verificar que el radio No esta deshabilitado")
    public void testNoRadioDeshabilitado() {
        Assert.assertTrue(radioButtonPage.isNoRadioDisabled(),
            "El radio button 'No' deberia estar deshabilitado");
    }
}
