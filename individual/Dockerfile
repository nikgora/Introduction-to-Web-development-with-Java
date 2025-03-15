# Этап сборки: используем Maven с Java 22 для сборки приложения
FROM maven:3.9.0-eclipse-temurin-22 AS build
WORKDIR /app
# Копируем pom.xml и исходники
COPY pom.xml .
COPY src ./src
# Собираем проект (результат – WAR-файл в target/)
RUN mvn clean package

# Этап запуска: используем JRE с Java 22 и устанавливаем Tomcat
FROM eclipse-temurin:22-jre
# Задаем версию Tomcat
ENV TOMCAT_VERSION=10.1.12
# Устанавливаем необходимые утилиты для загрузки и распаковки
RUN apt-get update && apt-get install -y wget tar && rm -rf /var/lib/apt/lists/*
WORKDIR /opt
# Загружаем и распаковываем Tomcat
RUN wget https://downloads.apache.org/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    tar xvf apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    rm apache-tomcat-${TOMCAT_VERSION}.tar.gz && \
    mv apache-tomcat-${TOMCAT_VERSION} tomcat
# Определяем переменную окружения для Tomcat
ENV CATALINA_HOME=/opt/tomcat
# Открываем порт Tomcat
EXPOSE 8080
# Копируем WAR-файл из этапа сборки в папку webapps и переименовываем его в ROOT.war
# для развертывания приложения в корневом контексте
COPY --from=build /app/target/*.war $CATALINA_HOME/webapps/ROOT.war
# Запускаем Tomcat
CMD ["sh", "-c", "$CATALINA_HOME/bin/catalina.sh run"]
