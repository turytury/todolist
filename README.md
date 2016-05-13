---
Developer: Tury
Stack: Java
Framework: SpringBoot
---

To do list
================
This is an application to provide RESTful APIs for manage to do list. 
Each item consist of
* id
* subject
* content
* status

### Prerequisites
- Java
- Maven
- MongoDB (https://docs.mongodb.com/)

### Running Project
Clone project from repository
```
git@github.com:turytury/todolist.git
```
Build project
```
mvn clean install
```
Run project
```bash
mvn spring-boot:run
```

### RESTful API
```
GET: /todolist
```
* View all items in the list.

```
GET: /todolist/{id}
```
* View a single task in the list

```
POST: /todolist
```
* Add a task to the list

```
PUT: /todolist/{id}
```
* Edit existing task

```
PUT: /todolist/{id}/status/{status}
```
* Set the task status

```
DELETE: /todolist/{id}
```
* Delete a task from the list


If you want to play around, I also have JSONDoc for generate documentation for REST endpoints.

[http://localhost:8080/jsondoc-ui.html](http://localhost:8080/jsondoc-ui.html) with documentation [http://localhost:8080/jsondoc](http://localhost:8080/jsondoc) 
