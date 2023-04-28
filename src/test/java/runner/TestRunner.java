package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"C:\\Users\\91762\\IdeaProjects\\EcommorceFramework\\src\\test\\java\\Cucumber"},
        glue="C:\\Users\\91762\\IdeaProjects\\EcommorceFramework\\src\\test\\java\\stepDefenitions",
monochrome = true,plugin = {"html:target/cucumber/report.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
