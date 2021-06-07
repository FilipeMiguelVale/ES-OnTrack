/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EsOntrack.busproducer;
import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author adven
 */
public class BusSerializer implements Serializer<Bus> {

    @Override
    public byte[] serialize(String string, Bus t) {
        
        byte[] ret = null;
        
        ObjectMapper o = new ObjectMapper();
        
        try
        {
            ret = o.writeValueAsString(t).getBytes();    
        }
        catch(Exception e){e.printStackTrace();}
        
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
