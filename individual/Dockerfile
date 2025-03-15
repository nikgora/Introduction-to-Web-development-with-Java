FROM eclipse-temurin:22 AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Этап запуска: используем JRE с Java 22 и устанавливаем Tomcat
FROM eclipse-temurin:22-jre
# Задаем версию Tomcat
ENV TOMCAT_VERSION=10.1.19
# Устанавливаем необходимые утилиты для загрузки и распаковки
RUN apt-get update && apt-get install -y wget tar && rm -rf /var/lib/apt/lists/*
WORKDIR /opt
# Загружаем и распаковываем Tomcat
RUN wget https://dlcdn.apache.org/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar xvf apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    mv apache-tomcat-${TOMCAT_VERSION} tomcat
# Определяем переменную окружения для Tomcat
ENV CATALINA_HOME=/opt/tomcat
# Открываем порт Tomcat
EXPOSE 8080
# Копируем WAR-файл из этапа сборки в папку webapps и переименовываем его в ROOT.war
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/ROOT.war
# Запускаем Tomcat
CMD ["sh", "-c", "$CATALINA_HOME/bin/catalina.sh run"]
