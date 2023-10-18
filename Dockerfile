FROM openjdk:17-alpine
VOLUME /tmp
ENV APP_NAME gestore2-api
COPY ./target/gestore2-api.jar /app/gestore2-api.jar
ENTRYPOINT ["java","-jar","/app/gestore2-api.jar"]