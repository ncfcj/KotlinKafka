# Etapa de construção
FROM gradle:8.8-jdk17 AS build

# Copie os arquivos do projeto para o diretório do gradle (considerando que o Dockerfile está em uma pasta dentro de producer)
COPY --chown=gradle:gradle . /home/gradle/src

# Defina o diretório de trabalho para o Gradle
WORKDIR /home/gradle/src

# Execute o build com o Gradle
RUN gradle build

# Configurações de runtime
FROM openjdk:17-jdk
VOLUME /tmp
EXPOSE 5005

# Copie o arquivo JAR gerado pela etapa anterior
COPY --from=build /home/gradle/src/build/libs/consumer-0.0.1-SNAPSHOT.jar app.jar

# Comando para rodar o JAR com suporte a debug (porta 5006)
CMD ["sh", "-c", "java -jar app.jar"]