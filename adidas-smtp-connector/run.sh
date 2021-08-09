#!/bin/bash

./gradlew clean build bootRun --args='--spring.profiles.active=local'