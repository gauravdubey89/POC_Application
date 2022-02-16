FROM openjdk:11
ADD target/cart-service-0.0.1-SNAPSHOT.jar cart-service-0.0.1-SNAPSHOT.jar
EXPOSE 8082
#ENTRYPOINT exec java $JAVA_OPTS -jar pocapplication.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT ["java", "-jar", "cart-service-0.0.1-SNAPSHOT.jar"]