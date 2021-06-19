package es_ontrack.backend.src.influx;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es_ontrack.backend.src.influx.models.Bus_MeanSpeeds;
import es_ontrack.backend.src.influx.models.Speeds;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/statistics", headers = "Accept=application/json")
public class InfluxControler {

    @Autowired
    private InfluxDbUtils influxdbUtils;

    @GetMapping("/mean_speed_by_bus")
    public ResponseEntity<List<Bus_MeanSpeeds>> getSpeedByBus() {

        return ResponseEntity.ok().body(influxdbUtils.getMeanSpeedByBus());
    }

    @GetMapping("/mean_speeds_by_hour")
    public ResponseEntity<List<Speeds>> getMeanSpeeds() {

        return ResponseEntity.ok().body(influxdbUtils.getSpeedsByHour());
    }

    @GetMapping("/mean_speeds_by_day")
    public ResponseEntity<List<Speeds>> getMeanSpeedsDays() {

        return ResponseEntity.ok().body(influxdbUtils.getSpeedsByDay());
    }

}
