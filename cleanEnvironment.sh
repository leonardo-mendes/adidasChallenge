#!/bin/bash

docker-compose -f docker/docker-compose.yml down
docker image rm adidas-bff:0.0.1-SNAPSHOT adidas-subscription-api:0.0.1-SNAPSHOT adidas-smtp-connector:0.0.1-SNAPSHOT
