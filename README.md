# Purpose
This simple application parses a log file containing several HTTP requests and provides some useful statistics about it.

# Usage
This project can be executed in three different ways. The most important part is to pass the file containing the HTTP requests as the first program argument.
The results then will be shown in the console.
### Using Maven `exec-maven-plugin`
In a terminal, run the following command in the project root directory.
````
mvn compile exec:java -Dexec.mainClass="campidelli.http.request.stats.App" -Dexec.arguments="programming-task-example-data.log"
````
The last parameter can be changed to point to another location where the log file is.
### Executing the JAR file
In a terminal, build the project using `mvn install` or `mvn package` and then navigate to the `target` folder. Then execute the following command:
````
java -jar http-request-stats-1.0-SNAPSHOT.jar ../programming-task-example-data.log
````
The last parameter can be changed to point to another location where the log file is.
### Using your preferred IDE
Run the main application `campidelli.http.request.stats.App` passing the file name as argument.

# Project development
This section describes the thinking process behind the project development.

1. Understand the log pattern, find the useful information and create a Regex expression using https://regex101.com/
2. Create a simple Java project using Maven
````
mvn archetype:generate -DgroupId=campidelli.http.request.stats -DartifactId=http-request-stats -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
````
3. Add it to a new Github repository
````
git init
````
4. Write down the Java classes and unit tests.
5. Test it.
6. Write the README file, describing how to execute the application.
7. Done :)

test