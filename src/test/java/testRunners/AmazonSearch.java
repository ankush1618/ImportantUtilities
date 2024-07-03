package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//monochrome =true and plugin=pretty does not work if provided together
//Cucumber for Java plugin+ Cucumber for IntelliJ
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/appFeatures/AmazonSearch.feature"},
        glue={"stepDefinations","myHooks"},
          plugin = {"pretty",
                  "json:target/MyReports/reports.json",
                  "junit:target/MyReports/reports.xml"
          },
        publish = true
)
public class AmazonSearch {


}
