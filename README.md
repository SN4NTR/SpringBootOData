# Spring Boot 2 + OData 2

### Description
Main entity of the application is User, which consists of following fields:

* ID
* First name
* Last name
* Date of creation
* Role

The application gives opportunity to create, read, update and delete (CRUD) users.

### Database
H2 is used as database for local deployment.

### REST

* GET /users - return all users
* GET /users/{id} - return user by ID
* POST /users - create new user
* PUT /users/{id} - update user by ID
* DELETE /users/{id} - delete user by ID

Local Deployment
-------
### Steps

* Clone git repository:

`git clone https://github.com/SN4NTR/SpringBootOData`

* Open project package in cmd and execute the following command:

`mvn clean install`

* Start Spring Boot application
* Request:

GET `http://localhost:8080/users`

We will see all users in database.