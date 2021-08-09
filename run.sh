#!/bin/bash

set -e
WITH_CONTAINER=$1

install_docker() {
  if ! [ -x "$(command -v docker)" ]; then
    echo "----- Installing docker -----"
    sudo curl -fsSL https://get.docker.com -o get-docker.sh
    sh get-docker.sh
    sudo rm get-docker.sh
    getent group docker || groupadd docker
    sudo usermod -aG docker $USER
    sudo newgrp docker
  fi
  docker --version
}

install_docker_compose() {
  if ! [ -x "$(command -v docker-compose)" ]; then
    echo "----- Installing docker-compose -----"
    sudo curl -L https://github.com/docker/compose/releases/download/v1.25.2/docker-compose-$(uname -s)-$(uname -m) -o /usr/local/bin/docker-compose && sudo chmod +x /usr/local/bin/docker-compose
  fi
  docker-compose --version
}

build_docker_images() {
  echo "----- Building Docker Images -----"
  sudo sh generateImages.sh
}

run_docker_compose() {
  echo "----- Running docker-compose -----"
  cd docker
  sudo docker-compose up -d
}

install_docker && install_docker_compose && build_docker_images && run_docker_compose




