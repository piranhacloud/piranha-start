FROM ghcr.io/piranhacloud/webprofile:24.4.0
COPY target/piranha-start.war /home/piranha/ROOT.war
WORKDIR /home/piranha
USER root
RUN chown -R piranha:piranha *
USER piranha
CMD ["java", "-jar", "piranha-dist-webprofile.jar", "--war-file", "ROOT.war"]
