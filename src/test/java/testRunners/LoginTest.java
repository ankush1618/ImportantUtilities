package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/appFeatures/Login.feature"},
        glue={"stepDefinations"},
        plugin = {"pretty",
                "json:target/MyReports/reports.json",
                "junit:target/MyReports/reports.xml"
        },
        publish = true
)
public class LoginTest {

}
