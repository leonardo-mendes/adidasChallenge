# Adidas SMTP Connector
It's an API responsible for mocking the sending of email to customers.


### Docker Environment

* [Swagger](http://172.32.0.102:9090/swagger-ui.html) : API documentation.


### Running Locally

```
sh run.sh
```

### Local Environment

* [Swagger]( http://127.0.1.1:9090/swagger-ui.html)

```
{
  "consentSubscribe": true (Mandatory),
  "dateOfBirth": "string" (Mandatory),
  "email": "string" (Mandatory),
  "firstName": "string",
  "gender": "string (It is not mandatory, possible values Male, Female and Other)"
}
```

##### Mocking Result

```
Failed execution: email field contains failed in body
```

#### Tech Stack
[Gradle](https://gradle.org/) - [Spring Boot](https://projects.spring.io/spring-boot/) - [Java](https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html) - [Junit](http://junit.org/) - [Mockito](http://site.mockito.org/) - [Swagger](https://swagger.io/)