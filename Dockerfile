FROM openjdk:8-jdk-alpine
COPY /target/project.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev-server","-jar","app.jar", "--imageServer=http://52.14.107.181:8086/images/"]
