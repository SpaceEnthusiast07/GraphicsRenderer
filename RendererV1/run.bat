:: Do not display the commands written her in the terminal
:: Removes clutter from the terminal output
@echo off

:: Compile all the code in both packages
javac -cp ./bin/ -d ./bin/ src/application/*.java src/renderer/*.java

:: Run the application
java -cp ./bin/ application/App
