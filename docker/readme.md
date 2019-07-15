
## Docker File and context of execution on build

### Building a docker image with docker-compose ( must be installed seperately)
This will bring up a kafka-server and a springstream-kafka client. Upon running the client will 
create the topics and queues in kafka when it connects. 

#### Commands to get containers running
cd docker 

####Kafka / Zookeeper single node UP
docker-compose -f kafka-single-node.yml up

####Kafka / Zookeeper single node UP *background mode*
docker-compose  -f kafka-single-node.yml up -d

####Kafka / Zookeeper single node DOWN
docker-compose -f kafka-single-node.yml down

#### To login to kafka server
localhost:9092     guest/guest

#### To Get and Post Messages
localhost:8080/swagger-ui.html

Utilze the POST method. To see messages that are published and received , refer to the console log output.




### Building an image with a Dockerfile
Docker requires all commands are subdirectories from where it is being executed.
 For instance this docker command
 
 COPY ./target/spring-stream-kafka-demo-0.0.1-SNAPSHOT.jar /opt/demo/springstream-kafka.jar
 
 Requires that you are in the home directory when executing it. The path to the jar is important
 You can build a docker image from this directory by running this command

You must be in root top level directory to execute this script to create a local copy and tag it 
```
% docker build -f docker/springstream-kafka.Dockerfile . --tag softvisionlvcp/demo-spring-stream-kafka:latest
```
Build with the maven profile
```
% mvn clean install -Pbuild-docker-image
```

### Building using the script
You must be in root top level directory to execute this script
% ./docker/docker.sh 1.0

### Building a docker image with docker-compose ( must be installed seperately)

cd docker

docker-compose up --build --force-recreate    (runs in interactive mode , ctl c to stop)

docker-compose up --build --force-recreate --detach ( runs in background. use docker exec to connect to specific container)


To stop
docker compose down


### Running the docker container
This will run the container in background mode

% docker run -dit -p 8080:8080  demo-spring-stream-kafka:latest

To connect to the container to see processes running use this

% docker container ps
  CONTAINER ID        IMAGE                COMMAND                  CREATED              STATUS              PORTS                    NAMES
  9745c3639183        demo-spring-stream-kafka:latest   "/docker-entrypoint.…"   About a minute ago   Up 3 minutes        0.0.0.0:8780->8780/tcp   hopeful_bassi

Once you have the container ID you can connect to it

% docker exec -t -i 9745c3639183  /bin/bash

[root@9745c3639183 /]# ps -aef
UID        PID  PPID  C STIME TTY          TIME CMD
root         1     0  0 19:35 pts/0    00:00:00 /bin/bash /docker-entrypoint.sh
root         7     1 17 19:35 pts/0    00:00:51 java -jar springstream-kafka.jar
root        63     0  0 19:39 pts/1    00:00:00 /bin/bash
root        77    63  0 19:40 pts/1    00:00:00 ps -aef

#### Connecting to the service
On your local machine browser connect like so

http://localhost:8080/swagger-ui.html

This should return the swagger page of the springstream-kafka app

### Running container with environment variables passed in
If you have a profile setup with environment variables for the data base URL you can assign them at run time
Ex. from application.yml

spring:
    profiles:
      datasource:
          url: jdbc:mysql://${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}
          username: ${DATABASE_USER}
          password: ${DATABASE_PASSWORD}
          
alternative application-container.yml
datasource:
  airports:
      url: jdbc:mysql://${DATABASE_HOST}:${DATABASE_PORT}/${DATABASE_NAME}
      username: ${DATABASE_USER}
      password: ${DATABASE_PASSWORD}
      driverClassName: com.mysql.jdbc.Driver



% docker run -dit --name springstream-kafka  -p 8080:8080  -e DATABASE_HOST=db.host.com -e DATABASE_PORT=3306 -e DATABASE_NAME=dbname -e DATABASE_USER=dbuser -e DATABASE_PASSWORD=dbpassword     demo-spring-stream-kafka:latest

You can also enhance the command to provide a network link
% docker run -dit --name springstream-kafka --link kafka:service -p 8080:8080  -e DATABASE_HOST=db.host.com -e DATABASE_PORT=3306 -e DATABASE_NAME=dbname -e DATABASE_USER=dbuser -e DATABASE_PASSWORD=dbpassword     demo-spring-stream-kafka:latest

### Run interactively with shell to allow you to see execution
% docker run  --name springstream-kafka  -p 8080:8080  -e DATABASE_HOST=db.host.com -e DATABASE_PORT=3306 -e DATABASE_NAME=dbname -e DATABASE_USER=dbuser -e DATABASE_PASSWORD=dbpassword     demo-spring-stream-kafka:latest

### USEFUL commands
#####List all exited containers

docker ps -aq -f status=exited

#####Remove stopped containers

docker ps -aq --no-trunc | xargs docker rm

#####This command will not remove running containers, only an error message will be printed out for each of them.
Remove dangling/untagged images

docker images -q --filter dangling=true | xargs docker rmi

#####Remove containers created after a specific container

docker ps --since a1bz3768ez7g -q | xargs docker rm

#####Remove containers created before a specific container

docker ps --before a1bz3768ez7g -q | xargs docker rm