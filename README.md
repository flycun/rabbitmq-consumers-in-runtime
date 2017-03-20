# This example shows how to enable and disable rabbit consumers in runtime

## This application starts or stops a rabbitmq consumer every minute. If the current minute is odd, it turns the consumer off. Otherwise, it starts the consumer.

## It slightly implements the [clean architecture pattern](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) 

### Important notices:
1. There are two approaches:
  1. Using the SimpleMessageListenerContainer
    1. This method is most painful, because you have to configure the SimpleMessageListenerContainer itself plus the listener method (take a look at ImportantMessageListener class), the Jackson2JsonMessageConverter, the MessageListenerAdapter (take a look at ConsumerConfig class)
    2. Using the SimpleRabbitListenerContainerFactory
  2. This is the easier one. If you do not intend to configure number of consumers and this kind of stuff, you just have to annotate the listener's method with @RabbitListener (take a look at ImportantMessageListener class)
    3. If you want to control the number of consumers, etc, you just have to configure the SimpleMessageListenerContainer (take a look at ConsumerConfig class)

2. The JSON expected to be posted in the queues is
```json
{
    "text": "message very important"
}
```
3. As the Jackson2JsonMessageConverter expects the property content_type to contain the text "json" and not always the producers set this property (sometimes it is being generated by another app or team), it was created the Jackson2JsonMessageConverterCustom that simple converts it no matter the property is set or not - it is only used together SimpleMessageListenerContainer.
4. A JMX example was also develop to serve as reference for this kind of approach (ConsumerJmx)

## To run this application, just follow the next steps:
* Start up the docker-compose (make sure you have it properly started)
** Open the docker directory and run "docker-compose up"
* Start up the spring-boot application through the Application class


