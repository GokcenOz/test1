package stepDefinitions;

import io.cucumber.java.en.Given;
import resources.BaseTest;

public class PaymentSteps extends BaseTest {


    @Given("go to page")
    public void goToPage() {
        getDriver().get("https://tech.test.simpralink.com/");
    }
    
     @When("ödeme bilgileri sayfasının geldiği doğrulanır")
    public void ödemeBilgileriSayfasınınGeldiğiDoğrulanır() {
        waitBy(5);
        assertElementDisplayed("//h3[normalize-space()='Ödeme Bilgileri']");
    }

    @Then("payment result sayfasının açıldığı onaylanır.")
    public void paymentResultSayfasınınAçıldığıOnaylanır() {
        waitBy(5);
        assertTrue(getDriver().getCurrentUrl().contains("payment-result"));
    }

    @When("güvenlik ekranının geldiği doğrulanır.")
    public void güvenlikEkranınınGeldiğiDoğrulanır() {
        waitBy(6);
        assertElementDisplayed("//h6[normalize-space()='3D SECURE ÖDEME DOĞRULAMA']");
    }

    @Given("go to CP page.")
    public void go_to_cp_page() {
    getDriver().get(getProperty("url1"));
    }


    @When("güvenlik şifresi ekranının geldiği doğrulanır.")
    public void güvenlikŞifresiEkranınınGeldiğiDoğrulanır() {
        waitBy(6);
        assertElementDisplayed("//h3[normalize-space()='Üç Boyutlu Güvenlik Sistemi']");
    }
}
