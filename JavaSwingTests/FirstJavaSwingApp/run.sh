#!/bin/bash

# Compiles the java files
javac -cp bin/ -d bin/ src/application/*.java

# Runs the main application (App.class)
java -cp bin/ src/application/App