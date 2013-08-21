package bodog;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseFixture {

    protected static WebDriver selenium;
    protected Properties configFile = new Properties();
    public String baseURL, testURL, titleURL, errMsg, loginConfirm;
    public String regURL, regTitle, regErrMsg, regConfirm;


    @BeforeSuite
    public void setup () throws Exception {

        loadConfig();
        selenium = new FirefoxDriver();
        selenium.get(baseURL);
        selenium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        selenium.get(testURL);
    }

    protected void loadConfig() throws FileNotFoundException {
        try {
            configFile.load(new FileInputStream("src/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseURL = configFile.getProperty("BASE_URL");
        testURL = configFile.getProperty("TEST_URL");
        titleURL = configFile.getProperty("URL_TITLE");
        errMsg = configFile.getProperty("ERR_MSG");

        regURL = configFile.getProperty("REG_URL");
        regTitle = configFile.getProperty("REG_TITLE");
        regErrMsg = configFile.getProperty("REG_ERROR");
        regConfirm = configFile.getProperty("REG_CONFIRM");
    }


    @AfterTest
    public void tearDown() {
        selenium.close();
    }

}
