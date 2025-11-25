# 🔌 Guide Complet - Tester Circuit Breaker Resilience4J avec Postman

## �� Architecture Actuelle
```
- animal-ms (port 8082) → Appelle GROUPE via Feign avec Circuit Breaker
- groupe-ms (port 8081) → Service Backend
- groupe-ms2 (port 8083) → Deuxième instance pour Load Balancing
- Gateway (port 8888) → API Gateway
- Eureka (port 8761) → Service Registry
```

---

## 🚀 ÉTAPE 1: Importer la Collection Postman

1. **Ouvrez Postman**
2. **Cliquez sur "Import"** (en haut à gauche)
3. **Sélectionnez le fichier:** `CircuitBreaker_Postman_Collection.json`
4. ✅ La collection est importée avec toutes les requêtes

---

## 📝 ÉTAPE 2: Tester le Scénario Normal (Circuit CLOSED)

### Test 2.1: Groupe disponible sur port 8081
**Request:** GET http://localhost:8081/api/groupes/Félin
```json
Réponse attendue:
{
  "codeGroupe": 1,
  "nomGroupe": "Félin"
}
```

### Test 2.2: Groupe disponible sur port 8083 (groupe-ms2)
**Request:** GET http://localhost:8083/api/groupes/Félin
```json
Même réponse (load balancing)
```

### Test 2.3: Animal récupère le groupe (via Feign)
**Request:** GET http://localhost:8082/api/animals/id/1
```json
Réponse actuellement:
{
  "codeAnimal": 1,
  "nomAnimal": "Lion",
  "poidsAnimal": 190.5,
  "dateNaissance": "2025-11-19",
  "codeGroupe": "Félin",
  "nomGroupe": null  ← PROBLÈME À RÉSOUDRE
}
```

---

## 🔴 ÉTAPE 3: Tester le Circuit Breaker (OPEN)

### PRÉPARATION: Arrêter le service groupe-ms

**En Terminal:**
```bash
docker compose stop groupe-ms
# ou arrêtez juste groupe-ms2:
docker compose stop groupe-ms2
```

### Test 3.1: Vérifier qu'une instance est DOWN
```bash
docker compose ps | grep groupe
# Vous devez voir un service arrêté
```

### Test 3.2: Tester Animal (groupe arrêté)
**Request:** GET http://localhost:8082/api/animals/id/1
```json
Avec 1 instance arrêtée:
{
  "nomGroupe": null  ← Fallback pas encore activé
}

Avec TOUTES les instances arrêtées:
{
  "nomGroupe": "NOT AVAILABLE"  ← Fallback activé!
}
```

### Test 3.3: Arrêter TOUTES les instances de groupe
```bash
docker compose stop groupe-ms groupe-ms2
```

### Test 3.4: Tester Animal (service groupe DOWN)
**Request:** GET http://localhost:8082/api/animals/id/1

**Résultat:**
```json
{
  "codeAnimal": 1,
  "nomAnimal": "Lion",
  "poidsAnimal": 190.5,
  "dateNaissance": "2025-11-19",
  "codeGroupe": "Félin",
  "nomGroupe": "NOT AVAILABLE"  ✅ CIRCUIT BREAKER OUVERT!
}
```

---

## 🟢 ÉTAPE 4: Tester la Récupération (HALF_OPEN → CLOSED)

### Test 4.1: Redémarrer le service groupe
```bash
docker compose start groupe-ms
# ou
docker compose start groupe-ms2
```

### Test 4.2: Attendre 10 secondes
```
Le circuit breaker attend 10s avant de tenter une requête
waitDurationInOpenState: 10000ms
```

### Test 4.3: Tester Animal (service redémarré)
**Request:** GET http://localhost:8082/api/animals/id/1

**Progression:**
```
Avant 10s: nomGroupe = "NOT AVAILABLE" (OPEN)
Après 10s: nomGroupe = "Félin" (HALF_OPEN → CLOSED)
```

---

## 📊 ÉTAPE 5: Monitoring avec Actuator

### Test 5.1: État du Circuit Breaker
**Request:** GET http://localhost:8082/actuator/circuitbreakers

```json
{
  "circuitBreakers": {}
}
```
⚠️ NOTE: Circuit breaker vide si pas créé encore

### Test 5.2: Événements du Circuit Breaker
**Request:** GET http://localhost:8082/actuator/circuitbreakerevents

```json
Montre l'historique des transitions d'état:
CLOSED → OPEN → HALF_OPEN → CLOSED
```

### Test 5.3: Santé du Service
**Request:** GET http://localhost:8082/actuator/health

```json
{
  "status": "UP"
}
```

---

## 🧪 SCÉNARIO DE TEST COMPLET

### ✅ Test 1: Service Normal (5 appels)
```bash
for i in {1..5}; do
  curl -s http://localhost:8082/api/animals/id/1 | jq '.nomGroupe'
  sleep 1
done
# Résultat: null (problème de désérialisation)
```

### ❌ Test 2: Service DOWN (Fallback)
```bash
# Terminal 1:
docker compose stop groupe-ms groupe-ms2

# Terminal 2:
for i in {1..5}; do
  curl -s http://localhost:8082/api/animals/id/1 | jq '.nomGroupe'
  sleep 1
done
# Résultat: "NOT AVAILABLE" ✅
```

### 🔄 Test 3: Récupération (Load Balancing)
```bash
# Terminal 1:
docker compose start groupe-ms

# Terminal 2 (après 10s):
for i in {1..5}; do
  curl -s http://localhost:8082/api/animals/id/1 | jq '.nomGroupe'
  sleep 1
done
# Résultat: null puis "Félin" (selon la configuration)
```

---

## 🎯 Configuration du Circuit Breaker

**Fichier:** `animal-microservice/src/main/resources/application.yml`

```yaml
resilience4j.circuitbreaker:
  configs:
    default:
      slidingWindowSize: 10              # Fenêtre glissante: 10 appels
      permittedNumberOfCallsInHalfOpenState: 2
      failureRateThreshold: 50           # 50% d'erreurs = OPEN
      waitDurationInOpenState: 10000     # 10 secondes avant HALF_OPEN
```

---

## 🔍 Troubleshooting

### Problème: nomGroupe retourne toujours null
**Cause:** Problème de désérialisation JSON du GroupeDto
**Solution:** Vérifier les annotations Jackson dans `GroupeDto.java`

### Problème: Fallback jamais activé
**Cause:** Le circuit breaker n'est pas configuré correctement
**Solution:** Vérifier que `@FeignClient` avec `fallback = GroupeFallback.class`

### Problème: Services ne se découvrent pas
**Cause:** Eureka n'enregistre pas les services
**Solution:** Vérifier `DISCOVERY_SERVICE_URL` dans les variables d'environnement

---

## 📦 Ressources

- **Postman Collection:** CircuitBreaker_Postman_Collection.json
- **Eureka:** http://localhost:8761
- **Gateway:** http://localhost:8888
- **Animal Service:** http://localhost:8082
- **Groupe Service:** http://localhost:8081 et http://localhost:8083

