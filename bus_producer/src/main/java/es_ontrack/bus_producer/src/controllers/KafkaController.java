package es_ontrack.bus_producer.src.controllers;

import es_ontrack.bus_producer.src.kafka.KafkaProd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.opencsv.CSVReader;

@RestController
@RequestMapping(value = "/kafka")
@EnableScheduling
@Log4j2
public class KafkaController {

    private final KafkaProd producer;
    private int i = 0;
    private List<List<String>> records = new ArrayList<List<String>>();
    // private static final SimpleDateFormat dateFormat = new
    // SimpleDateFormat("HH:mm:ss");
    // private static final Logger log =
    // LoggerFactory.getLogger(CoinController.class);
    
    private static final Logger LOG = Logger.getLogger(KafkaController.class.getName());

    @Autowired
    public KafkaController(KafkaProd producer) throws FileNotFoundException, IOException {
        this.producer = producer;
        try (CSVReader csvReader = new CSVReader(
                new FileReader("src/main/java/es_ontrack/bus_producer/src/dataset.csv"));) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        }
    }

    @Scheduled(fixedRate = 100)
    public void messageToTopic() {
        i += 1;
        if (i > records.size()) {
            i = 1;
        }
        for (int j = 0; j < records.get(i).size(); j++)
            if (records.get(i).get(j).isEmpty())
                records.get(i).set(j, "null");

        String message = "{'node_id':'" + records.get(i).get(1) + "','location_id':'" + records.get(i).get(2)
                + "','head': '" + records.get(i).get(3) + "','lon':'" + records.get(i).get(4) + "','lat':'"
                + records.get(i).get(5) + "', 'speed': " + records.get(i).get(6) + "}";

        this.producer.send("esp23_buses", message);
        LOG.log(Level.INFO, message);
        // log.info("Publishing data to topic data: "+message);
        System.out.println(message);
        // return "Published message: "+message ;
    }
}