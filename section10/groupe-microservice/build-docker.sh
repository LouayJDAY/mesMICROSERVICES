#!/usr/bin/env bash
set -euo pipefail

# Build the jar
mvn -DskipTests clean package

# Build the docker image. Change NADHEM to your docker hub namespace
IMAGE_NAME="louayyyy/groupe:v1"
docker build . -t "$IMAGE_NAME"
echo "Built $IMAGE_NAME"

# Run the image in detached mode, mapping port 8081
docker run -d -p 8081:8081 "$IMAGE_NAME"
echo "Running $IMAGE_NAME on port 8081"
