# transactions-api
This project consists of an API that is able to manage financial transactions for different accounts.

## Requirements
* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Maven](https://maven.apache.org/install.html)
* [Docker](https://docs.docker.com/engine/install/) and [docker-compose](https://docs.docker.com/compose/install/)

## Execute

Start the application without running the tests:
```shell
make start
```
The application will be running at http://localhost:8080/. If you want to know how to use it, make sure to visit the [usage](#usage) session.

Run tests locally
```shell
make test
```
Note that you'll need to set up all the [requirements](#requirements) to run the tests.

## Usage

You can read a non-interactive documentation with some examples [here](API_DOCS.md).
Also, if you start the application, you can try it out at http://localhost:8080/swagger-ui.html.
