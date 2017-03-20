# This example shows how to enable and disable rabbit consumers in runtime

## This application starts or stops a rabbitmq consumer every minute. If the current minute is odd, it turns the consumer off. Otherwise, it starts the consumer.

## It slightly implements the [clean architecture pattern](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) 

## To run this application, just follow the next steps:
* Start up the docker-compose (make sure you have it properly started)
** Open the docker directory and run "docker-compose up"
* Start up the spring-boot application through the Application class


