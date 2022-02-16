FROM openjdk:11
ADD target/proto-0.0.1-SNAPSHOT.jar proto-0.0.1-SNAPSHOT.jar
EXPOSE 8083
#ENTRYPOINT exec java $JAVA_OPTS -jar pocapplication.jar
#For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT ["java", "-jar", "proto-0.0.1-SNAPSHOT.jar"]