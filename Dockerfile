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
    mvn --no-transfer-progress clean install

#
# Download the Piranha Micro distribution
#
FROM eclipse-temurin:17 AS dist
ENV PIRANHA_MICRO_VERSION 22.9.0
WORKDIR /root
RUN curl -O https://repo1.maven.org/maven2/cloud/piranha/piranha-micro/$PIRANHA_MICRO_VERSION/piranha-micro-$PIRANHA_MICRO_VERSION.jar && \
    mv piranha-micro-$PIRANHA_MICRO_VERSION.jar piranha-micro.jar

#
# Run the application
#
FROM eclipse-temurin:17
COPY --from=builder /root/target/piranha-start.war /root/ROOT.war
COPY --from=dist /root/piranha-micro.jar  /root/piranha-micro.jar
EXPOSE 8080
WORKDIR /root
CMD ["java", "-jar", "piranha-micro.jar", "--war-file", "ROOT.war"]
