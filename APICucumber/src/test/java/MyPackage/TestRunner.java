package MyPackage;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@Test
@CucumberOptions(features = "src/test/resources/api_test.feature", glue = "MyPackage")
public class TestRunner extends AbstractTestNGCucumberTests {
}
