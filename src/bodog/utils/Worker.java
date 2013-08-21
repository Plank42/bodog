package bodog.utils;

import bodog.BaseFixture;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

import static bodog.utils.Assertions.checkLogin;
import static bodog.utils.Assertions.checkRegistration;

public abstract class Worker extends BaseFixture {

    /***
     * Main Login Functionality
     * @param account
     * @param password
     * @throws Exception
     */
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

        if (checkLogin(errMsg).equals(true)){
            Assert.assertTrue(true);

        }
        else {
            Assert.fail(account + " User Failed to Login");
        }
    }

    /***
     * Main Registration Function
     * @param testCaseData
     * @throws Exception
     */
    public void registerCustomer(Map<String,String> testCaseData) throws Exception {

        String user = testCaseData.get("username"), pwd = testCaseData.get("password");
        // Not required String pwdConf = testCaseData.get("passwordConfirm");
        String pField1 = testCaseData.get("phoneField1"), pField2 = testCaseData.get("phoneField2"), pField3 = testCaseData.get("phoneField3");
        String cBox1 = testCaseData.get("checkBox1"), cBox2 = testCaseData.get("checkBox2");

        checkNavigation(regURL,"head,title");

        enterTextIntoFieldById("edit-email-address", user);
        enterTextIntoFieldById("edit-password", pwd);
        enterTextIntoFieldById("edit-reenter-password", pwd);
        enterTextIntoFieldById("edit-mobile-phone-1", pField1);
        enterTextIntoFieldById("edit-mobile-phone-2", pField2);
        enterTextIntoFieldById("edit-mobile-phone-3", pField3);
        checkBoxValidation("edit-mobile-phone-opt-in", Boolean.valueOf(cBox1));
        checkBoxValidation("edit-agree-t-and-c", Boolean.valueOf(cBox2));
        clickButton("edit-submit");

        checkRegistration(regConfirm);

    }

    protected void checkNavigation(String URL, String twoTagNames){

        selenium.navigate().to(URL);
        String[] tags = twoTagNames.split(",");
        while (selenium.getCurrentUrl().contains("https")) {
            selenium.get("http://"+ URL);
        }
        WebElement header = selenium.findElement(By.tagName(tags[0]));
        Assert.assertEquals(header.findElement(By.tagName(tags[1])).getText().toLowerCase(), regTitle.toLowerCase());

    };

    /***
     * Generic field interaction method
     * @param fieldName
     * @param text
     * @throws Exception
     */
    public void enterTextIntoFieldById(String fieldName, String text) throws Exception {

        if (!selenium.findElement(By.id(fieldName)).equals(null)){
            selenium.findElement(By.id(fieldName)).sendKeys(text);
        }
        else {
            throwException("WebElement not found: " + fieldName);
        }
        //todo: add verification for field as nothing returned from getText...
        if ((!selenium.findElement(By.id(fieldName)).getText().equals(""))) {
            Assert.assertEquals(selenium.findElement(By.id(fieldName)).getText(), text);
        }
    }

    /***
     * Generic check box interaction
     * @param fieldName
     * @param select
     * @throws Exception
     */
    public void checkBoxValidation(String fieldName, boolean select) throws Exception {

        if (!selenium.findElement(By.id(fieldName)).equals(null) ){

            if (!selenium.findElement(By.id(fieldName)).isSelected() == select) {
                selenium.findElement(By.id(fieldName)).click();
            }
        }
        else {
            throwException("WebElement not found: " + fieldName);
        }
        Assert.assertEquals(selenium.findElement(By.id(fieldName)).isSelected(), select);
    }

    private void clickButton(String fieldName) {
        if (!selenium.findElement(By.id(fieldName)).equals(null)){
            selenium.findElement(By.id(fieldName)).click();
        }
    }

    public void throwException(String nameOfError) throws Exception {

        throw new Exception(nameOfError);
    }


}
