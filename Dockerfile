FROM openjdk:11-jre-alpine
COPY target/WorkshopHelloWorld-0.0.1-SNAPSHOT.jar WorkshopHelloWorld.jar
EXPOSE 9091/TCP
ENTRYPOINT ["java","-jar", "WorkshopHelloWorld.jar"]