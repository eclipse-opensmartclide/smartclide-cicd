#Using this image to have java + mvn
FROM adoptopenjdk/maven-openjdk11

WORKDIR app
COPY src/ src/
COPY pom.xml .
COPY Dockerfile .
COPY .dockerignore .

RUN mvn install -DskipTests=true
RUN mv target/*.%EXTENSION% ./app.%EXTENSION%
%EXTRA_BUILD_COMMANDS%
RUN ls

#Default port 8080 or include in run --server-port=PORT
ENTRYPOINT ["java","-jar","./app.%EXTENSION%"]