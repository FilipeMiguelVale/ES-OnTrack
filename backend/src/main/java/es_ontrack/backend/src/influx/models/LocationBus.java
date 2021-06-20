package es_ontrack.backend.src.influx.models;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "bus")
public class LocationBus {

    @JsonProperty("time")
    @Column(name = "time")
    public Instant time;
    @JsonProperty("latitude")
    @Column(name = "latitude")
    public double latitude;
    @JsonProperty("longitude")
    @Column(name = "longitude")
    public double longitude;
}
