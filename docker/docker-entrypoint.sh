#!/bin/bash
set -e

echo "Start entrypoint"

cd /opt/demo
# this will force it to run forever in background
#java  -jar  springstream-kafka.jar
#ls -al
java -Dspring.profiles.active=container -jar springstream-kafka.jar

echo "DONE entrypoint"
