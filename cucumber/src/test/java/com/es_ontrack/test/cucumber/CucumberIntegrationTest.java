package com.es_ontrack.test.cucumber;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        plugin = {"pretty", "html:target/cucumber/bagbasics"},
        extraGlue = "io.tpd.springbootcucumber.bagcommons")
public class CucumberIntegrationTest {

}
