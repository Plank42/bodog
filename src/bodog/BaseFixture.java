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
//    protected DateFormat dateValue = new SimpleDateFormat("dd/MM/yyyy");
//    protected Calendar dateAssert = Calendar.getInstance();
    public String baseURL, testURL, titleURL, locale, destination, errMsg;


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
    }


    @AfterTest
    public void tearDown() {
        selenium.close();
    }

}
