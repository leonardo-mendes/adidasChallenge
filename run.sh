#!/bin/bash

cd adidas-bff
./gradlew clean build docker

cd ../adidas-subscription-api
./gradlew clean build docker

cd ../adidas-smtp-connector
./gradlew clean build docker

cd ../docker
docker-compose up -d
