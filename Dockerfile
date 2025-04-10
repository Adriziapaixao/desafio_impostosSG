FROM openjdk:17-jdk-slim

# Baixe as dependências do Maven (etapa de cache)
RUN apt-get update && apt-get install -y wget && \
    wget https://archive.apache.org/dist/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.tar.gz && \
    tar -xvzf apache-maven-3.8.8-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.8/bin/mvn /usr/bin/mvn && \
    rm apache-maven-3.8.8-bin.tar.gz && \
    apt-get remove -y wget && apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copie o POM e baixe as dependências offline
COPY pom.xml /app/
RUN mvn dependency:go-offline -B

# Copie o código-fonte e compile
COPY src /app/src
RUN mvn package -DskipTests

# Exponha a porta e defina o comando de execução
EXPOSE 8080
CMD ["java", "-jar", "target/desafio_impostoSG-0.0.1-SNAPSHOT.jar"]
