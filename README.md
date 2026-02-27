# User Mobility Pass Service

Ce projet est le microservice de gestion des utilisateurs (User Service) pour l'architecture **Smart Mobility**. Il s'occupe de la gestion des utilisateurs et se connecte à une base de données relationnelle. Sa configuration est externalisée et récupérée au démarrage via le **Spring Cloud Config Server**.

## Caractéristiques

- **Nom de l'application :** `user-mobility-pass-service`
- **Version de Java :** 17
- **Bases de données :** MySQL (via Spring Data JPA)
- **Découverte de services :** Eureka Client (Netflix)

## Principales Dépendances

Ce microservice utilise :
- **Spring Web** (`spring-boot-starter-webmvc`) pour l'API REST.
- **Spring Data JPA** (`spring-boot-starter-data-jpa`) pour la persistance des données.
- **MySQL Driver** (`mysql-connector-j`) pour la connexion à la base de données.
- **Spring Cloud Config Client** (`spring-cloud-starter-config` & `spring-cloud-starter-bootstrap`) pour récupérer la configuration distante.
- **Eureka Client** (`spring-cloud-starter-netflix-eureka-client`) pour s'enregistrer auprès du registre de services.
- **Lombok** (`lombok`) pour réduire le code boilerplate (getters, setters, etc.).

## Configuration Externalisée

Au démarrage, l'application tente de se connecter au serveur de configuration centralisé localisé à l'adresse suivante (par défaut) :
```text
http://localhost:8888
```

Le serveur de configuration doit être lancé **avant** ce microservice pour que les propriétés suivantes soient injectées avec succès :
- `server.port` : Le port d'exécution du service.
- `spring.datasource.*` : L'URL, l'utilisateur et le mot de passe de la base de données MySQL.
- `spring.jpa.*` : Les configurations Hibernate (ex: ddl-auto).
- `eureka.client.service-url.defaultZone` : L'URL du serveur Eureka.

## Comment lancer le projet ?

Assurez-vous que :
1. Votre serveur MySQL est en cours d'exécution.
2. Le `spring-cloud-config-server` (sur le port 8888) est démarré.

Ensuite, vous pouvez compiler et lancer ce microservice en utilisant Maven depuis la racine de `smart-mobility-user-service` :

```bash
./mvnw spring-boot:run
```

Sous Windows, utilisez :
```cmd
mvnw.cmd spring-boot:run
```
