# Spring Security + Spring MVC + Angular 7 Application Example
Simple example that represents Spring MVC+Security backend application which interacts with Angular frontend part.

## Technologies
- Gradle
- Java 8
- Spring MVC 
- Spring Security
- Hibernate
- HSQLDB
- Angular 7 + Bootstrap


## Functionality
The Application consists of sub-applications represented as Gradle sub-modules:
1. userapp-backend 
2. userapp-frontend

### Backend part
- Keeps list of users (CRUD operations) through Hibernate Framework. Each users has either ADMIN or USER role for security purposes.
- Initial database is created and populated by SQL scripts within resources folder.
- Controller endpoints are secured by Spring security annotations (security logic is implemented via JWT auth token).
- HSQLDB is used as Database.
- Spring i18n is supported.


### Frontend part
- Represents application with login window and CRUD forms for the list of users (CRUD actions are available only for users with ADMIN role).
- Default password - '12345'.
- i18n is supported.


Workflow
========
1. Run **gradle appRun** from room-booking-app-backend directory.
2. Run **ng serve --open** from room-booking-app-frontend directory.
