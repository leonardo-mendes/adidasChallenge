#!/bin/bash

sh generateImages.sh

docker-compose -f docker/docker-compose.yml up -d




