# Adidas Subscription API
It's an API responsible for handling customers subscriptions.


### Docker Environment

* [Swagger](http://172.32.0.103:8989/swagger-ui.html) : API documentation.


### Running Locally

```
sh run.sh
```

### Local Environment

* [Swagger]( http://127.0.1.1:8989/swagger-ui.html)

Request Body
```
{
  "consentSubscribe": true (Mandatory),
  "dateOfBirth": "string" (Mandatory - Timestamp),
  "email": "string" (Mandatory),
  "firstName": "string",
  "gender": "string (It is not mandatory, possible values Male, Female and Other)"
}
```
Response Body
```
{
  "id": integer,
  "email": "string",
  "firstName": "string",
  "gender": "string",
  "dateOfBirth": "string",
  "consentSubscribe": boolean,
  "executed": boolean,
  "createdAt": long
}
```

### Tech Stack
[Gradle](https://gradle.org/) - [Spring Boot](https://projects.spring.io/spring-boot/) - [Java](https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html) - [Junit](http://junit.org/) - [Mockito](http://site.mockito.org/) - [Swagger](https://swagger.io/) - [H2 Database](https://www.h2database.com/html/main.html) - [Flyway](https://flywaydb.org/)