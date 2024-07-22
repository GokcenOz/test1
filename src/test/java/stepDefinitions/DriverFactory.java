package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import resources.BaseTest;

import java.util.*;


public class DriverFactory extends BaseTest {


    @Before("@UItest")
    public static void getDesktopDriver(Scenario scenario) {


        PropertiesFile();
        try {
            getDriver().getCurrentUrl();
        } catch (Exception e) {
            try {
                System.setProperty("webgetDriver().chrome.whitelistedIps", "");

                log.info(" ********** Starting Test Case Execution *************");
                log.info("-----------------------------------------");

                // WebgetDriver()Manager.chromegetDriver()().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--kiosk");
                //chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                chromeOptions.addArguments("disable-translate");
                chromeOptions.addArguments("--verbose");
                chromeOptions.addArguments("--lang=tr");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("-window-size=1980x1020");
                chromeOptions.addArguments("--remote-allow-origins=*");


                if (getProperty("browser").equalsIgnoreCase("chrome")) {
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    prefs.put("intl.accept_languages", "tr");
                    chromeOptions.setExperimentalOption("prefs", prefs);
                }

                driver.set(new ChromeDriver(chromeOptions));
               /*getDriver().get(getProperty("url"));*/
                getDriver().manage().window().maximize();

            } catch (Exception t) {
                log.error("Unable to load getDriver(): " + t.getMessage());

            }
        }
    }

    @After("@UItest")
    public void tearDownAndScreenshotOnFailure(Scenario scenario) {

        try {
            if (scenario.isFailed()) {
                scenario.attach(getByteScreenshot(), "image/png", scenario.getName());
                getScreenShot(scenario, getDriver());
                closeDriver();
            }
        } catch (Exception e) {
            log.info("Methods failed: tearDownAndScreenshotOnFailure, Exception: " + e.getMessage());
            closeDriver();
        }

    }


}
