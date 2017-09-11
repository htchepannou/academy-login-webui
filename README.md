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


## Run
```
$ java -jar target/academy-login-service.jar
```

## Links
- Local Environment
    - [Sample login](http://localhost:8080/login/100)
    - [Service Health](http://localhost:8080/health) 

- Integration Environment
    - [Sample login](https://io-tchepannou-a-login-web.herokuapp.com/login/100)
    - [Service Health](https://io-tchepannou-a-login-web.herokuapp.com/health) 


## License
This project is open source sofware under the [MIT License](https://opensource.org/licenses/MIT)
