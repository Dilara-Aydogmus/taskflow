# Temel Java imajını al
FROM openjdk:17-jdk-slim

# Jar dosyasını konteynıra kopyala
COPY target/*.jar app.jar

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "/app.jar"]
