# Spring Boot 2 + OData 2 Application

Description
---

Main entity is `User`. The application gives opportunity to create, read, update and delete (CRUD) users.
The `H2` database is used for local storing `User`'s information.

Pre-requisites
---

* Java 8 or later
* IDE (IntelliJ IDEA, Eclipse)

Used Technologies
---

* Spring Boot 2.2.0
* Olingo v2.0

Local Deployment
---

* Clone git repository:

```
git clone https://github.com/SN4NTR/SpringBootOData
```

* Open project package in command line and build it with Maven:

```
./mvnw clean install
```

* Start Spring Boot application:

```
./mvnw spring-boot:run
```

* Open URL `localhost`:`port`/`odata/Users`

SAP Cloud Foundry Deployment
---

* Clone git repository:

```
git clone https://github.com/SN4NTR/SpringBootOData
```

* Open project package in command line and build it with Maven:

```
./mvnw clean install
```

* Download and install Cloud Foundry Command Line Interface (CF CLI):

```
https://docs.pivotal.io/pivotalcf/2-3/cf-cli/install-go-cli.html
```

* Open command line and choose the region for Cloud Foundry:

```
cf api CLOUD_FOUNDRY_API_ENDPOINT
```

* Login to your account via CF CLI:

```
cf login
```

Then enter your Email and Password.

* Choose your organization and space which you want to use:

```
cf target -o ORGANIZATION -s SPACE
```

* Open `manifest.yml` file and write `path` to your jar-file.

* Go to location of your app in command line.

* Start deploying:

```
cf push
```

* Open URL `app_name`.`domain_region`/`odata/Users`