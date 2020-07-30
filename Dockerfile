FROM openjdk:8-jre-slim
ADD /target/service-1-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch service-1-0.0.1-SNAPSHOT.jar'
RUN mkdir /tmp/tomcat static
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 9097