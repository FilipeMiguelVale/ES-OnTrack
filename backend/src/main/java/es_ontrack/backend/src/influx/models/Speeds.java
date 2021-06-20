package es_ontrack.backend.src.influx.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "bus")
public class Speeds {
    @JsonProperty("time")
    @Column(name = "time")
    public Instant time;
    @JsonProperty("speed")
    @Column(name = "speed")
    public double speed;
}