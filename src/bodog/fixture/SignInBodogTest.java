package bodog.fixture;

import bodog.utils.Utils;
import bodog.utils.Worker;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class SignInBodogTest extends Worker {

// This is a class for testing the sign in on the www.bodog.net/join page.

    @Test(dataProvider = "registerTestCaseData", enabled = true)
    public void SignInBodogTest(final Map<String, String> testCaseData) throws Exception {

        // Assume those values comes from csv file and will be executed as many iterations as rows on the csv file.
		// When the text box field has a true from the csv file means that the check box has to be checked
		// Feel free to add annotations if you feel it's necessary.
		// If you want to add more columns to the csv file, feel free to do it.
        
        //all yours from here... 
        registerCustomer(testCaseData);
    }

    @DataProvider(name="registerTestCaseData")
    public Object[][] registerTestCaseData(){

        try {
            return Utils.getRegisterData("src/resources/RegisterBodogTest.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

