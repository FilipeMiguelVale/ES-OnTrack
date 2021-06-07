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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Date;
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
    this.prod = BusProducer.createProducer(serv);
    }
    
    private static Producer<String,Bus> createProducer(String BS)
    {
    Properties prop = new Properties();
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,BS);
    prop.put(ProducerConfig.CLIENT_ID_CONFIG, "Kafka Example Producer");
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,null);
    return new KafkaProducer<>(prop);
    }
    
    public class DataSource{
        private BufferedReader bf;
        
        public DataSource(String fname)
        {
            try (BufferedReader b = new BufferedReader(new FileReader(fname)))
            {
                b.readLine();
                this.bf = b;
            } 
            catch(IOException e){System.out.println("IOException reading DataSource");}
        }
        
        public Bus readData()
        {
            String l=null;
            try
            {
               l = bf.readLine();
            }
            catch(IOException e){System.out.println("IOException while reading file!");}
            if (l != null)
                    {
                    System.out.println("EOF Reached!");
//TODO                    
// Need to work on this to keep reading file even whhen EOF is reached
                    System.exit(1);
                            }
            else
            {   
                String[] bdata = l.split(",");
                Bus b = new Bus(bdata[0],Integer.parseInt(bdata[1]),
                        Double.parseDouble(bdata[2]),Double.parseDouble(bdata[3]),
                        Double.parseDouble(bdata[4]),Double.parseDouble(bdata[5]));
                return b;
            }
            return new Bus();
        }
        
    }
    
    
}
