FROM container-registry.oracle.com/graalvm/jdk:17
ARG APPLICATION_PATH=build/libs/*.jar
COPY ${APPLICATION_PATH} app.jar
ENTRYPOINT ["java", "-Xms1G", "-Xmx2G", "-jar", "app.jar"]
