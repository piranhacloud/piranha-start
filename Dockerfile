#
# Build the WAR file
#
FROM eclipse-temurin:17 AS builder
ENV MAVEN_VERSION 3.8.6
RUN cd /usr/local && \
    curl -O https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    tar xfvz apache-maven-$MAVEN_VERSION-bin.tar.gz
COPY . /root
RUN export PATH=$PATH:/usr/local/apache-maven-$MAVEN_VERSION/bin && \
    cd /root && \
    mvn --no-transfer-progress -DskipTests -DskipITs clean install

#
# Download the Piranha Servlet distribution
#
FROM eclipse-temurin:17 AS dist
ENV PIRANHA_VERSION 22.11.0
WORKDIR /root
RUN curl -O https://repo1.maven.org/maven2/cloud/piranha/dist/piranha-dist-servlet/$PIRANHA_VERSION/piranha-dist-servlet-$PIRANHA_VERSION.jar && \
    mv piranha-dist-servlet-$PIRANHA_VERSION.jar piranha-dist-servlet.jar

#
# Run the application
#
FROM eclipse-temurin:17
COPY --from=builder /root/target/piranha-start.war /root/ROOT.war
COPY --from=dist /root/piranha-dist-servlet.jar  /root/piranha-dist-servlet.jar
EXPOSE 8080
WORKDIR /root
CMD ["java", "-jar", "piranha-dist-servlet.jar", "--war-file", "ROOT.war"]
