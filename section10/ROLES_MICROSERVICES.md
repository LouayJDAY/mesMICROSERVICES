# 📋 RÔLE DE CHAQUE MICROSERVICE

## 🏗️ Architecture Microservices - Vue d'ensemble

Cette architecture suit les principes **Spring Cloud** avec **service discovery**, **configuration centralisée** et **circuit breaker** pour la résilience.

---

## 🎯 Rôles des Services

### 1. **Eureka Server** (Port: 8761) 🏷️
**Rôle**: Service Discovery Registry
- **Responsabilités**:
  - Enregistrement des instances de microservices
  - Découverte dynamique des services par les autres microservices
  - Load balancing entre instances multiples
  - Health check des services enregistrés
- **Technologie**: Netflix Eureka Server
- **Endpoint**: http://localhost:8761

---

### 2. **Config Server** (Port: 9999) ⚙️
**Rôle**: Configuration Centralisée
- **Responsabilités**:
  - Stockage centralisé des configurations
  - Gestion des propriétés par environnement (dev, prod, etc.)
  - Mise à jour à chaud des configurations
  - Versionning des configurations
- **Technologie**: Spring Cloud Config Server
- **Endpoint**: http://localhost:9999

---

### 3. **Gateway Server** (Port: 8888) 🚪
**Rôle**: API Gateway & Routage
- **Responsabilités**:
  - Point d'entrée unique pour tous les appels API
  - Routage intelligent vers les microservices appropriés
  - Load balancing côté client
  - Authentification et autorisation centralisée
  - Rate limiting et sécurité
- **Routes configurées**:
  - `/api/animals/**` → Service ANIMAL
  - `/api/groupes/**` → Service GROUPE
- **Technologie**: Spring Cloud Gateway
- **Endpoint**: http://localhost:8888

---

### 4. **Animal Microservice** (Ports: 8082, 8084) 🦁
**Rôle**: Gestion des Animaux (Business Service)
- **Responsabilités**:
  - CRUD operations sur les entités Animal
  - Enrichissement des données avec les groupes (via Feign)
  - Circuit Breaker pour la résilience
  - Base de données H2 en mémoire
- **Endpoints**:
  - `GET /api/animals/id/{id}` - Animal simple
  - `GET /api/animals/{id}` - Animal enrichi avec groupe (circuit breaker)
  - `GET /api/animals/nom/{nom}` - Recherche par nom
- **Technologies**:
  - Spring Boot + JPA + H2
  - OpenFeign (communication inter-services)
  - Resilience4J (circuit breaker)
- **Données de test**:
  - Lion (groupe: Félin)
  - Elephant (groupe: Pachyderme)  
  - Girafe (groupe: Girafidé)

---

### 5. **Groupe Microservice** (Ports: 8081, 8083) 🏷️
**Rôle**: Gestion des Groupes Taxonomiques (Business Service)
- **Responsabilités**:
  - Gestion des catégories d'animaux (Félin, Pachyderme, etc.)
  - Service de référence pour l'enrichissement des données Animal
  - Base de données H2 en mémoire
- **Endpoints**:
  - `GET /api/groupes/{nom}` - Recherche par nom
  - `GET /api/groupes/all` - Liste complète
  - `POST /api/groupes` - Création
- **Technologies**: Spring Boot + JPA + H2
- **Données de test**:
  - Félin
  - Pachyderme
  - Girafidé

---

## 🔄 Flux de Communication

### Requête normale:
```
Client → Gateway (8888) → Eureka (8761) → Animal Service (8082/8084)
                                      ↓
                            Groupe Service (8081/8083) ← Enrichissement via Feign
```

### Avec Circuit Breaker (Groupe DOWN):
```
Client → Gateway → Animal Service → [Circuit Breaker OPEN] → Fallback: "NOT AVAILABLE"
```

---

## 🛡️ Patterns Implémentés

### 1. **Service Discovery** (Eureka)
- Découverte automatique des services
- Load balancing entre instances multiples

### 2. **API Gateway** (Spring Cloud Gateway)
- Point d'entrée unique
- Routage intelligent
- Abstraction des services internes

### 3. **Circuit Breaker** (Resilience4J)
- Protection contre les pannes en cascade
- Fallback automatique quand service indisponible
- Récupération automatique

### 4. **Configuration Centralisée** (Config Server)
- Gestion centralisée des propriétés
- Mises à jour sans redémarrage

### 5. **Communication Synchrone** (OpenFeign)
- Client HTTP déclaratif
- Intégration transparente avec circuit breaker

---

## 📊 États des Services (Docker Compose)

| Service | Port(s) | Rôle | Instances |
|---------|---------|------|-----------|
| eureka-server | 8761 | Service Discovery | 1 |
| config-server | 9999 | Configuration | 1 |
| gateway-server | 8888 | API Gateway | 1 |
| animal-ms | 8082 | Business (Animaux) | 1 |
| animal-ms2 | 8084 | Business (Animaux) | 1 |
| groupe-ms | 8081 | Business (Groupes) | 1 |
| groupe-ms2 | 8083 | Business (Groupes) | 1 |
| mysql | 3306 | Base de données | 1 |

---

## 🎯 Objectif de l'Architecture

Cette architecture démontre:
- **Résilience**: Circuit breaker protège contre les pannes
- **Évolutivité**: Services indépendants, multi-instances
- **Découplage**: Communication via API, pas base de données partagée
- **Observabilité**: Actuator endpoints pour monitoring
- **Configuration**: Gestion centralisée des propriétés

**Use Case**: Système de gestion d'animaux avec classification taxonomique, résilient aux pannes des services de classification.
