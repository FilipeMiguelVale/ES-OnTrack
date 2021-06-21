package com.example.cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import java.sql.*;


public class CucumberSteps extends CucumberIntegrationTest {

    @Given("Client needs to connect to MySQL Database")
    public void CLIENT_NEEDS_TO_CONNECT_TO_MYSQL_DATABASE() {


        String sqlSelectAllPersons = "SELECT * FROM users";
        String connectionUrl = "jdbc:mysql://192.168.160.18:3306/es23";

        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "mysql"); 
                PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons); 
                ResultSet rs = ps.executeQuery()) {
                    
                while (rs.next()) {
                    int id = rs.getInt("Id");
                   
                    System.out.print(id);
                    // do something with the extracted data...
                }
        } catch (SQLException e) {
            System.out.print(e);
            fail();
        }

        System.out.println("User attempting to connect to MySQL DATABASE");

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

    @Given("Admin is connected to MySQL Database")
    public void ASSERT_CLIENT_CONNECTED() {
        System.out.println("CLIENT ALREADY CONNECTED TO MYSQL");
    }

    @When("Admin queries database for user with id={string}")
    public void MYSQL_DATABASE_QUERY(String user_id)
    {
        System.out.println("QUERY MADE");
    }

    @When("Admin queries database for user with id={string} and password={string}")
    public void CHECK_FOR_USERS_MYSQL(String user, String password)
    {   
        System.out.println("USER_ID: " + user);
        System.out.println("USER_PASSWORD " + password);
        System.out.println("Unimplemented Test");
    }


    @Then("Admin receives a non empty list of users")
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
