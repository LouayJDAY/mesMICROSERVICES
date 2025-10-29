#!/bin/bash

echo "=== Démarrage du système Eureka ==="
echo ""

# Fonction pour attendre qu'un service soit prêt
wait_for_service() {
    local url=$1
    local service_name=$2
    echo "Attente que $service_name soit prêt sur $url..."
    while ! curl -s $url > /dev/null; do
        sleep 2
    done
    echo "$service_name est prêt !"
}

echo "1. Construction des projets..."
cd /home/louay/Desktop/louay/les\ microservices/section3

# Construction du serveur Eureka
echo "Construction du serveur Eureka..."
cd eurekaserver
mvn clean package -DskipTests -q
cd ..

# Construction des microservices
echo "Construction du microservice animal..."
cd animal-microservice
mvn clean package -DskipTests -q
cd ..

echo "Construction du microservice groupe..."
cd groupe-microservice
mvn clean package -DskipTests -q
cd ..

echo ""
echo "2. Démarrage des services..."

# Démarrage du serveur Eureka en arrière-plan
echo "Démarrage du serveur Eureka (port 8761)..."
cd eurekaserver
java -jar target/eurekaserver-0.0.1-SNAPSHOT.jar > ../eureka.log 2>&1 &
EUREKA_PID=$!
cd ..
echo "Serveur Eureka démarré (PID: $EUREKA_PID)"

# Attendre que Eureka soit prêt
wait_for_service "http://localhost:8761" "Serveur Eureka"

# Démarrage du microservice groupe
echo "Démarrage du microservice groupe (port 8081)..."
cd groupe-microservice
java -jar target/groupe-microservice-0.0.1-SNAPSHOT.jar > ../groupe.log 2>&1 &
GROUPE_PID=$!
cd ..
echo "Microservice groupe démarré (PID: $GROUPE_PID)"

# Démarrage du microservice animal
echo "Démarrage du microservice animal (port 8082)..."
cd animal-microservice
java -jar target/animal-microservice-0.0.1-SNAPSHOT.jar > ../animal.log 2>&1 &
ANIMAL_PID=$!
cd ..
echo "Microservice animal démarré (PID: $ANIMAL_PID)"

echo ""
echo "=== Services démarrés ==="
echo "Serveur Eureka: http://localhost:8761"
echo "Microservice Animal: http://localhost:8082"
echo "Microservice Groupe: http://localhost:8081"
echo ""
echo "PIDs des processus:"
echo "Eureka: $EUREKA_PID"
echo "Groupe: $GROUPE_PID"
echo "Animal: $ANIMAL_PID"
echo ""
echo "Pour arrêter tous les services: kill $EUREKA_PID $GROUPE_PID $ANIMAL_PID"
echo ""
echo "=== Tests disponibles ==="
echo "1. Vérifier l'enregistrement Eureka: http://localhost:8761"
echo "2. Tester animal avec groupe: curl http://localhost:8082/api/animals/1"
echo "3. Console H2 Animal: http://localhost:8082/h2-console"
echo "4. Console H2 Groupe: http://localhost:8081/h2-console"