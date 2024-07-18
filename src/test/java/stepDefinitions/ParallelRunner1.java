package stepDefinitions;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/java/TestSuite",
        glue = "stepDefinitions",
        monochrome = true,
        publish = true,
        tags = "(@Link2) and (not @ignore)",
        plugin = { "pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:target/failedRerun1.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class ParallelRunner1 extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
