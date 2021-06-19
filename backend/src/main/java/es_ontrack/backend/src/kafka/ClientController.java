/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es_ontrack.backend.src.kafka;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es_ontrack.backend.src.models.Bus;

@RestController
@RequestMapping("/test")
public class ClientController {
    @Autowired
    private KafkaCons consumer;

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
        return consumer.getBuses();
    }
}
