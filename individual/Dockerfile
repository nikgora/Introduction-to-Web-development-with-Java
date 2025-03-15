# Этап сборки
FROM eclipse-temurin:22 AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Этап запуска
FROM eclipse-temurin:22-jre
ENV TOMCAT_VERSION=11.0.0-M26
RUN apt-get update && apt-get install -y wget tar && rm -rf /var/lib/apt/lists/*
WORKDIR /opt
RUN wget https://archive.apache.org/dist/tomcat/tomcat-11/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar xvf apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    mv apache-tomcat-${TOMCAT_VERSION} tomcat

ENV CATALINA_HOME=/opt/tomcat

# Удаляем дефолтный ROOT
RUN rm -rf $CATALINA_HOME/webapps/ROOT

# Копируем собранный WAR в ROOT.war
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/ROOT.war

EXPOSE 8080
CMD ["sh", "-c", "$CATALINA_HOME/bin/catalina.sh run"]
