# Guide du Projet Microservices Java

## Vue d'ensemble

Ce projet est une architecture de microservices développée en Java avec Spring Boot. Il comprend plusieurs services indépendants qui communiquent entre eux via un serveur Eureka pour la découverte de services, un serveur de passerelle (Gateway) pour le routage des requêtes, et un serveur de configuration centralisé pour la gestion des configurations.

## Architecture

L'architecture suit les principes des microservices avec les composants suivants :

- **Eureka Server** : Serveur de découverte de services
- **Config Server** : Serveur de configuration centralisée
- **Gateway Server** : Passerelle API pour le routage et l'équilibrage de charge
- **Animal Microservice** : Service métier pour la gestion des animaux
- **Groupe Microservice** : Service métier pour la gestion des groupes
- **Config Repo** : Dépôt de configurations externes

### Diagramme d'architecture

```
[Client] --> [Gateway Server] --> [Eureka Server]
                              --> [Animal Microservice]
                              --> [Groupe Microservice]
                              --> [Config Server] --> [Config Repo]
```

## Services

### 1. Eureka Server

- **Port** : 8761
- **Description** : Serveur de découverte de services pour l'enregistrement et la localisation des microservices
- **Dépendances** : Spring Cloud Netflix Eureka Server

### 2. Config Server

- **Port** : 8888
- **Description** : Serveur centralisé pour la gestion des configurations des microservices
- **Dépendances** : Spring Cloud Config Server
- **Configuration** : Utilise le dépôt `config-repo` pour les fichiers de configuration

### 3. Gateway Server

- **Port** : 8765
- **Description** : Passerelle API qui route les requêtes vers les services appropriés
- **Dépendances** : Spring Cloud Gateway

### 4. Animal Microservice

- **Port** : 8081 (configurable via config server)
- **Description** : Service pour la gestion des entités "animal"
- **Dépendances** : Spring Boot Web, Eureka Client, Config Client

### 5. Groupe Microservice

- **Port** : 8082 (configurable via config server)
- **Description** : Service pour la gestion des entités "groupe"
- **Dépendances** : Spring Boot Web, Eureka Client, Config Client

## Prérequis

- **Java** : JDK 11 ou supérieur
- **Maven** : 3.6 ou supérieur
- **Git** : Pour cloner le projet

## Installation et Configuration

1. **Cloner le projet** :

   ```bash
   git clone <url-du-repo>
   cd section5
   ```

2. **Configurer les variables d'environnement** (si nécessaire) :
   - Les configurations sont gérées via le Config Server
   - Les fichiers de configuration se trouvent dans `config-repo/`

## Démarrage des Services

L'ordre de démarrage est important pour éviter les erreurs de dépendances :

1. **Démarrer le Config Server** :

   ```bash
   cd configserver
   mvn spring-boot:run
   ```

2. **Démarrer le Eureka Server** :

   ```bash
   cd eurekaserver
   mvn spring-boot:run
   ```

3. **Démarrer la Gateway** :

   ```bash
   cd gatewayserver
   mvn spring-boot:run
   ```

4. **Démarrer les Microservices** :

   ```bash
   # Terminal 1 - Animal Microservice
   cd animal-microservice
   mvn spring-boot:run

   # Terminal 2 - Groupe Microservice
   cd groupe-microservice
   mvn spring-boot:run
   ```

## Vérification du Fonctionnement

- **Eureka Dashboard** : `http://localhost:8761`
- **Config Server** : `http://localhost:8888`
- **Gateway** : `http://localhost:8765`

## Configuration

### Profils Spring

Chaque microservice supporte différents profils :

- `default` : Configuration locale
- `qa` : Environnement de qualité
- `prod` : Environnement de production

Pour activer un profil :

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=qa
```

### Fichiers de Configuration

- `config-repo/animal.yml` : Configuration de base pour animal-microservice
- `config-repo/animal-qa.yml` : Configuration QA pour animal-microservice
- `config-repo/animal-prod.yml` : Configuration production pour animal-microservice
- `config-repo/groupe.yml` : Configuration de base pour groupe-microservice
- `config-repo/groupe-qa.yml` : Configuration QA pour groupe-microservice
- `config-repo/groupe-prod.yml` : Configuration production pour groupe-microservice

## Construction et Déploiement

### Construction

```bash
# Construire tous les services
mvn clean package -DskipTests
```

### Tests

```bash
# Exécuter les tests pour tous les services
mvn test
```

### Déploiement

Les services peuvent être déployés en tant que JARs exécutables :

```bash
java -jar target/service-name-0.0.1-SNAPSHOT.jar
```

## Logs

Chaque service génère des logs :

- `animal-microservice/animal.log`
- `gatewayserver/gateway.log`
- `groupe-microservice/groupe.log`

## Dépannage

### Problèmes Courants

1. **Erreur de connexion à Eureka** :
   - Vérifier que le Eureka Server est démarré
   - Vérifier la configuration réseau

2. **Erreur de configuration** :
   - Vérifier que le Config Server est accessible
   - Vérifier les fichiers dans `config-repo`

3. **Port déjà utilisé** :
   - Modifier les ports dans les fichiers de configuration
   - Arrêter les processus utilisant les ports

### Commandes Utiles

```bash
# Vérifier les processus Java
ps aux | grep java

# Tuer un processus
kill -9 <PID>

# Nettoyer les builds
mvn clean

# Forcer le rechargement des dépendances
mvn dependency:resolve
```

## Développement

### Structure du Projet

```text
section5/
├── animal-microservice/     # Service animal
├── config-repo/            # Configurations externes
├── configserver/           # Serveur de configuration
├── eurekaserver/           # Serveur Eureka
├── gatewayserver/          # Passerelle API
├── groupe-microservice/    # Service groupe
└── README.md              # Ce guide
```

### Ajout d'un Nouveau Service

1. Créer un nouveau module Maven
2. Ajouter la dépendance Eureka Client
3. Configurer le service dans `config-repo`
4. Enregistrer le service auprès d'Eureka

## Technologies Utilisées

- **Spring Boot** : Framework principal
- **Spring Cloud** : Pour les microservices
- **Netflix Eureka** : Découverte de services
- **Spring Cloud Gateway** : Routage API
- **Spring Cloud Config** : Configuration centralisée
- **Maven** : Gestion des dépendances et build

## Contributeurs

- Louay JDAY

## Licence

Ce projet est sous licence MIT.
