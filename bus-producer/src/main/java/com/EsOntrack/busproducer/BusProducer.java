/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EsOntrack.busproducer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import com.EsOntrack.busproducer.Bus;
import java.util.Properties;
/**
 *
 * @author adven
 */
public class BusProducer extends Thread {
    
    public final String BOOTSTRAP_SERVERS;
    public final String topic;
    private Producer<String,Bus> prod;
    
    public BusProducer(String serv,String top)
    {
    this.BOOTSTRAP_SERVERS=serv;
    this.topic=top;
    }
    
    private Producer<String,Bus> createProducer()
    {
    Properties prop = new Properties();
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BOOTSTRAP_SERVERS);
    prop.put(ProducerConfig.CLIENT_ID_CONFIG, "Kafka Example Producer");
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,)
    return new KafkaProducer<>(prop);
    }
    
}
