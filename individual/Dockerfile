# Этап сборки
FROM eclipse-temurin:22 AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Этап запуска
FROM eclipse-temurin:22-jre
# Задаем версию Tomcat
ENV TOMCAT_VERSION=11.0.0-M26
# Устанавливаем утилиты wget и tar
RUN apt-get update && apt-get install -y wget tar && rm -rf /var/lib/apt/lists/*
WORKDIR /opt
# Загружаем и распаковываем Tomcat из архива Apache
RUN wget https://archive.apache.org/dist/tomcat/tomcat-11/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar xvf apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    mv apache-tomcat-${TOMCAT_VERSION} tomcat
# Определяем переменную окружения для Tomcat
ENV CATALINA_HOME=/opt/tomcat
# Открываем порт Tomcat
EXPOSE 8080
# Копируем WAR-файл и переименовываем его в ROOT.war
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/ROOT.war
# Запускаем Tomcat
CMD ["sh", "-c", "$CATALINA_HOME/bin/catalina.sh run"]
