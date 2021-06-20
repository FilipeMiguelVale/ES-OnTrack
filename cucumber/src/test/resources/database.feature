Feature: Database Tests

    Scenario: Client wants to connect to MySQLDatabase
        Given Client needs to connect to MySQL Database
        When Client initializes MySQL Database handler
        Then MySQL Database Connection is established
    
    Scenario: Client wants to search for data in MySQLDatabase
        Given Client is connected to MySQLDatabase
        When Client queries database for user with id=test
        Then Client receives a non empty list of users

    Scenario: Client wants to connect to InfluxDB
        Given Client needs to connect to InfluxDB
        When Client initializes InfluxDB handler
        Then InfluxDB Connection is established
    
    Scenario: Client wants to see what happened in the last hour
        Given Client is connected to InfluxDB
        When Client queries InfluxDB for last hour data
        Then Client receives all data from what happened until 1 hour prior