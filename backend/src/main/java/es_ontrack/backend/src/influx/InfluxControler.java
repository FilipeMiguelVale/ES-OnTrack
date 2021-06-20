package es_ontrack.backend.src.influx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es_ontrack.backend.src.influx.models.BusData;
import es_ontrack.backend.src.influx.models.LocationBus;
import es_ontrack.backend.src.influx.models.Speeds;
import es_ontrack.backend.src.models.Bus;

@RestController
@RequestMapping(value = "/statistics", headers = "Accept=application/json")
public class InfluxControler {

    @Autowired
    private InfluxDbUtils influxdbUtils;
    
    private HashMap<String, BusData> buses = new HashMap<>();

    @GetMapping("/mean_speed_location_by_bus")
    public ResponseEntity<Collection<BusData>> getSpeedByBus(@RequestParam(name = "time") String time) {
    	buses.clear();
    	
    	for (BusData bus : influxdbUtils.getLocationMeanSpeedByBus(time)){
    		buses.put(bus.node_id, bus);
    	}
    	return ResponseEntity.ok().body(buses.values());
        	
    	//return ResponseEntity.ok().body(influxdbUtils.getLocationMeanSpeedByBus(time));
    }

    @GetMapping("/mean_speeds_by_hour")
    public ResponseEntity<List<Speeds>> getMeanSpeeds() {

        return ResponseEntity.ok().body(influxdbUtils.getSpeedsByHour());
    }

    @GetMapping("/mean_speeds_by_day")
    public ResponseEntity<List<Speeds>> getMeanSpeedsDays() {

        return ResponseEntity.ok().body(influxdbUtils.getSpeedsByDay());
    }

    @GetMapping(value = "/location_bus")
    public ResponseEntity<List<BusData>> getLocationBus(@RequestParam(name = "id") String id,
            @RequestParam(name = "time") String time) {
        return ResponseEntity.ok().body(influxdbUtils.getLocationBus(id, time));
    }
    
    @GetMapping(value = "/bus")
    public ResponseEntity<BusData> getBus(@RequestParam(name = "id") String id) {
        return ResponseEntity.ok().body(influxdbUtils.getBus(id));
    }

}
