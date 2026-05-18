:: Ensures the user doesn't see these commands being entered into the terminal
:: I.e. removes clutter from the terminal output
@echo off

:: Compiles the java files
javac -cp bin/ -d bin/ src/application/*.java

:: Runs the main application (App.class)
java -cp bin/ src/application/App