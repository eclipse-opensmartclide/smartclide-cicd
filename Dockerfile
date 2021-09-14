#Using this image to have java + mvn
FROM adoptopenjdk/maven-openjdk11
LABEL mainteiner="INTRASOFT International"

WORKDIR app
COPY src/ src/
COPY pom.xml .
COPY Dockerfile .
COPY .dockerignore .

RUN mvn install
RUN mv target/*.war ./app.war
RUN ls

#Default port 8080 or use in run --server-port=
ENTRYPOINT ["java","-jar","./app.war"]