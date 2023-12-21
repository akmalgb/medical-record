# medical-record api

```
git clone https://github.com/akmalgb/medical-record.git
cd medical-record
mvn clean install
```

* switch spring.flyway.enabled to true in application.properties
* run

# api list
* get all users
```
http://localhost:8080/api/users/list
```

* get user by id
```
http://localhost:8080/api/users/{id}
```

* create user
```
http://localhost:8080/api/users/store
```
```
{
    "name": "string",
    "username": "string"
}
```

* delete user
```
http://localhost:8080/api/users/delete/{id}
```