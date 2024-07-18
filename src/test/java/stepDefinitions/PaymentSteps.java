package stepDefinitions;

import io.cucumber.java.en.Given;
import resources.BaseTest;

public class PaymentSteps extends BaseTest {


    @Given("go to page")
    public void goToPage() {
        getDriver().get("https://tech.test.simpralink.com/");
    }
}
