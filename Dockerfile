FROM openjdk:17

COPY target/ApointmentService-0.0.1-SNAPSHOT.jar ApointmentService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/ApointmentService-0.0.1-SNAPSHOT.jar"]