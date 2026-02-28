# User Mobility Pass Service (UserProfile)

Ce projet est le microservice de gestion des profils utilisateurs et des titres de transport pour l'architecture **Smart Mobility**. 

## Rôle du Microservice

Dans l'architecture actuelle, ce service a évolué pour devenir un **User Profile Service**. Son rôle est découplé de l'authentification :
- **Authentification :** Déléguée à un serveur **Keycloak**.
- **Profil Utilisateur :** Stocke les informations métier (nom, prénom) liées à l'ID Keycloak.
- **Mobility Pass :** Gère le pass de transport numérique, le statut (ACTIF/SUSPENDU) et le suivi de la consommation journalière (**Daily Cap** de 25.00€).
- **Abonnements :** Gère les abonnements actifs de l'utilisateur.

## Caractéristiques Techniques

- **Nom :** `user-mobility-pass-service`
- **Identifiant Unique :** `keycloakId` (UUID Keycloak)
- **Base de données :** MySQL
- **Sécurité :** Intégration Keycloak 

## Endpoints Principaux

### Gestion des Profils (`/api/users`)
- `POST /api/users/profile` : Crée un profil métier après une inscription Keycloak.
- `GET /api/users/{keycloakId}` : Récupère les infos d'un profil.

### Gestion des Passes (`/api/passes`)
- `POST /api/passes/activate/{userId}` : Initialise un pass pour un utilisateur.
- `GET /api/passes/{userId}` : Récupère le statut et la consommation journalière (utilisé par **Pricing** et **Trip Management**).
- `PATCH /api/passes/{userId}/spent` : Met à jour la consommation cumulée (utilisé par **Billing**).

### Gestion des Abonnements (`/api/subscriptions`)
- `GET /api/subscriptions/active/{userId}` : Liste les abonnements actifs (utilisé par **Pricing** pour calculer les remises).

## Intégration avec les autres Services

Ce service est une pièce maîtresse consultée via **Feign Clients** par :
1. **Trip Management Service :** Pour vérifier si le pass est actif lors d'un scan.
2. **Pricing Service :** Pour récupérer les abonnements et le montant déjà dépensé ce jour.
3. **Billing Service :** Pour mettre à jour le montant dépensé après facturation.

## Configuration

La configuration (Base de données, Eureka, Keycloak) est récupérée via le **Config Server**.
- **Local :** `default` profile.
- **Docker :** `dev` profile.
