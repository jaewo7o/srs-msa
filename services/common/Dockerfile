FROM openjdk:11.0.8

EXPOSE 8081

ADD ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul", "/app.jar"]
