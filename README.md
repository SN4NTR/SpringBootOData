# Spring Boot 2 + OData 2 Application

Description
---

Main entity is `User`. The application gives opportunity to create, read, update and delete (CRUD) users.
The `H2` database is used for local storing `User`'s information.

Pre-requisites
---

* Maven 3.6.2 or later
* Java 8 or later
* IDE (IntelliJ IDEA, Eclipse)

Used Technologies
---

* Spring Boot 2.2.0
* Olingo v2.0

Local Deployment
-------

* Clone git repository:

```
git clone https://github.com/SN4NTR/SpringBootOData
```

* Open project package in command line and build it with Maven:

```
mvn clean install
```

* Start Spring Boot application:

```
./mvnw spring-boot:run
```