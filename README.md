##microservices-spring-boot-demo
------
###user-registration-backend
> * Restful Service: Spring Boot + Spring MVC + Spring Data Mongodb + Mongodb
> * AMQP Service: Spring Boot + Spring AMQP + Rabbitmq
> * Deployment: Single runnable jar + embedded tomcat 

###mail-service-backend
> * Email Service: Spring Boot + Spring Mail
> * AMQP Service: Spring Boot + Spring AMQP + Rabbitmq
> * Deployment: Single runnable jar

###Run the application
Simple mode:
```java
java -jar target/user-registration-backend-*.jar
jar -jar target/mail-service-backend-*.jar
```
Test:
```
curl -v -d '{"email":"xxx@xxx.com", "password":"secret"}' -H "content-type: application/json"  http://127.0.0.1:8080/rest/user
```

