Feature: KafkaConnection

    Scenario: Client wants to send data to topic
    Given Client has message to send
    When Client Initializes Kafka Producer and Consumer
    And Kafka Producer sends message to topic
    Then Kafka Consumer receives message from topic