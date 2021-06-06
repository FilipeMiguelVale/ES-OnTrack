package es_ontrack.backend.src.models;

import java.util.Date;

public class Bus {
	
	private String node_id;
	private int location_id;
	private double head;
	private double lon;
	private double lat;
	private double speed;
	private Date ts;
	
	public Bus(){}
	
	public Bus(String node_id, int location_id, double head, double lon, double lat, double speed) {
		super();
		this.node_id = node_id;
		this.location_id = location_id;
		this.head = head;
		this.lon = lon;
		this.lat = lat;
		this.speed = speed;
	}



	public String getNode_id() {
		return node_id;
	}

	public void setNode_id(String node_id) {
		this.node_id = node_id;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public double getHead() {
		return head;
	}

	public void setHead(double head) {
		this.head = head;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}
	
	
}
