package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/failedRerun.txt",
        glue = "stepDefinitions",
        monochrome = true,
        tags = "(@UI) and (not @ignore)",
        publish = true,
        plugin = { "pretty",
                "html:test-output/SparkReport/failed-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
                "rerun:target/failedRerun.txt",
               })

                


public class FailedRunner {



}
