package bodog.utils;

import bodog.BaseFixture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public abstract class Assertions extends BaseFixture {

    /***
     * Issue: Unable to create UK account as following error message returned
     * "We are sorry. Bodog is not available from your location."
     * Please provide valid test login details in future.
     * @return
     */
    protected static Boolean checkLogin(String errMsg){

        WebElement errorMsg = selenium.findElement(By.className("errorMessage"));

        if (!errorMsg.equals(null)){

            if (errorMsg.findElement(By.tagName("p")).getText().toLowerCase().trim().equals(errMsg.toLowerCase()))
                return false;
        }
        return true;
    }

    protected static void checkRegistration(String regConfirm){

        if (!selenium.getCurrentUrl().contains("https")) {
            //todo: need a better util to handle compound class names
            if (selenium.findElement(By.className("messagestatus")).equals(null)){
                Assert.assertEquals(selenium.findElement(By.className("message status")).getText().toLowerCase(), regConfirm.toLowerCase());
            }
            else {
                Assert.fail();
            }
        }
        else {
            Assert.fail("Navigation failure");
        }
    }
}
