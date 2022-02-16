FROM openjdk:11
ADD target/kong-0.0.1-SNAPSHOT.jar kong-0.0.1-SNAPSHOT.jar
EXPOSE 8080
#ENTRYPOINT exec java $JAVA_OPTS -jar pocapplication.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT ["java", "-jar", "kong-0.0.1-SNAPSHOT.jar"]