FROM openjdk:17
EXPOSE 8082
ADD target/customer-microservice.jar customer-microservice.jar
ENTRYPOINT ["java","-jar","/customer-microservice.jar"]