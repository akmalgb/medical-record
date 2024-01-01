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
  "nik": "string",
  "name": "string",
  "username": "string",
  "address": "string",
  "dob": "string",
  "weight": integer,
  "height": integer,
  "isHasInsurance": boolean,
  "insuranceCarrier": "string",
  "policyNumber": "string",
  "emergencyContactNumber": "string",
  "emergencyContactName": "string",
  "medicalConditions": [
    {
      "doctorName": "string",
      "speciality": "string",
      "phone": "string",
      "notes": "string"
    },
    {
      "doctorName": "string",
      "speciality": "string",
      "phone": "string",
      "notes": "string"
    }
  ]
}
```

* delete user
```
http://localhost:8080/api/users/delete/{id}
```
