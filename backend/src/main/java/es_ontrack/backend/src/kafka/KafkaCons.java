package es_ontrack.backend.src.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import es_ontrack.backend.src.models.Bus;


@Service
public class KafkaCons {

    private static final Logger logger = LoggerFactory.getLogger(KafkaCons.class);
    
    private List<String> logs = new LinkedList<>();
    private HashMap<String, Bus> buses = new HashMap<>();
    
    public List<String> getLogs() {
        return logs;
    }
    
    public HashMap<String, Bus> getBuses() {
        return buses;
    }
    
    @KafkaListener(topics = "logs",groupId = "esp23")
    public void consumeLog(String message) throws IOException{
        logs.add(message);
    }

    @KafkaListener(topics = "autobus",groupId = "esp23")
    public void consumeData(String message) throws IOException{
    	
        logger.info(String.format("#### -> Consumed message -> %s", message));
        Gson g = new Gson();
        try {
	        Bus b = g.fromJson(message, Bus.class);
	        logger.info(String.format("build bus -> %s", b.getNode_id()));
	        buses.put(b.getNode_id(),b);
        }catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
