# API Systeme de gestion d'etudiant

Application web Java (Jakarta EE) pour gerer des etudiants via:
- une interface JSP/Servlet (CRUD complet),
- une API de consultation exportable en `JSON` et `XML`.

## Analyse rapide du projet

Le depot est un projet **Dynamic Web Project Eclipse** (pas de Maven/Gradle) avec:
- Java 17
- Jakarta Servlet/JSP (Web 6.0)
- Apache Tomcat 10.1
- MySQL (driver `mysql-connector-j` fourni dans `WEB-INF/lib`)
- JSON-B (Yasson) et JAXB pour la serialisation
- Bootstrap 5 pour l'interface

Architecture observee:
- `src/main/java/ma/est/controllers` -> servlet MVC (CRUD etudiant + mini app calcul)
- `src/main/java/ma/est/api/controllers` -> servlet API (`/api/etudiant/*`)
- `src/main/java/ma/est/models` -> modeles (`Etudiant`, `EtudiantList`) + DAO
- `src/main/java/ma/est/dao/Db.java` -> connexion MySQL
- `src/main/webapp` -> JSP, ressources statiques, bibliotheques

## Fonctionnalités

### 1) Interface de gestion etudiant
- Afficher la liste des etudiants
- Afficher un etudiant
- Ajouter un etudiant
- Modifier un etudiant
- Supprimer un etudiant

### 2) API export
- Export de la liste en JSON/XML
- Export d'un etudiant (par id) en JSON/XML
- Interface d'acces API via `Welcome.jsp`

### 3) Mini module annexe
- Additionneur de 2 nombres (`/Calcul`, dossier `apptestcalcul`)

## Prerequis

- JDK 17
- Apache Tomcat 10.1
- MySQL 8+
- Eclipse IDE (ou tout IDE capable de deployer un projet web Jakarta EE)

## Configuration base de donnees

Le projet attend une base MySQL locale:
- host: `localhost`
- port: `3306`
- database: `etudiants`
- user: `root`
- password: vide

Configurer ces valeurs dans:
- `src/main/java/ma/est/dao/Db.java`

Exemple SQL minimal:

```sql
CREATE DATABASE IF NOT EXISTS etudiants
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE etudiants;

CREATE TABLE IF NOT EXISTS etudiant (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  telephone VARCHAR(30) NOT NULL,
  adresse VARCHAR(255) NOT NULL
);
```

## Lancer le projet (Eclipse + Tomcat)

1. Importer le dossier comme projet web Eclipse.
2. Verifier le runtime `Apache Tomcat v10.1` associe au projet.
3. Verifier la base MySQL et la table `etudiant`.
4. Demarrer Tomcat.
5. Ouvrir:
   - `http://localhost:8080/EtudiantAPI/` (selon votre context path)

## Routes principales

Le prefixe reel depend du context path Tomcat (souvent `/EtudiantAPI`).

### Interface web (`/etudiant`)

- `GET /etudiant` -> liste des etudiants
- `GET /etudiant?action=show&id={id}` -> fiche etudiant
- `GET /etudiant?action=create` -> formulaire de creation
- `POST /etudiant` avec `action=insert` -> insertion
- `GET /etudiant?action=edit&id={id}` -> formulaire d'edition
- `POST /etudiant` avec `action=update&id={id}` -> mise a jour
- `GET /etudiant?action=delete&id={id}` -> suppression

### API (`/api/etudiant/*`)

- `GET /api/etudiant/` -> page d'accueil API (`Welcome.jsp`)
- `GET /api/etudiant/liste?format=json`
- `GET /api/etudiant/liste?format=xml`
- `GET /api/etudiant/{id}?format=json`
- `GET /api/etudiant/{id}?format=xml`

Exemples:

```bash
curl "http://localhost:8080/EtudiantAPI/api/etudiant/liste?format=json"
curl "http://localhost:8080/EtudiantAPI/api/etudiant/1?format=xml"
```

## Structure du projet

```text
API-Systeme-de-gestion-d-etudiant/
  src/main/java/ma/est/
    api/controllers/EtudiantServlet.java
    controllers/EtudiantServlet.java
    controllers/CalculServlet.java
    dao/Db.java
    models/Etudiant.java
    models/EtudiantDAO.java
    models/EtudiantList.java
  src/main/webapp/
    index.jsp
    etudiants.jsp
    show.jsp
    create.jsp
    edit.jsp
    Welcome.jsp
    WEB-INF/web.xml
```

## Points d'amelioration proposes

- Externaliser la configuration DB (variables d'environnement ou fichier properties).
- Ajouter la gestion d'erreurs API (codes HTTP + messages JSON standardises).
- Ajouter une vraie couche de validation (ex: Bean Validation).
- Ajouter des tests (DAO + integration servlet).
- Migrer vers Maven pour simplifier la gestion des dependances.

## Auteur

- Brondon HOUAKEU
