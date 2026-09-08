#!/bin/bash

mkdir -p build/classes
mkdir -p build/docs/javadoc
mkdir -p build/libs

javac -d build/classes src/main/java/*.java

javadoc -d build/docs/javadoc src/main/java/*.java

jar cfm build/libs/app.jar MANIFEST.MF -C build/classes .

java -cp build/classes ru.example.HeapSort