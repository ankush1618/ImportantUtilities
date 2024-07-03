package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
     features={"src/test/resources/appFeatures/UserRegistration.feature"},
        glue={"stepDefinations"},
        plugin={"pretty",
        "json:target/MyReports/reports.json",
        "junit:target/MyReports/reports.xml"
        },
        monochrome=false,
     dryRun = false
)
public class UserRegistrationTest {



}
