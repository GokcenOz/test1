package stepDefinitions;


import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import resources.BaseTest;


import java.io.IOException;
import java.time.Duration;


@RunWith(Cucumber.class)
public class CommonSteps extends BaseTest {



    ///****************** Click Steps **************************/


    @When("{string} text değerini içeren elemente tıklanır.")
    public void userClicksToElementTextValue(String text) {
        moveElementOver(findElementByText(text));
        clickByText(text);
    }

    @When("{string} id değerini içeren elemente tıklanır.")
    public void userClicksToElementID(String id) {
        findElement(id).click();
    }

    @When("{string} butonuna tıklanır.")
    public void clickElement(String elementValue) {
        moveElementOver(elementValue);
        clickBy(elementValue);
    }

    @When("{string} tıklanır.")
    public void clickByElement(String elementValue) {
        moveElementOver(elementValue);
        clickBy(elementValue);
    }
    /////////////////////////////


    @And("{string} text değerine sahip elementin visible olduğu onaylanır.")
    public void değerineSahipElementinVisibleOlduğuGörülür(String text) {
        assertTrue(isDisplayedText(text, 22));
    }

    @And("{string} nin visible olduğu onaylanır.")
    public void visibleID(String id) {
        moveElementOver(id);
        assertTrue(findElementVisibleAccordingToSelector(id).isDisplayed());
    }

    @And("{string} nin visible olduğu görülür ve tıklanır.")
    public void verifyElementVisibleThenClick(String id) {
        waitVisibilityOfElementLocated(id, 30).click();
    }

    @And("{string} nin visible olduğu ve değerinin boş olmadığı onaylanır.")
    public void verifyVisibleAndNotNull(String id) {

        WebElement element = findElement(id);
        assertTrue(element.isDisplayed());
        Assert.assertNotNull(element.getText());
    }

    @And("{string} input alanına {string} değeri yazılır.")
    public void sendKeysToTextValue(String elementValue, String text) {

        waitMillisSecond(250);
        moveElementOver(elementValue);
        findElement(elementValue, 20).clear();
        waitMillisSecond(150);
        clearTextAreaAndSendKeys(elementValue, text);
    }





    @And("{string} değerine sahip elementin text değerinin {string} olduğu görülür.")
    public void değerineSahipElementinTextDeğeriOlduğuGörülür(String arg0, String arg1) {

        assertElementTextEqual(findElement(arg0), arg1);
    }

    @And("{string} 'in text değerinin {string} olduğu görülür.")
    public void verifyTextValueEqualTo(String arg0, String arg1) {

        assertElementTextEqual(findElement(arg0), arg1);
    }

    @And("{string} xpath değerine sahip elementin text değerinin {string} olduğu görülür.")
    public void assertXpathValueEqual(String arg0, String value) {

        assertElementTextEqual(findElement(arg0, 30), value);
    }

    @And("{string} 'nın visible olduğu ve text değerinin {string}'ya eşit olduğu görülür.")
    public void visibleAndTextEqual(String elementValue, String text) {

        waitVisibilityOfElementLocated(elementValue, 30);
        waitElementTextEqualTo(elementValue.trim(), text.trim());
    }

    @And("{string} 'nın visible olduğu ve text değerini {string}'yı içerdiği görülür.")
    public void waitVisibleAndTextContains(String elementValue, String text) {

        assertElementTextContains(elementValue, text);
    }

    @And("{string} nin visible ve enabled olduğu doğrulanır.")
    public void visibleAndEnable(String id) {

        WebElement element = findElement(id);
        assertTrue(element.isDisplayed() && element.isEnabled());
        log.info(element + " is enabled and visible");
    }

    @And("{string} 'nın visible olduğu ve değerinin {string}'ya eşit olduğu görülür.")
    public void visibleAndEqualAttribute(String elementValue, String text) {

        waitElementAttributeValueEqualsToDesiredValue(waitVisibilityOfElementLocated(elementValue, 45), "placeholder", text);
    }

    @And("{string} 'nın visible olduğu ve value değerinin {string}'ya eşit olduğu görülür.")
    public void visibleAndValueEqualAttribute(String elementValue, String value) {

        waitElementAttributeValueEqualsToDesiredValue(waitVisibilityOfElementLocated(elementValue, 40), "value", value);
    }

    @And("{string} 'nun tıklanabilir olduğu görülür ve tıklanır.")
    public void isElementClickableAndClick(String elementValue) {

        waitElementToBeClickable(elementValue).click();

    }

    @And("{string} text değerine sahip elementin not visible olduğu onaylanır.")
    public void değerineSahipElementinNotVisibleOlduğuGörülür(String text) {
        assertTrue(!isNotDisplayedText(text, 3));
    }

    @And("{string} 'nın not visible olduğu onaylanır.")
    public void assertElementNotVisible(String xpath) {

        assertTrue(!isDisplayedByXpath(xpath, 1));
    }

    @And("{string} 'nın sayfada olmadığı olduğu onaylanır.")
    public void assertElementNotVisibleByElementValue(String text) {

        assertTrue(!isDisplayed(text, 1));
    }

    @And("{string} text değerine sahip elementin enabled olduğu onaylanır.")
    public void isElementEnabledWithTextValue(String text) {

        assertTrue(findElementByText(text).isEnabled());

    }

    @And("{string} xpath değerine sahip elementin enabled olduğu onaylanır.")
    public void isElementEnabledWithXpathValue(String xpath) {

        assertTrue(findElementByXpath(xpath).isEnabled());
    }

    @And("{string} değerine sahip elementin disabled olduğu onaylanır.")
    public void verifyElementIsDisabled(String text) {

        assertTrue(!findElementByText(text).isEnabled());
    }

    @And("{string} 'nun disabled olduğu görülür.")
    public void isElementDisabled(String text) {

        assertTrue(!findElement(text).isEnabled());
    }

    @And("{string} 'nun enabled olduğu görülür.")
    public void isElementEnabled(String text) {

        assertTrue(findElement(text).isEnabled());
    }

    @And("{string} 'nun not enabled olduğu görülür.")
    public void isElementNotEnabled(String elementValue) {

        assertTrue(!findElement(elementValue).isEnabled());
    }


    @And("{string} 'nun enabled olduğu görülür ve tıklanır.")
    public void isElementEnabledAndClick(String id) {
        clickByJavaScript(id);
        waitMillisSecond(250);
    }


    @And("{int} sn beklenir.")
    public void waitSecond(int seconds) {
        try {
            Thread.sleep(seconds * 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @And("{string} nın visible olduğu ve text değerinin {string} ya eşit olduğu görülür ve tamam butonuna tıklanır.")
    public void nınVisibleOlduğuVeTextDeğerininYaEşitOlduğuGörülürVeTamamButonunaTıklanır(String arg0, String arg1) {

        waitVisibilityOfElementLocated(arg0, 30);
        waitElementTextEqualTo(arg0.trim(), arg1.trim());
        clickBy("//button[@class='btn btn-primary btn-full']");
    }

    @And("Default olarak {string} tabının açık geldiği görülür.")
    public void defaultOlarakTabınınAçıkGeldiğiGörülür(String arg0) {
        assertElementAttributeContains("//*[text()='" + arg0 + "']/parent::a", "class", "active");
    }

    @And("Sol üst köşeden bir önceki ekrana dönmek için <- seçeneğine tıklanır.")
    public void solÜstKöşedenBirÖncekiEkranaDönmekIçinSeçeneğineTıklanır() {
        getDriver().navigate().back();
    }

    @Then("{string} invisible oldugu onaylanır.")
    public void invisibleOlduguOnaylanır(String arg0) {
        waitInvisibilityOfElementLocated(arg0);
        assertTrue(!isDisplayedByXpath(arg0, 1));
    }


    @And("Sayfa kapatılır")
    public void sayfaKapatılır() {
        closeDriver();
    }


    @And("{string} css degerine sahip element tıklanır.")
    public void cssDegerineSahipElementTıklanır(String arg0) {
        clickByCss(arg0);
    }


    @Then("{string} görülmediği onaylanır.")
    public void elementIsNotVisible(String arg0) {

        Assert.assertFalse(isDisplayedByXpath(arg0, 2));

    }

    @And("{string} ekranı görüntüsü doğrulanır.")
    public void verifyImagesEquals(String screenName) throws IOException {
        validateImagesAreEquals(screenName);
    }


    @And(("Sayfa yenilenir."))
    public void refreshPage() {
        getDriver().navigate().refresh();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    @And("{string} Tab tusuna basılır.")
    public void tabTusunaBasılır(String arg0) {
        findElement(arg0).sendKeys(Keys.TAB);
    }


    @And("{string} input alanına {string} değeri yazılır ve enter a basılır.")
    public void sendKeysToTextValueAndPressEnter(String arg0, String arg1) {
        sendKeysToTextValue(arg0, arg1);
        findElement(arg0).sendKeys(Keys.ENTER);
    }

    @And("{string} max length {string} oldugu gorulur.")
    public void maxLengthOlduguGorulur(String arg0, String arg1) {
        assertElementAttributeEqual(findElement(arg0), "maxlength", arg1);
    }

    @And("{string} elementin string degeri kaydedilir.")
    public void saveElementValueAsString(String arg0) {
        saveTextValue(getText(findElement(arg0)));
    }

    @Then("{string} iceren url ayrı tabda acildigi gorulur.")
    public void icerenUrlAyrıTabdaAcildigiGorulur(String arg0) {
        directToNewTab();
        getDriver().getCurrentUrl().contains(arg0);
        waitMillisSecond(200);
        directToMainTab();
    }

    @And("{string} value degeri alınır.")
    public void valueDegeriAlınır(String arg0) {
        saveAttributeValueOfElement(findElement(arg0));
    }

    @And("{string} list {int}.index tıklanır.")
    public void listIndexTıklanır(String arg0, int arg1) {
        clickByElementWithIndex(arg0, arg1);
    }


    @Then("{string} toast mesajı alındıgı verify edilir.")
    public void toastMesajıAlındıgıVerifyEdilir(String arg0) {
        waitMillisSecond(200);
        assertElementDisplayedAndTextEqual("//*[@class='simprakit-toast-message']", arg0);

    }

    @And("{string} editable oldugu onaylanır.")
    public void editableOlduguOnaylanır(String arg0) {
        assertTrue(isTextBoxEditable(findElement(arg0), "text"));
    }


    @And("Yeni tab açılır.")
    public void yeniTabAçılır() {
        directToNewTab();
    }

    @And("Main tab a geri dönülür")
    public void tabInaGeriDönülür() {
        directToMainTab();
    }

    @Given("{string} adresine gidilir.")
    public void adresineGidilir(String arg0) {
        getDriver().get(arg0);
    }

    @And("Tarayıcıda geri butonu tıklanır.")
    public void tarayıcıdaGeriButonuTıklanır() {
        getDriver().navigate().back();
    }

    @And("Tab kapatılır.")
    public void closeCurrentTab() {
        getDriver().close();
    }

    @And("Scroll to element {string}")
    public void scrollToElement(String arg0) {
        moveElementOver(arg0);
    }


    @And("{string} input alanı silinir.")
    public void inputAlanıSilinir(String arg0) {
        clearByBackSpace(findElement(arg0));
    }

    @Then("{string} input altında {string} görüldüğü onaylanır.")
    public void inputAltındaGörüldüğüOnaylanır(String arg0, String arg1) {
        assertElementDisplayedAndTextEqual("//*[@name='" + arg0 + "']//following::span[1]", arg1);
    }

    @And("{string} switch butonu tıklanır.")
    public void switchButonuTıklanır(String arg0) {
        clickByXpath("//*[.='" + arg0 + "']//following::div[@class='simprakit-switch-base'][1]");
    }

    @And("Scroll Down")
    public void scrollDown() {
        scrollDownByJS();
    }

    @And("{string} üzerine gidilir ve tıklanır.")
    public void üzerineGidilirVeTıklanır(String arg0) {
        hoverElementAndCLick(arg0);
    }

    @And("{string} input alanına {int} karakterden fazla değer girilir.")
    public void inputAlanınaKarakterdenFazlaDegerGirilir(String str, int i) {

        WebElement element = findElement(str);

        Faker faker = new Faker();
        String randomText = faker.lorem().characters(i+10);
        System.out.println(randomText);
        clearTextAreaAndSendKeys(element, randomText);

    }

}