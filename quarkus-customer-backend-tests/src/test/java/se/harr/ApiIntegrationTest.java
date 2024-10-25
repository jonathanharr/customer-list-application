package se.harr;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.quarkiverse.cucumber.CucumberQuarkusTest;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "se.harr.glues"
)
@QuarkusTest
public class ApiIntegrationTest extends CucumberQuarkusTest {
}
