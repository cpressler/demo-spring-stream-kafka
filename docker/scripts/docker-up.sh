#!/bin/bash

pwd
# Bring up Docker
cd docker
docker-compose up  -d #--build --force-recreate

# Give Docker up to 1 minute to initialize
COUNTER=0
while [  $COUNTER -lt 6 ]; do
    echo "Waiting for Docker to Initialize"
    let COUNTER=COUNTER+1
# what on the response from this url tells us when compose api is up
    STATUS=$(curl -s -o /dev/null -w '%{http_code}' http://localhost:8180/v2/api-docs)
    if [ $STATUS -eq 200 ]; then
        echo "Got 200! Docker is initialized!"
        break
    else
        echo "Got $STATUS :( Docker initialization is not done yet..."
    fi

    sleep 10
done

# Give Docker up to 1 minute to initialize
COUNTER2=0
while [  $COUNTER2 -lt 6 ]; do
    echo "Waiting for Docker Frontend to Initialize"
    let COUNTER2=COUNTER2+1
# what on the response from this url tells us when compose api is up
    STATUS=$(curl -s -o /dev/null -w '%{http_code}' http://localhost:9000)
    if [ $STATUS -eq 200 ]; then
        echo "Got 200! Docker Frontend is initialized!"
        break
    else
        echo "Got $STATUS :( Docker Frontend initialization is not done yet..."
    fi

    sleep 10
done
