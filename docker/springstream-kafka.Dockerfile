FROM centos:7
ARG version
ARG stack


RUN yum update -y && \
yum install -y java-1.8.0-openjdk && \
rm -rf /var/cache/yum

#RUN yum install -y java-1.8.0-openjdk

RUN mkdir /opt/demo

COPY ./target/spring-stream-kafka-demo-0.0.1-SNAPSHOT.jar /opt/demo/springstream-kafka.jar

ADD ./docker/docker-entrypoint.sh /docker-entrypoint.sh
RUN chmod +x /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]

#ENTRYPOINT ["java -jar /opt/demo/springstream-kafka.jar", "run"]
#ENTRYPOINT ["bash", "run"]
