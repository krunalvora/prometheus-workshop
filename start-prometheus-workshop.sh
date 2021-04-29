#!/bin/bash

echo -e "\nRunning prometheus on port 9090..."
docker-compose --log-level ERROR down && docker-compose --log-level ERROR up -d

echo -e "☑ Prometheus UI can be found at http://localhost:9090."

echo -e "\nBuilding the java app to expose prometheus metrics..."
./gradlew -q clean build

echo -e "\nRunning the java app to expose prometheus metrics...\n"
java -jar build/libs/prometheus-workshop-all.jar

docker-compose down