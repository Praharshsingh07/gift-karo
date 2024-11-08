FROM openjdk:21
WORKDIR /app

COPY target/gift-karo-0.0.1-SNAPSHOT.jar /app/gift-karo-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gift-karo-0.0.1-SNAPSHOT.jar"]