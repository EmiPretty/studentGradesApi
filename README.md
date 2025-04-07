# Student Grade API

## Description

Student Grade API est une application de gestion des notes des étudiants. Elle permet de gérer les étudiants, les cours, ainsi que les notes via des endpoints RESTful. Elle utilise Spring Boot, Spring Data JPA pour la gestion des données, et MySQL pour la base de données.

## Technologies utilisées

 Ajouter les dépendances nécessaires :
   - Spring Boot DevTools : Pour une expérience de développement améliorée (rechargement rapide, LiveReload).
   - Spring Web : Pour créer des applications web, y compris des API RESTful.
   - Spring Data JPA : Pour la persistance des données avec JPA et Hibernate.
   - MySQL Driver : Pour connecter l'application à une base de données MySQL.
   - Validation : Pour la validation des données via Bean Validation.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- Java 17+ : [Télécharger Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- Maven ou Gradle : [Installer Maven](https://maven.apache.org/install.html)
- MySQL ou MariaDB : [Télécharger MySQL](https://dev.mysql.com/downloads/installer/)
- Postman : [Télécharger Postman](https://www.postman.com/) pour tester les endpoints.

## Installation

1. Clonez le projet :
   git clone https://github.com/EmiPretty/studentGradesApi.git

2. Configurez votre base de données MySQL :
Créez une base de données student_grade_management.

3. Configurez les informations de connexion MySQL dans src/main/resources/application.properties

4. Exécutez l'application :
Avec l'IDE ou vous pouvez exécuter la commande suivante dans le terminal :
./mvnw spring-boot:run
