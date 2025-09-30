#!/bin/bash

# Set Java 21
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

# Run Gradle simulation
./gradlew simulateJava
