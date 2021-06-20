package com.example.cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class CucumberSteps extends CucumberIntegrationTest {

    @Given("Client needs to connect to MySQL Database")
    public void CLIENT_NEEDS_TO_CONNECT_TO_MYSQL_DATABASE() {
        System.out.println("User attempring to connect to MySQL DATABASE");
    }

    @When("Client initializes MySQL Database handler")
    public void INITIALIZE_MYSQL_DATABASE_HANDLER()
    {
        System.out.println("Database Handler Initialized");
    }

    @Then("MySQL Database Connection is established")
    public void SQL_CONNECTION_ESTABLISHED()
    {
        System.out.println("CONNECTION ESTABLISHED");
    }

    @Given("Client is connected to MySQLDatabase")
    public void ASSERT_CLIENT_CONNECTED() {
        System.out.println("CLIENT ALREADY CONNECTED TO MYSQL");
    }

    @When("Client queries database for user with id=test")
    public void MYSQL_DATABASE_QUERY()
    {
        System.out.println("QUERY MADE");
    }

    @Then("Client receives a non empty list of users")
    public void RECEIVE_DATA_FROM_MYSQL()
    {
        System.out.println("NON EMPTY LIST OF USERS");
    }

    @Given("Client needs to connect to InfluxDB")
    public void CLIENT_NEEDS_TO_CONNECT_TO_INFLUXDB() {
        System.out.println("INFLUX CONNECTED");
    }

    @When("Client initializes InfluxDB handler")
    public void INIT_INFLUXDB_HANDLER()
    {
        System.out.println("INFLUXDB HANDLER INITIALIZED");
    }

    @Then("InfluxDB Connection is established")
    public void ASSERT_INFLUXDB_CONNECTION_ESTABLISHED()
    {
        System.out.println("CONNECTION WITH INFLUXDB ESTABLISHED");
    }

    
    @Given("Client is connected to InfluxDB")
    public void a(){System.out.println("Unimplemented step");}
    @When("Client queries InfluxDB for last hour data")
    public void b(){System.out.println("Unimplemented step");}
    @Then("Client receives all data from what happened until 1 hour prior")
    public void c(){System.out.println("Unimplemented step");}
    @Given("Client has message to send")
    public void d(){System.out.println("Unimplemented step");}
    @When("Client Initializes Kafka Producer and Consumer")
    public void e(){System.out.println("Unimplemented step");}
    @And("Kafka Producer sends message to topic")
    public void f(){System.out.println("Unimplemented step");}
    @Then("Kafka Consumer receives message from topic")
    public void g(){System.out.println("Unimplemented step");}
    
    
    /*
    Scenario: Client wants to see what happened in the last hour
    Given Client is connected to InfluxDB
    When Client queries InfluxDB for last hour data
    Then Client receives all data from what happened until 1 hour prior

    Scenario: Client wants to send data to topic
    Given Client has message to send
    When Client Initializes Kafka Producer and Consumer
    And Kafka Producer sends message to topic
    Then Kafka Consumer receives message from topic
    */


}
