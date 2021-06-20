package es_ontrack.backend.src.influx;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import es_ontrack.backend.src.influx.models.BusData;
import es_ontrack.backend.src.influx.models.LocationBus;
import es_ontrack.backend.src.influx.models.Speeds;
import es_ontrack.backend.src.models.Bus;
import lombok.Data;

@Data
public class InfluxDbUtils {

    private InfluxDB influxDB;
    private String url;
    private String userName;
    private String password;
    private String database;

    public InfluxDbUtils(String userName, String password, String url, String database) {
        this.userName = userName;
        this.password = password;
        this.url = url;
        this.database = database;
        this.influxDB = influxDbBuild();
    }

    private InfluxDB influxDbBuild() {
        if (influxDB == null) {
            this.influxDB = InfluxDBFactory.connect(this.url, this.userName, this.password);
            this.influxDB.setDatabase(this.database);
        }

        return this.influxDB;
    }

    public InfluxDB getInfluxDB() {
        if (this.influxDB == null)
            influxDbBuild();
        return this.influxDB;
    }

    public List<BusData> getLocationMeanSpeedByBus(String time) {

        String query = "select id, speed, latitude, longitude from bus";

        if (!time.equals("ALL"))
            query += " where time > now() - " + time;

        QueryResult result = performQuery(query);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

        return resultMapper.toPOJO(result, BusData.class);
    }

    public List<Speeds> getSpeedsByHour() {
        String query = "select time,mean(speed) as speed from bus group by time(1h)";

        QueryResult result = performQuery(query);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

        return resultMapper.toPOJO(result, Speeds.class);

    }

    public List<Speeds> getSpeedsByDay() {
        String query = "select mean(speed) as speed from bus group by time(1h)";

        QueryResult result = performQuery(query);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

        return resultMapper.toPOJO(result, Speeds.class, TimeUnit.MILLISECONDS);

    }

    public List<Bus_Id> getBusIds() {
        String query = "select time,distinct(id) as id from bus";

        QueryResult result = performQuery(query);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

        return resultMapper.toPOJO(result, Bus_Id.class);

    }

    public List<LocationBus> getLocationBus(String id, String time) {
        String query = "select latitude,longitude from bus where id='" + id + "'";

        if (!time.equals("ALL"))
            query += " and time > now() - " + time;

        QueryResult result = performQuery(query);

        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

        return resultMapper.toPOJO(result, LocationBus.class);
    }

    private QueryResult performQuery(String query) {
        Query queryObj = new Query(query, this.database);

        QueryResult result = this.influxDB.query(queryObj);

        return result;
    }

    public void write(Bus b) {

        Point point = Point.measurementByPOJO(Bus.class).addFieldsFromPOJO(b).build();

        this.influxDB.write(point);
    }

    @Measurement(name = "bus")
    public static class Bus_Id {
        @Column(name = "time")
        public Instant time;
        @Column(name = "id")
        public String id;
    }

}
