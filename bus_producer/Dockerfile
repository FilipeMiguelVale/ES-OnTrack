FROM adoptopenjdk/openjdk11:latest
EXPOSE 8080
ADD target/Bus_Producer-0.0.1-SNAPSHOT.jar Bus_Producer-0.0.1-SNAPSHOT.jar
ADD src/main/java/es_ontrack/bus_producer/src/dataset.csv src/main/java/es_ontrack/bus_producer/src/dataset.csv

ENTRYPOINT ["java","-jar","/Bus_Producer-0.0.1-SNAPSHOT.jar"]
