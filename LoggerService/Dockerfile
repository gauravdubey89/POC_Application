FROM openjdk:11
ADD target/LoggerService-0.0.1-SNAPSHOT.jar LoggerService-0.0.1-SNAPSHOT.jar
EXPOSE 8083
#ENTRYPOINT exec java $JAVA_OPTS -jar pocapplication.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT ["java", "-jar", "LoggerService-0.0.1-SNAPSHOT.jar"]