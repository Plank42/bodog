package bodog.utils;

import bodog.BaseFixture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 18/08/13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public abstract class Worker extends BaseFixture {

    public void loginToBodog(String account, String password) throws Exception {

        WebElement accountField = selenium.findElement(By.name("username"));
        WebElement pwField = selenium.findElement(By.id("passwordClear"));
        WebElement signIn = selenium.findElement(By.id("submitSignIn"));

        if (accountField.equals(null)){
            throwException("Account field not found");
        }

        if (pwField.equals(null)){
            throwException("Password field not found");
        }

        if (signIn.equals(null)){
            throwException("Sign In button not found");
        }

        accountField.sendKeys(account);
        pwField.sendKeys(password);

        signIn.click();

        if (checkLogin().equals(true)){
            Assert.assertTrue(true);

        }
        else {
            Assert.fail(account + " User Failed to Login");
        }

    }


    public void throwException(String nameOfError) throws Exception {

        throw new Exception(nameOfError);
    }

    /***
     * Issue: Unable to create UK account as following error message returned
     * "We are sorry. Bodog is not available from your location."
     * Please provide valid test login details in future.
     * @return
     */
    public Boolean checkLogin(){

        WebElement errorMsg = selenium.findElement(By.className("errorMessage"));

        if (!errorMsg.equals(null)){

            if (errorMsg.findElement(By.tagName("p")).getText().toLowerCase().trim().equals(errMsg.toLowerCase()))
                return false;
        }

        return true;

    }
}
