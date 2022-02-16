FROM openjdk:11
ADD target/productService-0.0.1-SNAPSHOT.jar productService-0.0.1-SNAPSHOT.jar
EXPOSE 8084
#ENTRYPOINT exec java $JAVA_OPTS -jar pocapplication.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT ["java", "-jar", "productService-0.0.1-SNAPSHOT.jar"]