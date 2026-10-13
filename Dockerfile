# --- Étape 1 : Build de l'application avec Maven préinstallé ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copier les fichiers du projet
COPY pom.xml .
COPY src ./src

# Compiler et packager l'application
RUN mvn clean package -DskipTests

# --- Étape 2 : Exécution légère avec JRE Java 25 ---
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copier le JAR généré depuis l'étape précédente
COPY --from=build /app/target/*.jar app.jar

# Exposer le port par défaut pour Cloud Run
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]