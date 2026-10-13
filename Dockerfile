# --- Étape 1 : Build de l'application ---
FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

# Copier les fichiers de configuration et le code source
COPY pom.xml .
COPY src ./src

# Compiler et packager l'application (en ignorant les tests pour aller plus vite)
RUN ./mvnw clean package -DskipTests

# --- Étape 2 : Exécution de l'application ---
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

# Copier le JAR généré depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port par défaut (Cloud Run injecte la variable PORT dynamiquement)
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]