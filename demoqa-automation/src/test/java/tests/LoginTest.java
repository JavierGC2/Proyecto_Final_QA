package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage(driver);
        loginPage.navigateTo();
    }

    // TC-001
    @Test(description = "TC-001: Login con credenciales validas")
    public void testLoginValido() {
        loginPage.login("testuser", "Test@1234");
        Assert.assertTrue(loginPage.isLoginSuccessful(),
                "El login deberia redirigir al perfil del usuario");
    }

    // TC-002
    @Test(description = "TC-002: Login con contrasena incorrecta")
    public void testLoginContrasenaIncorrecta() {
        loginPage.login("testuser", "wrongpass");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Invalid username or password!"),
                "Deberia mostrar mensaje de error por contrasena incorrecta");
    }

    // TC-003
    @Test(description = "TC-003: Login con campos vacios")
    public void testLoginCamposVacios() {
        loginPage.login("", "");
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.contains("login"),
                "Deberia permanecer en la pagina de login");
    }

    // TC-004
    @Test(description = "TC-004: Login con usuario inexistente")
    public void testLoginUsuarioInexistente() {
        loginPage.login("usuarioNoExiste123", "Test@1234");
        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Invalid username or password!"),
                "Deberia mostrar mensaje de error por usuario inexistente");
    }
}
