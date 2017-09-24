Master: [![Build Status](https://travis-ci.org/htchepannou/academy-login-webui.svg?branch=master)](https://travis-ci.org/htchepannou/academy-login-webui)
[![Code Coverage](https://img.shields.io/codecov/c/github/htchepannou/academy-login-webui/master.svg)](https://codecov.io/github/htchepannou/academy-login-webui?branch=master)
[![JDK](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

# Academy Login WebUI
Web UI for login

## Requirements
- Java 1.8
- Maven

## Installation
Clone the code repository locally and build it.
```
$ git clone git@github.com:htchepannou/academy-login-webui.git
$ cd academy-login-webui
$ mvn compile
$ mvn clean install
```

This will generate the service binary ``target/academy-login-webui.jar``


## Run the service
- Run the service
```
$ java -jar target/academy-login-service.jar
```
- Access the login page at [http://localhost:8080/login](http://localhost:8080/login)


## Run the service locally
If you want to run the service and all its downstream locally:

- Run the dependent services using `local` profile:
  - `academy-user-service`: See instructions [here](https://github.com/htchepannou/academy-user-service#run-the-server-locally)
- Run the service using `local` profile: 
```
$ java -Dspring.profiles.active=local -jar target/academy-login-service.jar
```
- Test the status of the service at [http://localhost:28081/health](http://localhost:28081/health). The status should be `UP`.
- Access the [login page](http://localhost:28081/login?done=http://www.google.ca)


## License
This project is open source sofware under the [MIT License](https://opensource.org/licenses/MIT)
