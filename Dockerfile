FROM openjdk:17
COPY "./target/BibliotecaUTS-1.jar" "app.jar"
EXPOSE 8103
ENTRYPOINT [ "java", "-jar", "app.jar" ]