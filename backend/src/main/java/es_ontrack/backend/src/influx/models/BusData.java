package es_ontrack.backend.src.influx.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "bus")
public class BusData {

    @JsonProperty("time")
    @Column(name = "time")
    public Instant time;

    @JsonProperty("id")
    @Column(name = "id")
    public String node_id;

    @JsonProperty("speed")
    @Column(name = "speed")
    public double speed;

    @JsonProperty("longitude")
    @Column(name = "longitude")
    public double lon;

    @JsonProperty("latitude")
    @Column(name = "latitude")
    public double lat;
    
    @JsonProperty("location")
    @Column(name = "location")
    public String location;


}
