# Spring Security + Spring MVC + Angular 7 Application Example
Simple example that represents Spring MVC+Security backend application which interacts with Angular frontend part.

## Technologies
- Gradle
- Java 8
- Spring MVC 
- Spring Security
- Hibernate
- HSQLDB
- Angular 7 + Bootstrap + JQuery


## Functionality
The Application consists of sub-applications represented as Gradle sub-modules:
1. userapp-backend 
2. userapp-frontend

### Backend part
- Keeps list of users (CRUD operations) through Hibernate Framework. Each users has either ADMIN or USER role for security purposes.
- Initial database is created and populated by SQL scripts within resources folder (HSQL is used as application DB).
- Controller endpoints are secured by Spring security annotations (security logic is implemented via JWT auth token filter).
- File upload is supported.
- Spring i18n is supported.
- Integration tests provided.


### Frontend part
- Represents application with login window and CRUD forms for the list of users (CRUD actions are available only for users with ADMIN role).
- Default password - '12345'.
- i18n is supported.


Workflow
========
## Run project
1. Run **gradle runBackend** from project root directory (http://localhost:8090/userapp-backend).
2. Run **gradle runFrontend** from project root directory (http://localhost:4200).

## Debug backend
1. Run **gretty appRunDebug** gradle task.
2. Run following remote debug configuration in IDEA: 
```
-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
```
