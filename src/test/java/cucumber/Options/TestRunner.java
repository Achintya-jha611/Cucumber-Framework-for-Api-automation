package cucumber.Options;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)//The runner class allows you to use Cucumber with a test framework like JUnit or TestNG, which provides structure and integration with build tools (like Maven or Gradle). For example:
@CucumberOptions(features = "src/test/java/features",glue = {"stepDefinitions"})//glue specifies package where cucumber should look for stepdef
public class TestRunner {
}
