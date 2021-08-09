# Adidas BFF
It's an BFF API responsible for handling customers subscriptions.


### Docker Environment

* [Altair GraphQL UI](http://172.32.0.101:9595/altair) : API documentation.


### Running Locally

```
sh run.sh
```

### Local Environment

* [Altair GraphQL UI](http://127.0.1.1:9595/altair) - Documentation

#### Subscribe
```
mutation{
  subscribe(subscription: {
    firstName: "string"
    email: "string"
    gender: "string"
    dateOfBirth: "string"
    consentSubscribe: true
  }){
    id
    email
    firstName
    gender
    dateOfBirth
    consentSubscribe
  }
}
```

#### Unsubscribe
```
mutation{
  unsubscribe(id: integer)
}
```

#### Retrieve Subscriptions by Id
```
query{
  subscription(id: integer){
    id
    email
    firstName
    gender
    dateOfBirth
  }
}
```

#### Retrieve All Subscriptions
```
query{
  subscriptions{
    id
    email
    firstName
    gender
    dateOfBirth
  }
}
```

*Errors*
```
Sometimes, when running for the first time, it is necessary to clear the Altair variables:

- Click Set Variables
- Remove undefined
```


### Tech Stack
[Gradle](https://gradle.org/) - [Spring Boot](https://projects.spring.io/spring-boot/) - [Java](https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html) - [Junit](http://junit.org/) - [Mockito](http://site.mockito.org/) - [GraphQL](https://github.com/graphql-java-kickstart/graphql-spring-boot)
