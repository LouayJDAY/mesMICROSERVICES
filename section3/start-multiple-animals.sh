#!/bin/bash

echo "=== Création d'instances multiples d'animal-microservice ==="
echo ""

BASE_DIR="/home/louay/Desktop/louay/les microservices/section3"
cd "$BASE_DIR"

# Vérifier que le JAR existe
if [ ! -f "animal-microservice/target/animal-microservice-0.0.1-SNAPSHOT.jar" ]; then
    echo "Erreur: JAR du microservice animal non trouvé. Lancez d'abord mvn package"
    exit 1
fi

# Fonction pour démarrer une instance
start_instance() {
    local port=$1
    local instance_name="animal-instance-$port"

    echo "Démarrage de l'instance $instance_name sur le port $port..."

    cd animal-microservice
    java -jar target/animal-microservice-0.0.1-SNAPSHOT.jar --server.port=$port > "../$instance_name.log" 2>&1 &
    local pid=$!
    cd ..

    echo "Instance $instance_name démarrée (PID: $pid, Port: $port)"
    echo "Logs: $instance_name.log"
    echo ""

    # Stocker le PID pour arrêt ultérieur
    echo $pid >> pids.txt
}

# Nettoyer les anciens PIDs
rm -f pids.txt

echo "Démarrage des instances multiples..."

# Instance principale (port 8082)
start_instance 8082

# Instances supplémentaires
start_instance 8083
start_instance 8084
start_instance 8085

echo ""
echo "=== Instances démarrées ==="
echo "Vérifiez l'enregistrement sur: http://localhost:8761"
echo ""
echo "Test du load balancing:"
echo "curl http://localhost:8082/api/animals/1"
echo "curl http://localhost:8083/api/animals/1"
echo "curl http://localhost:8084/api/animals/1"
echo ""
echo "Pour arrêter toutes les instances:"
echo "kill \$(cat pids.txt) && rm pids.txt"
echo ""
echo "Test du failover: Arrêtez une instance et relancez les requêtes"