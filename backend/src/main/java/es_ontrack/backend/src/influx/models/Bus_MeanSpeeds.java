package es_ontrack.backend.src.influx.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bus_MeanSpeeds {

    @JsonProperty("id")
    public String id;

    @JsonProperty("speed")
    public double speed;

    public double getSpeed() {
        return speed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

}