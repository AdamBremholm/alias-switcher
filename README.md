# Alias-switcher

A rest api backend for controlling what static ip address should be assigned to what pfsense alias in your pfsense homenetwork.
The server creates endpoints which pfsense can listen to to. It can also interact with the router through the [faux-api](https://github.com/ndejong/pfsense_fauxapi) plugin.
A frontend for this service is available at: [alias-switcher-frontend](https://github.com/AdamBremholm/alias-switcher-frontend)



### Tech

alias-switcher uses the following technology:

* [Spring Boot](https://spring.io/projects/spring-boot) - as the server running the application.
* [H2](https://www.h2database.com/html/main.html) - in-memory database
* [Docker](https://www.docker.com) - for containerizing and deploying

### Installation

alias switcher requires [mven](https://maven.apache.org) to build.


```sh
$ mvn package
$ docker build . -t yourrepo/name
$ docker run -p 5000:5000
```


