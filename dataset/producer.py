from confluent_kafka import Producer
import socket
import csv
from time import sleep

def acked(err, msg):
	if err is not None:
		print("Failed to deliver message: %s: %s" % (str(msg), str(err)))
	else:
		print("Message produced: %s" % (str(msg)))

conf = {'bootstrap.servers': "10.10.1.3:9092",
		'client.id': socket.gethostname()}

producer = Producer(conf)

with open('dataset.csv', mode='r') as csv_file:
	csv_reader = csv.DictReader(csv_file)
	line_count = 0
	lines = []
	for row in csv_reader:
		if line_count < 100:
			producer.produce("autobus", key="key", callback=acked,
							 value=str({"node_id":row["node_id"],"location_id":row["location_id"],"head": row["head"],"lon": row["lon"],
										"lat": row["lat"], "speed": 0}))
			producer.flush()
			sleep(2)
		else:
			break
		line_count+=1
