
# Hobby Project - Fantasy Database (Back End)

This project as part of the Software course including Java and Spring.

This repo is the back-end for recieving fetch requests and accessing the Fantasy Database.

The front end can be found at:
https://github.com/Callum-Robinson/hobby-project-frontend

The kanban board can be found at:
https://callumjrobinson.atlassian.net/jira/software/projects/HP/boards/3 (This may be unavailable if viewing later)



## Installation

Install this project by simply cloning

```bash
  git clone https://github.com/Callum-Robinson/hobby-project-backend.git
```


## Running the Application

Ensure you have the correct MySQL login on the application-prod.properties file (default as root for username and password) and set the active profile to prod
***if wanting to the run the production version.***

Run the jar file as follows:

```bash
  java -jar hobby-project-backend-0.0.1-SNAPSHOT.jar
```
You can then send http requests to the local host port the application is running on

This can be done throught the front end provided or through applications such as Postman using: localhost:8080
## Running Tests

Coverage: 82%

To run tests, run the following command

```bash
  mvn test
```


## Authors

- Callum Robinson - [@Callum-Robinson](https://github.com/Callum-Robinson)


## Built with
 - [Maven](https://maven.apache.org/)
## Acknowledgements

 - Morgan Walsh for the support during the project
 - Jamie-lee MacAskill for her constant support

