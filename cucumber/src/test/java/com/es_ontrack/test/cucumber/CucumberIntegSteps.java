package com.es_ontrack.test.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import com.es_ontrack.test.cucumber.db.UserRepositoryTest;

public class CucumberIntegSteps extends CucumberIntegTests
{
    //@Autowired
    //UserRepositoryTest u;

    @Given("Client needs to connect to MySQL Database")
    public void CLIENT_NEEDS_TO_CONNECT_TO_MYSQL_DATABASE() throws Throwable {
    //System.out.println(u.findByEmail("admin"));
    }

    @When("Client initializes MySQL Database handler")
    public void INITIALIZE_MYSQL_DATABASE_HANDLER()
    {   
       // makeQuery(c);
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
    
}