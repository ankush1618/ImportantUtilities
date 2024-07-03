package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/appFeatures/Uber.feature"},
        glue={"stepDefinations"},
        tags= "@Prod and @Smoke or ~@Regression",
        plugin = {"pretty",
                "json:target/MyReports/reports.json",
                "junit:target/MyReports/reports.xml"
        },
        monochrome=false, //if true provides proper clean aligned console output
        dryRun=false //cucumber eclipse /Intelli J plugin does the same
        //,publish=true //publish the report link in the console-- we can use cucumber.properties file to do this
)
public class UberTest {

}
