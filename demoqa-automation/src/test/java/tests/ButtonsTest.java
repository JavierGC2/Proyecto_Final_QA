package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ButtonsPage;
import utils.BaseTest;

public class ButtonsTest extends BaseTest {
    private ButtonsPage buttonsPage;

    @BeforeMethod
    public void init() {
        buttonsPage = new ButtonsPage(driver);
        buttonsPage.navigateTo();
    }

    // TC-013
    @Test(description = "TC-013: Click simple en boton dinamico")
    public void testClickMe() {
        buttonsPage.performClick();
        Assert.assertEquals(
            buttonsPage.getDynamicClickMessage(),
            "You have done a dynamic click",
            "Mensaje incorrecto para click simple"
        );
    }

    // TC-014
    @Test(description = "TC-014: Doble click en boton")
    public void testDoubleClick() {
        buttonsPage.performDoubleClick();
        Assert.assertEquals(
            buttonsPage.getDoubleClickMessage(),
            "You have done a double click",
            "Mensaje incorrecto para doble click"
        );
    }

    // TC-015
    @Test(description = "TC-015: Click derecho en boton")
    public void testRightClick() {
        buttonsPage.performRightClick();
        Assert.assertEquals(
            buttonsPage.getRightClickMessage(),
            "You have done a right click",
            "Mensaje incorrecto para click derecho"
        );
    }
}
