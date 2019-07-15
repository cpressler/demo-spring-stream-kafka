

# Demo Spring Stream with KAFKA
## multi-channel/topic support


This project is a demonstration of using the Spring Cloud Stream with a Binder for Kafka
Based on the documentation here http://www.baeldung.com/spring-cloud-stream.
This example will bring up a kafka node and allow you to post a message to the Kafka Server.
The application will handle Producing the message and Consuming the Message to a log.

This application will also demonstrate how to interface to multiple Kafka Topics in one service by using CustomSource and Consumers that 
are configured by a Class and a Name that is mapped in the application.yml 

For an example  see
1) CustomSourceBean   - custom producer bean
2) CustomOutput - custom producer 
3) CustomInput - custom consumer
4) CustomChannelDemoMessageHandler - custom consumer handler
5) application.yml

```yml
        customInput:
          destination: demo.custom.topic
          content-type: application/text
          group: demoCustomConsumer
          binder: kafka1
        customOutput:
          destination: demo.custom.topic
          binder: kafka1
```


## Running the demo with docker-compose 
**(must be installed seperately)**

The demo assumes that Docker and Docker Compose have both been installed on your local Host computer.

### Commands to run
% cd docker
```
% docker-compose up
```

Wait for the services to come up  (runs in interactive mode , ctl c to stop)
1) Goto localhost:8080   this should bring up a landing page
2) Goto  localhost:8080/swagger-ui.html   to bring up the Message send interface
 Using the PUT interface to send a text string. This string should then be display in the docker-compose text window

```bash
springstream-kafka_1  | 2019-07-15 22:22:33.097  INFO 6 --- [nio-8080-exec-1] o.a.kafka.common.utils.AppInfoParser     : Kafka version : 2.0.1
springstream-kafka_1  | 2019-07-15 22:22:33.097  INFO 6 --- [nio-8080-exec-1] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId : fa14705e51bd2ce5
springstream-kafka_1  | 2019-07-15 22:22:33.117  INFO 6 --- [ad | producer-2] org.apache.kafka.clients.Metadata        : Cluster ID: Fn5D_tfmS-GHKFJpO310cg
springstream-kafka_1  | 2019-07-15 22:22:33.298  INFO 6 --- [container-0-C-1] c.s.d.e.handlers.DemoMessageHandler      : DemoMessageHandler:Received a message ->>>>  "this is a long string"
```

To cancel on the interactive console hit Ctl C and wait for a prompt
then 
```bash
% docker compose down
```


#### Alternatives for docker-compose
% docker-compose up --build --force-recreate    (runs in interactive mode , ctl c to stop)

% docker-compose up --build --force-recreate --detach ( runs in background. use docker exec to connect to specific container)

To Cancel in detach more run

% docker compose down.

### Connecting to Swagger UI Client to Send a Message

http://localhost:8080/swagger-ui.html

Use the PUT interface to send any message. To verify look at the console output.

**Normal Binding to default topic**
```bash 
curl -X PUT "http://localhost:8080/messages/test" -H "accept: */*" -H "Content-Type: application/json" -H "X-XSRF-TOKEN: c23f9cae-a6c0-465d-b769-30a42eb5aa85" -d "\"string\"" 
```   
**Custom Binding to topic**
```bash
curl -X PUT "http://localhost:8080/messages/test/custom" -H "accept: */*" -H "Content-Type: application/json" -H "X-XSRF-TOKEN: c23f9cae-a6c0-465d-b769-30a42eb5aa85" -d "\"custom1\""
```
**Custom Binding to topic2**
```bash
curl -X PUT "http://localhost:8080/messages/test/custom2" -H "accept: */*" -H "Content-Type: application/json" -H "X-XSRF-TOKEN: c23f9cae-a6c0-465d-b769-30a42eb5aa85" -d "\"custom message2\""
```
