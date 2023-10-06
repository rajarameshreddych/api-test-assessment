**Readme** 

This document helps you to run the tests after the current project is cloned from git to your local. This is a simple project that allows anyone to run Search and Lookup API tests with Java, Maven, RestAssured and TestNG. 

Currently the project has few tests in com.apple.itunestests.SearchApiTests and com.apple.itunestests.LookupApiTests. 

**Table of Contents** 

There are a few things we need before running the tests. These are:

1.     Java Installation / Update

2.     Setting up Maven

3.     Running the Project from Terminal

**Java Installation / Update **

·       Check your system to see if you have the latest Java version installed. 

·       Open Command/Terminal and verify below 

java -version 

·       If you do not have the latest Java installed, find out how to install Java here 

·       Ensure your JAVA_HOME environment to the location of the installed JDK. Find out how to do that here 

**Setting up Maven** 

·       Check your system to see if you have Maven already installed 

·       Open Command/Terminal and verify below 

mvn –version 

·       If you do not have the Maven installed then download and Install Maven. 

**Running the Project from the Command Line/Terminal**

Open Command 
Navigate to the project folder(the folder where you see pom.xml) 
Execute below command 

mvn test

You will see the required packages will be downloaded automatically and tests will run.

 
