#!/bin/bash

mkdir -p build/classes
mkdir -p build/docs/javadoc
mkdir -p build/libs

javac -d build/classes $(find src/main/java -name "*.java")

javadoc -d build/docs/javadoc $(find src/main/java -name "*.java")

jar cfm build/libs/app.jar MANIFEST.MF -C build/classes .

java -cp build/classes ru.nsu.fit.oop.ilg.blackjack.Main