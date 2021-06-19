/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es_ontrack.backend.src.kafka;

import java.util.HashMap;
import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es_ontrack.backend.src.api.RestControler;
import es_ontrack.backend.src.models.Bus;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@Log4j2
@RequestMapping("/test")
public class ClientController {
    @Autowired
    private KafkaCons consumer;
    
    private static final Logger LOG = Logger.getLogger(ClientController.class.getName());
    
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping("/logs")
    public List getLogs(){
        return consumer.getLogs();
    }

    @RequestMapping("/data")
    public HashMap<String, Bus> getData(){
    	//log.info("Topic: {}", "plane");
    	LOG.log(Level.INFO, "teste");
    	LOG.log(Level.INFO, consumer.getBuses().toString());
        return consumer.getBuses();
    }
}
