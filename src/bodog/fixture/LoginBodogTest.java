package bodog.fixture;

import bodog.utils.Utils;
import bodog.utils.Worker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class LoginBodogTest extends Worker {

    /***
     * Todo: Issue: Unable to create UK account as following error message returned
     * "We are sorry. Bodog is not available from your location."
     * Please provide valid login details in future.
     * Todo: Due to the above, was unable to prove dataProvider Functionality of repeated lines.
     * Please provide valid login details in future.
     * @param testCaseData
     * @throws Exception
     */
    @Test(dataProvider = "testCaseData")
    public void loginBodogTest(final Map<String, String> testCaseData) throws Exception {

        WebElement header = selenium.findElement(By.tagName("head"));
        WebElement title = header.findElement(By.tagName("title"));
        Assert.assertEquals(title.getText().toString().toLowerCase(), titleURL.toLowerCase());

        String user = testCaseData.get("username");
        String pwd = testCaseData.get("password");
        // Assume those values comes from csv file and will be executed as many iterations as rows on the csv file.
        loginToBodog(user, pwd);
    }

    @DataProvider(name = "testCaseData")
    public Object[][] testCaseData() throws IOException {
        try {
            return Utils.getData("src/resources/LoginBodogTest.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}


