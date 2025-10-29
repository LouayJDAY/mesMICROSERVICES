# Eureka Server pour Microservices Animal et Groupe

Ce projet configure un serveur Eureka pour la découverte de services entre les microservices `animal-microservice` et `groupe-microservice`.

## Architecture

- **eurekaserver** (Port 8761) : Serveur de découverte Eureka
- **animal-microservice** (Port 8082) : Service client Eureka
- **groupe-microservice** (Port 8081) : Service client Eureka

## Démarrage rapide

### Démarrage automatique de tout le système
```bash
./start-system.sh
```

Ce script va :
1. Construire tous les projets
2. Démarrer le serveur Eureka
3. Démarrer les microservices animal et groupe
4. Afficher les URLs et PIDs pour monitoring

### Démarrage manuel

#### 1. Démarrer le serveur Eureka
```bash
cd eurekaserver
mvn clean package -DskipTests
java -jar target/eurekaserver-0.0.1-SNAPSHOT.jar
```

Le serveur Eureka sera accessible sur : http://localhost:8761/

#### 2. Démarrer les microservices clients
```bash
# Terminal 1 - groupe-microservice
cd groupe-microservice
mvn clean package -DskipTests
java -jar target/groupe-microservice-0.0.1-SNAPSHOT.jar

# Terminal 2 - animal-microservice
cd animal-microservice
mvn clean package -DskipTests
java -jar target/animal-microservice-0.0.1-SNAPSHOT.jar
```

## Créer plusieurs instances d'animal-microservice

Pour tester le load balancing et le failover :

```bash
./start-multiple-animals.sh
```

Ce script crée 4 instances sur les ports 8082, 8083, 8084, 8085.

## Tester le failover

1. Vérifiez que toutes les instances sont enregistrées sur http://localhost:8761/
2. Testez l'API : `GET http://localhost:8082/api/animals/1/with-groupe`
3. Arrêtez une instance (par exemple celle sur le port 8082)
4. Retestez l'API - elle devrait continuer à fonctionner grâce au load balancer

## APIs disponibles

### Animal Microservice
- `GET /api/animals/id/{id}` - Récupère un animal par ID
- `GET /api/animals/nom/{nom}` - Récupère un animal par nom
- `GET /api/animals/{id}` - Récupère un animal avec son groupe (utilise Eureka pour découvrir GROUPE)

### Groupe Microservice
- `GET /api/groupes/{nom}` - Récupère un groupe par nom

## Données de test

### Animaux
- Lion (190.5kg, Félin)
- Elephant (5000.0kg, Pachyderme)
- Girafe (1200.0kg, Girafidé)

### Groupes
- Félin
- Pachyderme
- Girafidé

## Monitoring

- **Eureka Dashboard** : http://localhost:8761/
- **Console H2 Animal** : http://localhost:8082/h2-console (JDBC: jdbc:h2:mem:animaldb)
- **Console H2 Groupe** : http://localhost:8081/h2-console (JDBC: jdbc:h2:mem:grpdb)

## Logs

Les logs sont disponibles dans :
- `eureka.log`
- `animal.log`
- `groupe.log`
- `animal-instance-{port}.log` (pour les instances multiples)