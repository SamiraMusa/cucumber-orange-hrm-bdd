package TestRunner;

import io.cucumber.testng.*;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"StepDefinitions"},
        plugin = { "pretty" },
        monochrome = true
)
public class cucumberTestRunner extends AbstractTestNGCucumberTests {
}

