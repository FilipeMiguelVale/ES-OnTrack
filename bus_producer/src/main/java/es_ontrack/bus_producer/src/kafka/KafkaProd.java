/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es_ontrack.bus_producer.src.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProd {
    
    //private static final Logger logger = LoggerFactory.getLogger(KafkaProd.class);
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String topic,String message){
        //logger.info(String.format("Produced data: %s", message));    
        this.kafkaTemplate.send(topic,message);
    }
}
