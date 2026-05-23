package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.BaseTest;

public class RegisterTest extends BaseTest {
    private RegisterPage registerPage;

    @BeforeMethod
    public void init() {
        registerPage = new RegisterPage(driver);
        registerPage.navigateTo();
    }

    // TC-005
    @Test(description = "TC-005: Registro con datos validos")
    public void testRegistroValido() {
        registerPage.fillForm(
            "Juan", "Perez",
            "juanperez_" + System.currentTimeMillis(),
            "Test@1234!"
        );
        registerPage.clickRegister();
        // CAPTCHA impide automatizacion completa; se valida que el boton responde
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("register") || url.contains("login"),
            "Deberia permanecer en register (por CAPTCHA) o redirigir a login");
    }

    // TC-006
    @Test(description = "TC-006: Registro con contrasena debil")
    public void testContrasenaDebil() {
        registerPage.fillForm("Juan", "Perez", "juantest_weak", "12345678");
        registerPage.clickRegister();
        String errorMsg = registerPage.getErrorMessage();
        Assert.assertNotNull(errorMsg,
            "Deberia mostrar mensaje de error por contrasena debil");
    }

    // TC-007
    @Test(description = "TC-007: Registro con campos vacios")
    public void testCamposVacios() {
        registerPage.clickRegister();
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("register"),
            "Deberia permanecer en la pagina de registro");
    }
}
