package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PracticeFormPage;
import utils.BaseTest;

public class PracticeFormTest extends BaseTest {
    private PracticeFormPage practiceFormPage;

    @BeforeMethod
    public void init() {
        practiceFormPage = new PracticeFormPage(driver);
        practiceFormPage.navigateTo();
    }

    // TC-017
    @Test(description = "TC-017: Envio de formulario completo")
    public void testFormularioCompleto() {
        practiceFormPage.fillBasicInfo(
            "Maria",
            "Lopez",
            "maria@mail.com",
            "1234567890"
        );
        practiceFormPage.submitForm();
        Assert.assertTrue(practiceFormPage.isModalVisible(),
            "El modal de confirmacion deberia aparecer");
        Assert.assertEquals(
            practiceFormPage.getModalTitle(),
            "Thanks for submitting the form",
            "El titulo del modal no es el esperado"
        );
    }

    // TC-018
    @Test(description = "TC-018: Envio con campos obligatorios vacios")
    public void testCamposObligatoriosVacios() {
        practiceFormPage.submitForm();
        Assert.assertTrue(practiceFormPage.isFirstNameInvalid(),
            "El campo First Name deberia marcarse como invalido");
    }
}
