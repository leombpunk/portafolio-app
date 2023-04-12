#original
#FROM openjdk:17-ea-slim-buster

#este pesa menos que el de arriba - usaria éste
#FROM openjdk:17-alpine

#mas pequeño 106,67mb
#FROM open-liberty:kernel-slim-java17-openj9

#pesa aun menos que los demas (creo)
#probar?
FROM amazoncorretto:17-al2-jdk 

EXPOSE 8080
ARG JAR_FILE=target/portfolio-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} springapi.jar
ENTRYPOINT [ "java", "-jar", "/springapi.jar" ]