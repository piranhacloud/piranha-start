#
# Build the WAR file
#
FROM eclipse-temurin:21 AS builder
ENV MAVEN_VERSION 3.8.6
RUN cd /usr/local && \
    curl -O https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    tar xfvz apache-maven-$MAVEN_VERSION-bin.tar.gz
COPY . /root
RUN export PATH=$PATH:/usr/local/apache-maven-$MAVEN_VERSION/bin && \
    cd /root && \
    mvn --no-transfer-progress -DskipTests -DskipITs clean install

#
# Download the Piranha Web Profile distribution
#
FROM eclipse-temurin:21 AS dist
ENV PIRANHA_VERSION 24.3.0
WORKDIR /root
RUN curl -O https://repo1.maven.org/maven2/cloud/piranha/dist/piranha-dist-webprofile/$PIRANHA_VERSION/piranha-dist-webprofile-$PIRANHA_VERSION.jar && \
    mv piranha-dist-webprofile-$PIRANHA_VERSION.jar piranha-dist-webprofile.jar

#
# Run the application
#
FROM eclipse-temurin:21
RUN useradd -m piranha
EXPOSE 8080
COPY --from=builder /root/target/piranha-start.war /home/piranha/ROOT.war
COPY --from=dist /root/piranha-dist-webprofile.jar  /home/piranha/piranha-dist-webprofile.jar
WORKDIR /home/piranha
RUN chown -R piranha:piranha *
USER piranha
CMD ["java", "-jar", "piranha-dist-webprofile.jar", "--war-file", "ROOT.war"]
