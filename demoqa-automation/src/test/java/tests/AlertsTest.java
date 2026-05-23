package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertsPage;
import utils.BaseTest;

public class AlertsTest extends BaseTest {
    private AlertsPage alertsPage;

    @BeforeMethod
    public void init() {
        alertsPage = new AlertsPage(driver);
        alertsPage.navigateTo();
    }

    // TC-019
    @Test(description = "TC-019: Aceptar alerta simple")
    public void testAceptarAlertaSimple() {
        alertsPage.clickSimpleAlert();
        String alertText = alertsPage.acceptAlertAndGetText();
        Assert.assertNotNull(alertText,
            "La alerta deberia tener un texto");
        Assert.assertTrue(alertsPage.isAlertDismissed(),
            "La alerta deberia estar cerrada despues de aceptar");
    }
}
