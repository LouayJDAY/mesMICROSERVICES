#!/bin/bash

echo "Starting Microservices System..."

# Start Config Server
echo "Starting Config Server..."
cd configserver
java -jar target/configserver-0.0.1-SNAPSHOT.jar &
CONFIG_PID=$!
cd ..

# Wait a bit for config server to start
sleep 10

# Start Eureka Server
echo "Starting Eureka Server..."
cd eurekaserver
java -jar target/eurekaserver-0.0.1-SNAPSHOT.jar &
EUREKA_PID=$!
cd ..

# Wait for Eureka
sleep 10

# Start Animal Microservice
echo "Starting Animal Microservice..."
cd animal-microservice
java -jar target/animal-microservice-0.0.1-SNAPSHOT.jar &
ANIMAL_PID=$!
cd ..

# Start Groupe Microservice
echo "Starting Groupe Microservice..."
cd groupe-microservice
java -jar target/groupe-microservice-0.0.1-SNAPSHOT.jar &
GROUPE_PID=$!
cd ..

# Wait for microservices
sleep 10

# Start Gateway Server
echo "Starting Gateway Server..."
cd gatewayserver
java -jar target/gatewayserver-0.0.1-SNAPSHOT.jar &
GATEWAY_PID=$!
cd ..

echo "All services started!"
echo "Config Server PID: $CONFIG_PID"
echo "Eureka Server PID: $EUREKA_PID"
echo "Animal Microservice PID: $ANIMAL_PID"
echo "Groupe Microservice PID: $GROUPE_PID"
echo "Gateway Server PID: $GATEWAY_PID"
echo ""
echo "To stop all services, run: kill $CONFIG_PID $EUREKA_PID $ANIMAL_PID $GROUPE_PID $GATEWAY_PID"

# Keep script running to show logs
echo "Press Ctrl+C to stop all services..."
trap "echo 'Stopping all services...'; kill $CONFIG_PID $EUREKA_PID $ANIMAL_PID $GROUPE_PID $GATEWAY_PID 2>/dev/null; exit" INT
wait