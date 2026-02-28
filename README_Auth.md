# Authentification & Auto-Provisioning (Keycloak)

Ce document explique comment l'authentification et la création automatique d'utilisateurs (**Auto-Provisioning**) sont gérées dans le projet Smart Mobility Pass.

## 🧭 Flow d'authentification

1.  **Keycloak as IdP :** Keycloak est l'unique source de vérité pour l'authentification. L'utilisateur s'enregistre et se connecte directement via Keycloak.
2.  **JWT Token :** Une fois authentifié, l'utilisateur possède un jeton **JWT** contenant son `sub` (ID unique Keycloak), son email, son nom et son prénom.
3.  **Gateway Validation :** L'API Gateway valide le jeton et transmet les informations aux microservices.

## 🔄 Auto User Provisioning (Pattern)

Plutôt que d'avoir un endpoint `/register` complexe, nous utilisons un pattern de création à la volée au premier accès.

### Fonctionnement du endpoint `/api/users/me` 

Lorsqu'un utilisateur se connecte pour la première fois et appelle `/api/users/me` :

1.  **Extraction du JWT :** Le service extrait le `keycloakId` du claim `sub`.
2.  **Vérification en BDD :** Le service vérifie si un profil existe déjà pour cet ID.
3.  **Création à la volée :** Si aucun profil n'existe, il est créé automatiquement en utilisant les claims `given_name`, `family_name` et `email` présents dans le jeton.
4.  **Initialisation métier :** Lors de cette création, le service active automatiquement le **Mobility Pass** de l'utilisateur.

## 🛡️ Sécurité des Microservices

Chaque microservice (dont User Service) agit comme un **OAuth2 Resource Server** :
- Il valide la signature du JWT via l'URL `issuer-uri` de Keycloak.
- Il utilise l'ID de l'utilisateur authentifié (injecté via `@AuthenticationPrincipal`) pour les opérations métier.

## 🚀 Avantages

- **Expérience Utilisateur :** Aucun formulaire Redondant. Dès que l'utilisateur est loggé via Keycloak, son compte "Mobility" existe.
- **Maintenance :** Aucune gestion de mot de passe, salage ou hachage dans nos services.
- **Extensibilité :** On peut activer Google/Apple Login dans Keycloak sans changer une seule ligne de code dans nos microservices.
