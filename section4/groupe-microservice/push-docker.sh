#!/usr/bin/env bash
set -euo pipefail

# Variables
LOCAL_IMAGE="louayyyy/groupe:v1"
REMOTE_IMAGE="louayyyy/groupe:v1"

echo "Tag de l'image locale pour Docker Hub..."
docker tag "$LOCAL_IMAGE" "$REMOTE_IMAGE"

echo "Push de l'image sur Docker Hub..."
docker push "$REMOTE_IMAGE"

echo "Image poussée sur Docker Hub : $REMOTE_IMAGE"