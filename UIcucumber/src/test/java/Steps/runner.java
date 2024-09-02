package Steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/my_feature.feature",    glue = "Steps"
)
public class runner extends AbstractTestNGCucumberTests {
}
