FROM openjdk:11.0.8

EXPOSE 8000

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]