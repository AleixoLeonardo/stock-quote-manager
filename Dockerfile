FROM openjdk:8-jdk-alpine
ENV SPRING_DATASOURCE_URL=jdbc:mysql://docker-mysql:3306/bootdb?useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
ENV APP_URL=http://app:8080/
COPY ./stock-quote-manager/target/stock-quote-manager-0.0.1-SNAPSHOT.war .
ENTRYPOINT ["java","-jar","/app.war"]