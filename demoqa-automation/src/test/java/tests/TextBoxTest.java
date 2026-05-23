package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.TextBoxPage;
import utils.BaseTest;

public class TextBoxTest extends BaseTest {
    private TextBoxPage textBoxPage;

    @BeforeMethod
    public void init() {
        textBoxPage = new TextBoxPage(driver);
        textBoxPage.navigateTo();
    }

    // TC-008
    @Test(description = "TC-008: Envio de formulario con datos validos")
    public void testFormularioValido() {
        textBoxPage.fillForm(
            "Juan Perez",
            "juan@mail.com",
            "Calle Reforma 123, CDMX",
            "Av. Insurgentes 456, CDMX"
        );
        textBoxPage.submitForm();
        String outputName = textBoxPage.getOutputName();
        Assert.assertTrue(outputName.contains("Juan Perez"),
            "El resultado deberia mostrar el nombre ingresado");
    }

    // TC-009
    @Test(description = "TC-009: Envio con email con formato invalido")
    public void testEmailInvalido() {
        textBoxPage.fillForm(
            "Juan Perez",
            "juanmail.com",
            "Calle Reforma 123",
            "Av. Insurgentes 456"
        );
        textBoxPage.submitForm();
        Assert.assertTrue(textBoxPage.isEmailInvalid(),
            "El campo email deberia marcarse como invalido");
    }
}
