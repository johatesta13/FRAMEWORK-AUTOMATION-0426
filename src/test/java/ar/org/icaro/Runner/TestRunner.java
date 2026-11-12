package ar.org.icaro.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "ar.org.icaro.Steps",
                "ar.org.icaro.Runner"
        },

        plugin = {
                "pretty",
                "html: target/cucumber-reports.html"
        },

        monochrome = false,
        tags = "@compra-completa"
)


public class TestRunner extends AbstractTestNGCucumberTests {


        /**
         * Este metodo permite que cada fila de Examples
         * se ejecute como un test separado en TestNG.
         */
        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
