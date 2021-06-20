package com.example.cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class CucumberSteps extends CucumberIntegrationTest {

    /*-------------------------------------------------------------
    Scenario Outline: Park is or is not free
    -------------------------------------------------------------*/
    @Given("Que as versões do cucumber funcionam")
    public void caring() {
        System.out.println("O CUCUMBER QSF");
    }

    @When("Na realidade estão todas na merda")
    public void care()
    {
        System.out.println("O CUCUMBER QSF");
    }

    @Then("É preferivel mandar o cucumber com o caralho")
    public void dont()
    {
        System.out.println("O CUCUMBER QSF");
    }
}
