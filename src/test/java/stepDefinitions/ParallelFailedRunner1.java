package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failedRerun1.txt",
        glue = "stepDefinitions",
        monochrome = true,
        tags = "(@Link2) and (not @ignore)",
        publish = true,
        plugin = {"pretty",
                "html:test-output/SparkReport/cucumber-html-failed-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:target/failedRerun1.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"

               })
public class ParallelFailedRunner1 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
