# Communication Inter-Microservices - Spring Cloud OpenFeign

## Architecture

Ce projet démontre la communication entre deux microservices :
- **animal-microservice** (port 8082) : Service gérant les animaux
- **groupe-microservice** (port 8081) : Service gérant les groupes

## Démarrage des Microservices

### Terminal 1 - Démarrer groupe-microservice
```bash
cd groupe-microservice
java -jar target/groupe-microservice-0.0.1-SNAPSHOT.jar
```
La console H2 est accessible à : `http://localhost:8081/h2-console`

### Terminal 2 - Démarrer animal-microservice
```bash
cd animal-microservice
java -jar target/animal-microservice-0.0.1-SNAPSHOT.jar
```
La console H2 est accessible à : `http://localhost:8082/h2-console`

## Données de Test

### Groupes (groupe-microservice)
- Félin
- Pachyderme
- Girafidé

### Animaux (animal-microservice)

- Lion (poids: 190.5, groupe: Félin)
- Elephant (poids: 5000.0, groupe: Pachyderme)
- Girafe (poids: 1200.0, groupe: Girafidé)

## Tests avec Postman

### 1. Récupérer un animal par ID (simple)
```
GET http://localhost:8082/api/animals/id/1
```
Réponse :
```json
{
  "codeAnimal": 1,
  "nomAnimal": "Lion",
  "poidsAnimal": 190.5,
  "dateNaissance": "2025-10-28",
  "codeGroupe": null,
  "nomGroupe": null
}
```

### 2. Récupérer un animal par nom (simple)
```
GET http://localhost:8082/api/animals/nom/Lion
```

### 3. Récupérer un animal avec son groupe (Communication OpenFeign)
```
GET http://localhost:8082/api/animals/1/with-groupe
```

Réponse avec communication inter-microservices :
```json
{
  "animalDto": {
    "codeAnimal": 1,
    "nomAnimal": "Lion",
    "poidsAnimal": 190.5,
    "dateNaissance": "2025-10-28",
    "codeGroupe": "Félin",
    "nomGroupe": "Félin"
  },
  "groupeDto": {
    "codeGroupe": 1,
    "nomGroupe": "Félin"
  }
}
```

## Modifications Principales

### 1. Entité Animal
- Ajout du champ `codeGroupe` pour lier un animal à un groupe

### 2. AnimalDto
- Ajout de `codeGroupe` et `nomGroupe` pour le transport de données

### 3. GroupeDto
- Créée dans le projet animal-microservice pour la sérialisation des données du groupe-microservice

### 4. APIResponseDto
- Combine `AnimalDto` et `GroupeDto` pour les réponses complexes

### 5. APIClient (FeignClient)
- Interface Feign pour appeler `groupe-microservice`
- URL : `http://localhost:8081`
- Endpoint : `GET /api/groupes/{nom}`

### 6. AnimalServiceImpl
- Méthode `getAnimalByIdWithGroupe()` qui utilise APIClient pour récupérer les informations du groupe

### 7. AnimalController
- Nouvel endpoint : `GET /api/animals/{id}/with-groupe` pour retourner APIResponseDto

### 8. AnimalMicroserviceApplication
- Annotation `@EnableFeignClients` activée
- Bean `WebClient` configuré
- Dépendance Spring Cloud OpenFeign ajoutée

## Flux de Communication

1. **Client** → POST/GET sur `http://localhost:8082/api/animals/{id}/with-groupe`
2. **AnimalController** → Appelle `AnimalService.getAnimalByIdWithGroupe(id)`
3. **AnimalServiceImpl** → Récupère l'animal de la base de données
4. **AnimalServiceImpl** → Utilise **APIClient (Feign)** pour appeler `groupe-microservice`
5. **APIClient** → `GET http://localhost:8081/api/groupes/{codeGroupe}`
6. **GroupeController** → Retourne les données du groupe
7. **APIResponseDto** → Combine les données et les retourne au client

## Dépendances Ajoutées

```xml
<!-- WebFlux pour les communications asynchrones -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webflux</artifactId>
</dependency>

<!-- Spring Cloud OpenFeign pour les appels déclaratifs -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>4.0.3</version>
</dependency>
```

## Avantages de Spring Cloud OpenFeign

✅ **Déclaratif** : Définition simple via interfaces  
✅ **Automatique** : Spring génère l'implémentation  
✅ **Gestion des erreurs** : Gestion intégrée des exceptions  
✅ **Résilience** : Intégration possible avec Hystrix/Resilience4j  
✅ **Support du Load Balancing** : Avec Spring Cloud LoadBalancer  

## Dépannage

### Erreur : "Connection refused"
- Vérifiez que les deux microservices sont en cours d'exécution
- Vérifiez les ports (8081 et 8082)

### Erreur : "No instances available"
- S'assurer que les deux services sont démarrés avant d'appeler l'endpoint composite

### Animal sans groupe associé
- Les animaux créés au démarrage n'ont pas de `codeGroupe` assigné par défaut
- Vous devez manuellement ajouter un animal avec un `codeGroupe` valide

## Prochaines Étapes Possibles

1. Ajouter du service de découverte (Eureka)
2. Ajouter de la résilience (Hystrix/Resilience4j)
3. Ajouter du circuit breaker
4. Ajouter du load balancing
5. Ajouter de la sécurité (OAuth2/JWT)
