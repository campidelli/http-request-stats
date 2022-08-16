# Purpose
This simple application parses a log file containing several HTTP requests and provides some useful statistics about it.

# Usage
TBD

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