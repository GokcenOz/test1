package resources;

import com.github.javafaker.Faker;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BaseTest {

    public static ThreadLocal<WebDriver> driver = new InheritableThreadLocal<>();

    public static Logger log = Logger.getLogger(BaseTest.class);
    private static String SAVED_ATTRIBUTE;
    private static String SAVED_ATTRIBUTE_SECOND;
    private static String DATE_VALUE;
    private static String SAVED_TEXT;
    private static String SAVED_TEXT_SECOND;
    private static String SAVED_TEXT_THIRD;
    private static int SAVED_INT;
    private static double SAVED_DOUBLE;
    private static Properties prop;
    private static List<String> SAVED_TEXT_LİST;
    private static List<Object> SAVED_API_LİST;
    private static List<WebElement> SAVED_LİST;

    public BaseTest() {
        PropertyConfigurator.configure("log4j.properties");
    }

    public static void PropertiesFile() {

        prop = new Properties();
        try {
            InputStream input = new FileInputStream("src/test/java/properties/config.properties");
            prop.load(input);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public FluentWait getFluentWait(int time) {
        FluentWait<WebDriver> driverFluentWait;
        driverFluentWait = new FluentWait<>(getDriver());
        driverFluentWait.withTimeout(Duration.ofSeconds(time))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
        return driverFluentWait;
    }
    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

    public static String getProperty(String value) {
        return prop.getProperty(value);
    }

    public static String getSavedAttribute() {
        return SAVED_ATTRIBUTE;
    }

    public static String getDateValue() {
        return DATE_VALUE;
    }

    public static String getSavedText() {
        return SAVED_TEXT;
    }

    public static int getSavedInt() {
        return SAVED_INT;
    }

    public static double getSavedDouble() {
        return SAVED_DOUBLE;
    }

    public static String getSecondSavedText() {
        return SAVED_TEXT_SECOND;
    }

    public static String getThirdSavedText() {
        return SAVED_TEXT_THIRD;
    }

    public void saveTextValue(String text) {
        SAVED_TEXT = text;
        log.info("Saved text value is: " + SAVED_TEXT);
    }

    public void saveIntValue(int i) {
        SAVED_INT = i;
        log.info("Saved int value is: " + SAVED_INT);
    }

    public void saveDoubleValue(double d) {
        SAVED_DOUBLE = d;
        log.info("Saved double value is: " + SAVED_DOUBLE);
    }

    public void saveSecondTextValue(String text) {
        SAVED_TEXT_SECOND = text;
        log.info("Saved second text value is: " + SAVED_TEXT_SECOND);
    }

    public void saveThirdTextValue(String text) {
        SAVED_TEXT_THIRD = text;
        log.info("Saved third text value is: " + SAVED_TEXT_THIRD);
    }

    public List<String> savedTextList(List<WebElement> elements) {

        List<String> savedtexts = new ArrayList<>();
        for (WebElement element : elements) {
            waitPresenceOfElementLocatedAndScrollToElement(element);
            savedtexts.add(element.getText());
        }
        SAVED_TEXT_LİST = savedtexts;
        log.info("Saved List is " + SAVED_TEXT_LİST);
        return SAVED_TEXT_LİST;

    }

    public List<Object> savedApiList(List<Object> elements) {

        List<Object> savedtexts = new ArrayList<>();
        for (Object element : elements) {
            savedtexts.add(element);
        }
        SAVED_API_LİST = savedtexts;
        log.info("Saved Api List is " + SAVED_API_LİST);
        return SAVED_API_LİST;

    }

    public List<WebElement> savedList(List<WebElement> elements) {

        List<WebElement> list = new ArrayList<>();
        for (WebElement element : elements) {
            waitPresenceOfElementLocatedAndScrollToElement(element);
            list.add(element);
        }
        SAVED_LİST = list;
        log.info("Saved List is " + SAVED_LİST);
        return SAVED_LİST;

    }

    public static List<String> getSavedTextList() {
        return SAVED_TEXT_LİST;
    }

    public static List<WebElement> getSavedList() {
        return SAVED_LİST;
    }

    public static List<Object> getSavedApiList() {
        return SAVED_API_LİST;
    }

    public static String getSavedAttributeSecond() {

        return SAVED_ATTRIBUTE_SECOND;
    }

    public void saveAttributeValueOfElement(WebElement element) {
        SAVED_ATTRIBUTE = getElementAttribute(element, "value");
        log.info("Saved attribute value is: " + SAVED_ATTRIBUTE);
    }

    public void saveDateValue() {
        DATE_VALUE = new SimpleDateFormat("HH:mm").format(new Date());
        log.info("Saved attribute value is: " + DATE_VALUE);
    }

    public void saveSecondAttributeValueOfElement(WebElement element) {
        SAVED_ATTRIBUTE_SECOND = getText(element);
        log.info("Saved attribute value is: " + SAVED_ATTRIBUTE_SECOND);
    }


    public WebElement findElementPresenceAccordingToSelector(String elementValue, int time) {

        if (elementValue.contains("//")) {
            return (WebElement) getFluentWait(time).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementValue)));

        } else if (elementValue.contains("[")) {
            return (WebElement) getFluentWait(time).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(elementValue)));
        } else {
            return (WebElement) getFluentWait(time).until(ExpectedConditions.visibilityOfElementLocated(By.id(elementValue)));
        }
    }

    public WebElement findElementVisibleAccordingToSelector(String elementValue) {

        if (elementValue.contains("//")) {
            return (WebElement) getFluentWait(16).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementValue)));

        } else if (elementValue.contains("[")) {
            return (WebElement) getFluentWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementValue)));
        } else {
            return (WebElement) getFluentWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.id(elementValue)));
        }
    }

    public void closeDriver() {

        getDriver().manage().deleteAllCookies();
        getDriver().quit();
        log.info("Simpra Desktop app quited and deleted all cookies");

    }


    public void moveElementOver(String value) {
        new Actions(getDriver()).moveToElement(findElement(value, 15)).build().perform();
    }

    public void moveElementOver(String value, int time) {
        new Actions(getDriver()).moveToElement(findElement(value, time)).build().perform();
    }

    public void hoverElementAndCLick(String value) {

        WebElement element = findElement(value, 20);

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                element);
        new Actions(getDriver()).moveToElement(element).build().perform();
        element.click();

    }

    public void moveElementOver(WebElement element) {
        new Actions(getDriver()).moveToElement(findElement(element)).build().perform();
    }

    //// //// //// //// //// //// //// //// //// //// Waiting Methods //// //// //// //// //// //// //// //// //// //// //// ////

    public WebElement waitVisibleOfElementLocated(WebElement element, int time) {

        WebElement element1 = (WebElement) getFluentWait(time).until(ExpectedConditions.visibilityOf(element));
        log.info(element1 + " is displayed");
        return element1;
    }
    public WebElement waitVisibleOfElementLocated(String element, int time) {

        WebElement element1 = (WebElement) getFluentWait(time).until(ExpectedConditions.visibilityOf(findElementVisibleAccordingToSelector(element)));
        log.info(element1 + " is displayed");
        return element1;
    }

    public WebElement waitVisibleOfElementLocated(WebElement element) {

        return (WebElement) getFluentWait(30).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitPresenceOfElementLocated(String elementValue) {

        return findElementPresenceAccordingToSelector(elementValue, 10);
    }

    public WebElement waitPresenceOfElementLocated(String elementValue, int time) {

        return findElementPresenceAccordingToSelector(elementValue, time);
    }

    public WebElement waitVisibilityOfElementLocated(String elementValue, int time) {

        return findElementVisibleAccordingToSelector(elementValue);
    }

    public static void waitElementToBeEnabled(String elementValue, int time) {

        int count = 0;
        while (count < time) {
            try {
                Assert.assertTrue(getDriver().findElement(By.xpath(elementValue)).isEnabled());
                return;
            } catch (Throwable e) {
                waitMillisSecond(1000);
                count++;
                if (count == time) {
                    Assert.fail(elementValue + " is not enabled !!!!");
                }
            }
        }
    }

    public WebElement waitVisibilityOfElementLocatedAndScrollToElement(String elementValue, int time) {

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                findElementVisibleAccordingToSelector(elementValue));
        log.info(findElementVisibleAccordingToSelector(elementValue) + " is visible");
        return findElementVisibleAccordingToSelector(elementValue);
    }

    public WebElement waitPresenceOfElementLocatedAndScrollToElement(String elementValue) {

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                findElementPresenceAccordingToSelector(elementValue, 30));
        log.info(findElementPresenceAccordingToSelector(elementValue, 30) + " is present");
        return findElementPresenceAccordingToSelector(elementValue, 30);
    }

    public WebElement waitPresenceOfElementLocatedAndScrollToElement(WebElement element) {

        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                element);
        log.info(element + " is present");
        return element;
    }

    public void waitElementSizeToBe(String elementValue, int size) {

        if (elementValue.contains("//"))
            getFluentWait(30).until(ExpectedConditions.numberOfElementsToBe(By.xpath(elementValue), size));
        else
            getFluentWait(30).until(ExpectedConditions.numberOfElementsToBe(By.id(elementValue), size));
    }

    public void waitElementSizeToBeMoreThan(String elementValue, int size) {

        if (elementValue.contains("//"))
            getFluentWait(30).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(elementValue), size));
        else
            getFluentWait(30).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id(elementValue), size));
    }


    public void waitInvisibilityOfElementLocated(WebElement element) {

        getFluentWait(15).until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitInvisibilityOfElementLocated(String element) {

        getFluentWait(45).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
    }

    public void waitElementAttributeValueEquals(WebElement element, String attiribute, String value) {

        getFluentWait(30).until(ExpectedConditions.attributeToBe(element, attiribute, value));
    }

    public void waitElementTextValueEqualTo(String elementValue, String value) {


        if (elementValue.contains("//")) {
            getFluentWait(30).
                    until(ExpectedConditions.attributeToBe(By.xpath(elementValue), "text", value));
        } else {
            getFluentWait(30).
                    until(ExpectedConditions.attributeToBe(By.id(elementValue), "text", value));

        }
    }

    public void waitElementVisibleByStream(String elementValue, String text) {
        int count = 0;
        while (count < 5) {
            waitMillisSecond(100);
            if (isDisplayed(elementValue, 1)) {
                log.info(findElements(elementValue).stream().map(WebElement::getText));
                assertTrue(findElements(elementValue).stream().map(WebElement::getText).anyMatch(s -> s.equalsIgnoreCase(text)));
                return;
            }
            count++;
            if (count == 5) {
                Assert.fail("Element is not visible");
            }
        }
    }

    public String getElementAttribute(WebElement element, String attribute) {
        return findElement(element).getAttribute(attribute);
    }

    public String getElementAttribute(String elementValue, String attribute) {
        return findElementByID(elementValue).getAttribute(attribute);
    }

    public Integer getElementSize(String elementValue) {
        return findElements(elementValue).size();
    }

    ////////////////////////////////////////////////////////// Find Element methods //////////////////////////////

    public WebElement findElement(WebElement element) {
        return waitVisibleOfElementLocated(element, 40);
    }

    public WebElement findElement(WebElement element, int time) {
        return waitVisibleOfElementLocated(element, time);
    }

    public WebElement findElement(String elementValue, int time) {
        return waitPresenceOfElementLocated(elementValue, time);
    }
    public WebElement findElement(String elementValue) {
        return waitVisibleOfElementLocated(elementValue, 30);
    }
    public WebElement findElementByID(String id) {
        return waitPresenceOfElementLocated(id);
    }

    public WebElement findElementByIndex(String xpath, int index) {
        return findElementsByXpath(xpath).get(index);
    }


    public List<WebElement> findElements(String id) {

        Wait<WebDriver> wait = new FluentWait<>(getDriver());

        ((FluentWait<?>) wait).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        if (id.contains("//"))
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(id)));
        else if (id.contains("["))
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(id)));
        else
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(id)));
    }


    public List<WebElement> findElements(List<WebElement> elements) {

        return (List<WebElement>) getFluentWait(30).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public WebElement findElementByXpath(String xpath) {
        return waitPresenceOfElementLocated(xpath,15);
    }

    public List<WebElement> findElementsByXpath(String xpath) {
        return (List<WebElement>) getFluentWait(30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public WebElement findElementByText(String text) {
        String value = "//*[contains(text(),'" + text + "')]";
        return waitVisibilityOfElementLocated(value, 30);
    }

    public WebElement findElementByText(String text, int time) {
        String xpath = "//*[contains(text(),'" + text + "')]";
        return waitPresenceOfElementLocated(xpath, time);
    }

    //////////////////////////////////////////////////////////////// SendKeys Methods  ////////////////////////////////////////////////////////////////


    public static void clearByBackSpace(WebElement element) {
        String inputText = element.getAttribute("value");
        if( inputText != null ) {
            for(int i=0; i<inputText.length();i++) {
                element.sendKeys(Keys.BACK_SPACE);
            }
        }

    }

    public void setTxtArea(String value, String text) {
        WebElement entityName;
        entityName = findElementVisibleAccordingToSelector(value);
        waitMillisSecond(500);
        entityName.clear();
        entityName.sendKeys(text);
    }


    public void sendsKeysToElement(WebElement element, String text) {

        findElement(element).sendKeys(text);
        log.info(text + " text value is sent to " + element + " text area ");

    }

    public void clearTextAreaAndSendKeys(WebElement element, String text) {

        waitMillisSecond(200);
        clearTextArea(element);
        element.sendKeys(text);
        log.info("Cleared " + element + " text area and " + text + " text value is sent to " + element);

    }

    public void clearTextAreaAndSendKeys(String value, String text) {

        WebElement element = findElementByID(value);
        while (!element.getAttribute("value").equals("")) {
            try {
                element.sendKeys(Keys.BACK_SPACE);
            } catch (ElementNotInteractableException e) {
                waitBy(2);
                element.sendKeys(Keys.BACK_SPACE);
            }
        }
        element.sendKeys(text);
        log.info("Cleared " + value + " text area and " + text + " text value is sent to " + text);

    }

    public void clearTextArea(WebElement element) {

        findElement(element).clear();
    }

    public void clearTextArea(String element) {

        findElement(element, 30).clear();
    }

    public void sendKeysValueOfCharToChar(WebElement element, String text) {

        clearTextArea(element);

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);
            findElement(element).sendKeys(String.valueOf(c));
        }
        log.info(text + "' has written to '" + element + "' element.");
    }

    public void sendKeysToElementByNumpad(WebElement element, String text) {

        for (int i = 0; i < 5; i++) {
            element.sendKeys(Keys.BACK_SPACE);
            waitMillisSecond(200);
        }
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '1':
                    element.sendKeys(Keys.NUMPAD1);
                    break;
                case '2':
                    element.sendKeys(Keys.NUMPAD2);
                    break;
                case '3':
                    element.sendKeys(Keys.NUMPAD3);
                    break;
                case '4':
                    element.sendKeys(Keys.NUMPAD4);
                    break;
                case '5':
                    element.sendKeys(Keys.NUMPAD5);
                    break;
                case '6':
                    element.sendKeys(Keys.NUMPAD6);
                    break;
                case '7':
                    element.sendKeys(Keys.NUMPAD7);
                    break;
                case '8':
                    element.sendKeys(Keys.NUMPAD8);
                    break;
                case '9':
                    element.sendKeys(Keys.NUMPAD9);
                    break;
                case '0':
                    element.sendKeys(Keys.NUMPAD0);
                    break;
            }
        }
    }

    public boolean isTextBoxEditable(WebElement element, String text) {

        findElement(element).sendKeys(text);
        element.clear();
        log.info(element + " text area is verified as editable");
        return true;

    }

    //////////////////////////////////////////////////////////////// Wait methods  ////////////////////////////////////////////////////////////////


    public void waitBy(int seconds) {
        try {
            Thread.sleep(seconds * 1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitMillisSecond(int millisSeconds) {
        try {
            Thread.sleep(millisSeconds);
            log.info(millisSeconds + " mili seconds waiting");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement waitElementToBeClickable(WebElement element) {

        return (WebElement) getFluentWait(30).until(ExpectedConditions.elementToBeClickable(element));
    }


    public WebElement waitElementToBeClickable(String elementValue) {

        if (elementValue.contains("//"))
            return (WebElement) getFluentWait(30).until(ExpectedConditions.elementToBeClickable(By.xpath(elementValue)));
        else if (elementValue.contains("["))
            return (WebElement) getFluentWait(30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(elementValue)));
        else
            return (WebElement) getFluentWait(30).until(ExpectedConditions.elementToBeClickable(By.id(elementValue)));
    }

    public void waitElementTextEqualTo(String elementValue, String text) {

        if (elementValue.contains("//"))
            getFluentWait(30).until(ExpectedConditions.textToBe(By.xpath(elementValue), text));
        else if (elementValue.contains("["))
            getFluentWait(30).until(ExpectedConditions.textToBe(By.cssSelector(elementValue), text));
        else
            getFluentWait(30).until(ExpectedConditions.textToBe(By.id(elementValue), text));
    }

    public void waitElementTextEqualTo(WebElement element, String text) {

        getFluentWait(15).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitElementTextEqualTo(String elementValue, String text, int time) {

        if (elementValue.contains("//"))
            getFluentWait(time).until(ExpectedConditions.textToBe(By.xpath(elementValue), text));
        else
            getFluentWait(time).until(ExpectedConditions.textToBe(By.id(elementValue), text));

    }


//**************************************************************************************************


    public boolean isDisplayed(WebElement element) {
        try {
            return findElement(element).isDisplayed();

        } catch (Exception e) {

            log.info(element + " element is not visible");
            return false;
        }
    }


    public boolean isDisplayed(WebElement element, int time) {
        try {

            Boolean b = findElement(element, time).isDisplayed();
            log.info(element + " element is displayed");
            return b;

        } catch (Exception e) {
            log.info(element + " element is not displayed");
            return false;
        }
    }

    public boolean isDisplayed(String ID, int time) {

        try {
            return waitVisibilityOfElementLocated(ID, time).isDisplayed();
        } catch (Exception e) {
            log.info(ID + " is not visible");
            return false;
        }
    }

    public boolean isDisplayedByXpath(String xpath, int time) {

        try {
            return waitPresenceOfElementLocated(xpath, time).isDisplayed();
        } catch (Exception e) {
            log.info(xpath + " is not visible");
            return false;
        }
    }

    public boolean isDisplayedText(String text) {

        try {
            Boolean isDisplayed = findElementByText(text).isDisplayed();
            log.info(text + " value  is visible");
            return true;

        } catch (Exception e) {
            log.info(text + " value is not visible");
            return false;

        }
    }

    public boolean isDisplayedText(String text, int time) {

        try {
            findElementByText(text, time).isDisplayed();
            log.info(text + " value  is visible");
            return true;

        } catch (Exception e) {
            log.info(text + " value is not visible");
            return false;

        }
    }

    public void assertIsElementDisplayedAndClickable(WebElement element) {

        Assert.assertTrue(isElementClickable(element, 2) && element.isDisplayed());

    }
    public void assertElementIsDisplayed(String elementValue) {

        assertTrue(findElement(elementValue).isDisplayed());
    }


    public void assertAllElementsTextIsEqualWithStream(List<WebElement> elements, String text) {
        assertTrue(elements.stream().allMatch(t -> t.getText().equalsIgnoreCase(text)));
    }


    public void assertAllElementsTextContainsWithStream(List<WebElement> elements, String text) {
        assertTrue(elements.stream().allMatch(s -> s.getText().toLowerCase().contains(text)));
    }

    public void assertElementValueEqual(WebElement element, String value) {

        assertTrue(getElementAttribute(element, "value").equals(value));
    }

    public void assertElementDisplayed(WebElement element) {

        assertTrue(findElement(element).isDisplayed());
    }

    public void assertElementDisplayed(String elementValue) {

        assertTrue(findElementVisibleAccordingToSelector(elementValue).isDisplayed());
    }

    public void assertElementIsDisplayedByElementValue(String elementValue) {

        assertTrue(findElementByID(elementValue).isDisplayed());
    }

    public void assertElementIsDisplayedAndSizeEqualTo(String elementValue, int size) {

        List<WebElement> element = findElements(elementValue);
        assertTrue(element.get(0).isDisplayed() && element.size() == size);
    }


    public void assertElementDisplayedAndSizeGreaterThan(String elementValue, int size) {

        List<WebElement> element = findElements(elementValue);
        getFluentWait(30).until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(elementValue), size));
        System.out.println(element.size());
        //assertTrue(element.get(0).isDisplayed());
    }


    public void assertElementDisplayedAndTextEqual(WebElement elementID, String value) {

        WebElement element = findElement(elementID);
        assertTrue(element.isDisplayed() && element.getText().trim().equals(value));
    }

    public void assertElementDisplayedAndTextEqual(String elementValue, String value) {

        assertTrue(waitVisibilityOfElementLocated(elementValue, 30).isDisplayed() &&
                waitVisibilityOfElementLocated(elementValue, 30).getText().trim().equals(value));
        log.info(elementValue + " is displayed and text equal to " + value);
    }

    public boolean waitElementIsDisplayed(String elementValue, int time) {

        try {
            return findElement(elementValue, time).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void assertElementAttributeEqual(WebElement element, String attribute, String value) {

        getFluentWait(30).until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public void assertElementIsDisplayedAndTextEqual(WebElement element, String value) {
        assertElementTextEqual(findElement(element), value);
    }

    public void assertElementIsDisplayedAndAttributeEqual(WebElement element, String attribute, String value) {
        assertTrue(findElement(element, 20).getAttribute(attribute).contains(value));
    }

    public void assertElementAttributeContains(String elementValue, String attribute, String value) {
        assertTrue(findElement(elementValue, 20).getAttribute(attribute).contains(value));
    }

    public void assertElementAttributeNotContains(String elementValue, String attribute, String value) {
        assertTrue(!findElement(elementValue, 20).getAttribute(attribute).contains(value));
    }

    public void assertElementNotDisplayed(WebElement element) {
        Assert.assertTrue(!isDisplayed(element, 1));
    }

    public void assertElementNotDisplayed(String element) {
        Assert.assertTrue(!isDisplayed(element, 1));
    }

    public void assertElementsTextIsEqualWithStream(List<WebElement> elements, String text) {
        assertTrue(elements.stream().map(s -> s.getText().trim()).anyMatch(s -> s.equalsIgnoreCase(text)));
    }

    public void assertTrue(Boolean b) {
        Assert.assertTrue(b);
    }

    public void assertElementIsSelected(WebElement element) {
        assertTrue(element.isSelected());
    }

    public void assertElementSelected(String element) {
        waitMillisSecond(250);
        assertTrue(findElement(element, 20).isSelected());
    }

    public void assertElementNotSelected(String element) {
        assertTrue(!findElement(element, 20).isSelected());
    }

    public void assertElementIsNotSelected(WebElement element) {
        assertTrue(!element.isSelected());
    }

    public void assertTextIsDisplayed(String text) {
        assertTrue(findElementByText(text).isDisplayed());
        log.info(text + " is displayed");
    }

    public void assertEquals(String a, String b) {
        Assert.assertEquals(a, b);
    }

    public void assertIsElementClickable(WebElement element) {
        Assert.assertTrue(isElementClickable(element, 2));
    }

    public void assertElementTextEqual(WebElement element, String text) {

        String actual = element.getText().replaceAll("[\\n\\t ]", "").trim();
        String expected = text.replaceAll("[\\n\\t ]", "").trim();
        assertTrue(actual.equalsIgnoreCase(expected));
    }

    public void assertElementTextEqual(String elementValue, String text) {

        assertTrue(waitVisibilityOfElementLocated(elementValue, 30).getText().replaceAll("[\\n\\t ]", "").trim()
                .equalsIgnoreCase(text.replaceAll("[\\n\\t ]", "").trim()));
    }

    public void assertElementTextContains(String elementValue, String text) {

        assertTrue(waitVisibilityOfElementLocated(elementValue, 30).getText().replaceAll("[\\n\\t ]", "").trim()
                .contains(text.replaceAll("[\\n\\t ]", "").trim()));
        log.info(elementValue + " is displayed and text contains " + text);
    }

    public void assertElementTextContains(WebElement elementValue, String text) {

        assertTrue(waitVisibleOfElementLocated(elementValue).getText().replaceAll("[\\n\\t ]", "").trim()
                .contains(text.replaceAll("[\\n\\t ]", "").trim()));
    }

    public boolean isNotDisplayedText(String text, int time) {

        try {
            Boolean isDisplayed = findElementByText(text, time).isDisplayed();
            log.info(text + " value  is visible");
            return isDisplayed;

        } catch (Exception e) {

            log.info(text + " value is not visible");
            return false;

        }
    }

    public String getText(WebElement element) {
        return findElement(element).getText().trim();
    }

    public String getText(String elementValue) {
        return findElementByID(elementValue).getText().trim();
    }

    public String waitElementVisibleThenGetText(String elementValue) {
        return waitVisibilityOfElementLocated(elementValue, 20).getText().trim();
    }
    ////////////////////////////////////////////////////// Assertion Methods  ////////////////////////////////////////////////////////////////


    public void assertElementEnabled(WebElement element) {
        Assert.assertTrue(element + " is not enabled !", findElement(element).isEnabled());
    }

    public void assertElementEnabled(String elementValue) {
        Assert.assertTrue(elementValue + " is not enabled !", findElement(elementValue, 30).isEnabled());
    }

    public void assertElementNotEnabled(String elementValue) {
        Assert.assertTrue(elementValue + " is enabled !", !(findElement(elementValue, 30).isEnabled()));
    }

    public void assertElementNotEnabled(WebElement element) {
        Assert.assertTrue(element + " is enabled !", !(findElement(element).isEnabled()));
    }


    public boolean isElementClickable(WebElement element, int time) {

        try {
            waitElementToBeClickable(element);
            return true;

        } catch (Exception e) {

            log.info("Element " + element + " not clickable ");
            return false;

        }
    }

    //////////////////////////////////////////////////////////////// Tapping and CLicking Methods ///////////////////////////////////////////////

    public void dragAndDropElement(WebElement currentElement, WebElement desiredDestinationElement) {

        Actions act = new Actions(getDriver());
        act.dragAndDrop(findElement(currentElement), findElement(desiredDestinationElement)).build().perform();
        log.info(currentElement + " drags and drops to " + desiredDestinationElement);
    }


    public Boolean waitElementTextValueEqualsToDesiredValue(String elementValue, String value) {

        getFluentWait(30).until(ExpectedConditions.textToBe(By.xpath(elementValue), value));
        return true;
    }
    public void assertElementsTextIsNotEqualWithStream(List<WebElement> elements, String text) {
        assertTrue(!elements.stream().map(s -> s.getText()).anyMatch(s -> s.equalsIgnoreCase(text)));
    }

    public void waitElementAttributeValueContainsDesiredValue(WebElement element, String attiribute, String value) {

        getFluentWait(30).until(ExpectedConditions.attributeContains(element, attiribute, value));

    }
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public void doubleClickOfElement(WebElement element) {

        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);
        actions.doubleClick().build().perform();
        log.info("Double clicked of element: " + element);
    }

    public void doubleClickOfElement(String element) {

        Actions actions = new Actions(getDriver());
        actions.moveToElement(findElement(element, 30));
        actions.doubleClick().build().perform();
        log.info("Double clicked of element: " + element);
    }

    public void waitVisibilityOfElementAndClick(String value) {
        waitVisibilityOfElementLocated(value, 40).click();
        log.info(value + " is clicked");
    }

    public void clickByElementWithIndex(String xpath, int index) {
        findElements(xpath).get(index).click();
        log.info(xpath + " with index" + index + " is clicked");

    }

    public void clickByScroll(String elementValue) {
        try {
            waitPresenceOfElementLocatedAndScrollToElement(elementValue).click();
        } catch (ElementNotInteractableException | StaleElementReferenceException e) {
            waitMillisSecond(500);
            clickBy(elementValue);
        }
    }

    public void clickByScroll(WebElement element) {
        try {
            waitPresenceOfElementLocatedAndScrollToElement(element).click();
        } catch (ElementNotInteractableException e) {
            waitMillisSecond(500);
            waitPresenceOfElementLocatedAndScrollToElement(element);
            clickBy(element);
        }

    }
    public void clickWithTryCath(WebElement element) {
        try {
            element.click();
        } catch (Exception e) {
            waitBy(3);
            element.click();
        }
        log.info(element + " is clicked");
    }


    public void waitElementAttributeValueEqualsToDesiredValue(WebElement element, String attiribute, String value) {

       getFluentWait(30).until(ExpectedConditions.attributeToBe(element, attiribute, value));

    }

    public void clickBy(WebElement element) {
        waitElementToBeClickable(element).click();
        log.info(element + " is clicked");
    }



    public void clickBy(String value) {
        try {
            waitElementToBeClickable(findElement(value, 15)).click();
        } catch (StaleElementReferenceException e) {
            waitBy(2);
            waitElementToBeClickable(findElement(value, 12)).click();
        }
        log.info(value + " is clicked");
    }

    public void clickByJavaScript(String elementValue) {
        WebElement nameInputField = findElement(elementValue, 30);
        JavascriptExecutor executor = (JavascriptExecutor) getDriver();
        executor.executeScript("arguments[0].click();", nameInputField);
    }


    public void clickByCss(String element) {
        getFluentWait(30).until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));
        getDriver().findElement(By.cssSelector(element)).click();
        log.info(element + " is clicked");
    }


    public void clickByXpath(String xpath) {
        findElementByXpath(xpath).click();
        log.info(xpath + " is clicked");
    }

    public void clickByText(String text) {
        findElementByText(text).click();
        log.info(text + " is clicked");
    }

    //////////////////////////////////////////  Utils ////////////////////////////////////////////////////////////




    public void getScreenShot(Scenario scenario, WebDriver driver) throws IOException {

        File screenShotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        final byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/*", scenario.getName());
        FileUtils.copyFile(screenShotFile, new File("test-output/" + scenario.getName() + ".png"));
    }

    public byte[] getByteScreenshot() throws IOException {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(src);
        return fileContent;
    }


    public String getQRCodeTextValue(WebElement element) throws IOException, NotFoundException {

        String barCodeURL = findElement(element).getAttribute("src");
        URL url = new URL(barCodeURL);
        BufferedImage bufferedImage = ImageIO.read(url);
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        return result.getText();
    }

    public void validateImageEqual(WebElement element, String imageName) throws IOException {
        Screenshot logoImageScreenshot = new AShot().takeScreenshot(getDriver(), findElement(element));
        ImageIO.write(logoImageScreenshot.getImage(), "png", new File("src/test/java/appImages/" + imageName + ".png"));
    }

    public void directToNewTab() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.open('');");
        ArrayList<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1)); //switches to new tab
    }

    public void directToMainTab() {

        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(0)); // switch back to main screen
    }


    public void validateImagesAreEquals(String screenName) throws IOException {

        BufferedImage expectedImage;
        try {
            expectedImage = ImageIO.read(new File("images/" + screenName + ".png"));
        } catch (Exception e) {
            expectedImage = ImageIO.read(new File("images/default.png"));
            log.info("Image not saved");
        }
        File screenShotFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShotFile, new File("test-output/ExpectedImage/Ödeme.png"));
        BufferedImage actualImage = ImageIO.read(new File("test-output/ExpectedImage/Ödeme.png"));
        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff diff = imageDiffer.makeDiff(expectedImage, actualImage);
        Assert.assertFalse("Expected images are not same", diff.hasDiff());
        log.info("Images are equals");

    }

    //////////////////////////////////////////  Utils ////////////////////////////////////////////////////////////


    public String Split(String text, String chars, int index) {
        String[] str = text.split(chars);
        String strText = str[index];
        return strText;
    }



    public String currentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String formattedDate = sdf.format(date);
        return (formattedDate);
    }

    public static String getCurrentDate(String dateFormat) {
        DateFormat dateType = new SimpleDateFormat(dateFormat);
        //get current date time with Date()
        Date date = new Date();
        // Now format the date
        String currentDate = dateType.format(date);
        return currentDate;
    }

    public static String addDay(String date, int dayCount) {
        return LocalDate.parse(date).plusDays(dayCount).toString();
    }




    public static void scrollDownByJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) getDriver());
        jsexecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    }

}

