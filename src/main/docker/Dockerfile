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
# Run the application
#
FROM ghcr.io/piranhacloud/webprofile:24.3.0
EXPOSE 8080
COPY --from=builder /root/target/piranha-start.war /home/piranha/ROOT.war
WORKDIR /home/piranha
USER root
RUN chown -R piranha:piranha *
USER piranha
CMD ["java", "-jar", "piranha-dist-webprofile.jar", "--war-file", "ROOT.war"]
