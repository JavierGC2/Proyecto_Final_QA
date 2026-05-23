package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.UploadDownloadPage;
import utils.BaseTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class UploadDownloadTest extends BaseTest {
    private UploadDownloadPage uploadPage;
    private String testFilePath;

    @BeforeMethod
    public void init() throws IOException {
        uploadPage = new UploadDownloadPage(driver);
        uploadPage.navigateTo();

        // Crear archivo temporal para la prueba
        File tempFile = File.createTempFile("test", ".png");
        tempFile.deleteOnExit();
        testFilePath = tempFile.getAbsolutePath();
    }

    // TC-016
    @Test(description = "TC-016: Subir archivo valido")
    public void testSubirArchivoValido() {
        uploadPage.uploadFile(testFilePath);
        String uploadedName = uploadPage.getUploadedFileName();
        Assert.assertTrue(uploadedName.contains("test"),
            "El nombre del archivo subido deberia contener 'test'");
    }
}
