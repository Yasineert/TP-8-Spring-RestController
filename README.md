# MS-Banque - Microservice de Gestion des Comptes Bancaires

## Description

MS-Banque est un microservice REST développé avec Spring Boot pour la gestion des comptes bancaires. Ce projet démontre l'utilisation de Spring Data JPA, la négociation de contenu (JSON/XML), et l'intégration de Swagger pour la documentation de l'API.

## Technologies Utilisées

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA** - Pour la persistence des données
- **H2 Database** - Base de données en mémoire
- **Lombok** - Réduction du code boilerplate
- **Swagger/OpenAPI** - Documentation automatique de l'API
- **Jackson** - Support JSON/XML

## Fonctionnalités

- ✅ **CRUD complet** sur les comptes bancaires
- ✅ **Négociation de contenu** - Support JSON et XML
- ✅ **Base de données H2** en mémoire avec console web
- ✅ **Documentation API** avec Swagger UI
- ✅ **Hot reload** avec Spring DevTools

## Structure du Projet

```
ms-banque/
├── src/
│   ├── main/
│   │   ├── java/ma/rest/spring/
│   │   │   ├── MsBanqueApplication.java
│   │   │   ├── entities/
│   │   │   │   ├── Compte.java
│   │   │   │   └── TypeCompte.java
│   │   │   ├── repositories/
│   │   │   │   └── CompteRepository.java
│   │   │   └── web/
│   │   │       └── CompteController.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
└── README.md
```

## Configuration

Le fichier `application.properties` contient les configurations suivantes :

```properties
# Base de données H2
spring.datasource.url=jdbc:h2:mem:banque
spring.datasource.username=sa
spring.datasource.password=

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Port du serveur
server.port=8082

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Installation et Démarrage

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+

### Étapes

1. **Cloner le projet**
```bash
git clone <url-du-repo>
cd ms-banque
```

2. **Compiler le projet**
```bash
mvn clean install
```

3. **Lancer l'application**
```bash
mvn spring-boot:run
```

L'application démarre sur le port **8082**.

## Endpoints API

### Base URL
```
http://localhost:8082
```

### Endpoints disponibles

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| `GET` | `/banque/comptes` | Récupérer tous les comptes |
| `GET` | `/banque/comptes/{id}` | Récupérer un compte par ID |
| `POST` | `/banque/comptes` | Créer un nouveau compte |
| `PUT` | `/banque/comptes/{id}` | Mettre à jour un compte |
| `DELETE` | `/banque/comptes/{id}` | Supprimer un compte |

## Tests avec cURL

### a) Requête GET pour obtenir la liste des comptes en JSON
```bash
curl -X GET "http://localhost:8082/banque/comptes" -H "Accept: application/json"
```

### b) Requête GET pour obtenir la liste des comptes en XML
```bash
curl -X GET "http://localhost:8082/banque/comptes" -H "Accept: application/xml"
```

### c) Requête POST pour créer un nouveau compte en JSON
```bash
curl -X POST "http://localhost:8082/banque/comptes" \
  -H "Content-Type: application/json" \
  -d '{"solde": 1500.0, "dateCreation": "2024-11-01", "type": "COURANT"}'
```

### d) Requête POST pour créer un nouveau compte en XML
```bash
curl -X POST "http://localhost:8082/banque/comptes" \
  -H "Content-Type: application/xml" \
  -d '<compte><solde>1500.0</solde><dateCreation>2024-11-01</dateCreation><type>COURANT</type></compte>'
```

### e) Requête PUT pour mettre à jour un compte en JSON
```bash
curl -X PUT "http://localhost:8082/banque/comptes/1" \
  -H "Content-Type: application/json" \
  -d '{"solde": 2000.0, "dateCreation": "2024-11-01", "type": "EPARGNE"}'
```

### f) Requête PUT pour mettre à jour un compte en XML
```bash
curl -X PUT "http://localhost:8082/banque/comptes/1" \
  -H "Content-Type: application/xml" \
  -d '<compte><solde>2000.0</solde><dateCreation>2024-11-01</dateCreation><type>EPARGNE</type></compte>'
```

### g) Requête DELETE pour supprimer un compte
```bash
curl -X DELETE "http://localhost:8082/banque/comptes/1"
```

## Documentation Swagger

Une fois l'application lancée, accédez à la documentation interactive Swagger UI :

```
http://localhost:8082/swagger-ui/index.html
```

![Swagger UI - Documentation OpenAPI](docs/swagger-ui.png)

### Fonctionnalités Swagger
- Visualisation de tous les endpoints disponibles
- Test interactif des API directement depuis l'interface
- Documentation détaillée des modèles de données
- Support des formats JSON et XML

## Console H2

Pour accéder à la console H2 et visualiser les données :

```
http://localhost:8082/h2-console
```

**Paramètres de connexion :**
- JDBC URL: `jdbc:h2:mem:banque`
- User Name: `sa`
- Password: _(laisser vide)_

![Console H2 - Base de données](docs/h2-console.png)

## Modèle de Données

### Entité Compte

```java
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Compte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double solde;
    
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    
    @Enumerated(EnumType.STRING)
    private TypeCompte type;
}
```

### Énumération TypeCompte

```java
public enum TypeCompte {
    COURANT,
    EPARGNE
}
```

## Tests avec Postman ou SoapUI

### Configuration dans Postman/SoapUI

1. **Endpoint** : `http://localhost:8082/banque/comptes`
2. **Méthode HTTP** : GET
3. **Headers** :
   - Pour JSON : `Accept: application/json`
   - Pour XML : `Accept: application/xml`

### Exemple de réponse JSON
```json
[
  {
    "id": 1,
    "solde": 4696.507485480144,
    "dateCreation": "2025-11-22",
    "type": "EPARGNE"
  },
  {
    "id": 2,
    "solde": 3076.3841827122037,
    "dateCreation": "2025-11-22",
    "type": "COURANT"
  },
  {
    "id": 3,
    "solde": 146.3665515681546,
    "dateCreation": "2025-11-22",
    "type": "EPARGNE"
  }
]
```

### Exemple de réponse XML
```xml
<List>
  <item>
    <id>1</id>
    <solde>4696.507485480144</solde>
    <dateCreation>2025-11-22</dateCreation>
    <type>EPARGNE</type>
  </item>
  <item>
    <id>2</id>
    <solde>3076.3841827122037</solde>
    <dateCreation>2025-11-22</dateCreation>
    <type>COURANT</type>
  </item>
  <item>
    <id>3</id>
    <solde>146.3665515681546</solde>
    <dateCreation>2025-11-22</dateCreation>
    <type>EPARGNE</type>
  </item>
</List>
```

## Négociation de Contenu

L'API supporte la négociation de contenu automatique grâce à Spring Boot :

- **JSON** : En-tête `Accept: application/json` ou `Content-Type: application/json`
- **XML** : En-tête `Accept: application/xml` ou `Content-Type: application/xml`

Le serveur retournera automatiquement le format demandé par le client.

## Dépendances Maven Principales

```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- H2 Database -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    
    <!-- Swagger/OpenAPI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.1.0</version>
    </dependency>
    
    <!-- XML Support -->
    <dependency>
        <groupId>com.fasterxml.jackson.dataformat</groupId>
        <artifactId>jackson-dataformat-xml</artifactId>
    </dependency>
</dependencies>
```

## Captures d'Écran

### Interface Swagger UI
L'interface Swagger permet de tester facilement tous les endpoints de l'API avec support JSON et XML.

### Console H2
La console H2 permet de visualiser et manipuler directement les données en base.

## Remarques

- L'application utilise une base de données **H2 en mémoire**, les données sont donc perdues à chaque redémarrage.
- Des données de test sont automatiquement créées au démarrage de l'application.
- Le **hot reload** est activé grâce à Spring DevTools pour faciliter le développement.

## Améliorations Futures

- [ ] Ajouter la validation des données avec Bean Validation
- [ ] Implémenter la gestion des exceptions personnalisées
- [ ] Ajouter la pagination pour les listes de comptes
- [ ] Implémenter des tests unitaires et d'intégration
- [ ] Ajouter la sécurité avec Spring Security
- [ ] Migrer vers une base de données persistante (PostgreSQL, MySQL)

## Auteur

Développé dans le cadre d'un projet d'apprentissage Spring Boot et REST APIs.

## Licence

Ce projet est à des fins éducatives.
