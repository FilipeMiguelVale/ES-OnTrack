package es_ontrack.backend.src.influx.models;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;

import org.influxdb.annotation.Measurement;

@Measurement(name = "Bus")
public class BusData {

    @Column(name = "time")
    private Instant time;

    @Column(name = "id")
    private String node_id;

    @Column(name = "speed")
    private double speed;

    @Column(name = "timestamp")
    private Date ts;

    @Column(name = "longitude")
    private double lon;

    @Column(name = "latitude")
    private double lat;

}
