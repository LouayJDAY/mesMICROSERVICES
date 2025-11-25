#!/bin/bash

echo "Stopping all microservices..."

# Find and kill Java processes for our services
pkill -f "configserver-0.0.1-SNAPSHOT.jar"
pkill -f "eurekaserver-0.0.1-SNAPSHOT.jar"
pkill -f "animal-microservice-0.0.1-SNAPSHOT.jar"
pkill -f "groupe-microservice-0.0.1-SNAPSHOT.jar"
pkill -f "gatewayserver-0.0.1-SNAPSHOT.jar"

echo "All services stopped."