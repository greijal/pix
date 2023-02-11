<div align="center">
<a href="https://www.emojione.com/emoji/1f410">
  <img
    height="80"
    width="80"
    alt="goat"
    src="https://upload.wikimedia.org/wikipedia/commons/8/8a/Banco_Ita%C3%BA_logo.svg"
  />
</a>
<h1>Itau - PIX Case</h1>
</div>



[![codecov](https://codecov.io/gh/greijal/pix/branch/master/graph/badge.svg?token=J9ABG5PDA1)](https://codecov.io/gh/greijal/pix) 
![GitHub repo file count](https://img.shields.io/github/directory-file-count/greijal/pix)
![GitHub top language](https://img.shields.io/github/languages/top/greijal/pix)

## Prerequisites

* Have a JDK 11 installed
* Have Gradlew installed and available on your PATH or IDE
* Have Docker/Docker compose installed


## Technology Stack

- ``Java 18``
- ``Spring Boot 3.0.2``
- ``Junit 5.8.2``
- ``Mockito 4.3.1``
- ``Gradlew``
- ``Jacoco``
- ``Docket``
- ``InteliJ IDEA``

## Run
 1 - Start docker-compose 
 
```bash
docker compose up
```
2 -  Start Project
```bash
 ./gradlew bootRun
 ```

### Postmen 
 You can import the Postmen collection and use it to help to make those requests. 
 
 File:
 ```bash
 PIX.postman_collection.json
```
 

## Request
### Create PIX 

```http
POST /pix HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 177

{
    "type": "cpf",
    "value": "11844456378",
    "accountType": "corrente",
    "account": "00000001",
    "agency": "1111",
    "firstName": "Algusto",
    "lastName": ""
}
```

### Get by ID 
```http
GET /pix/1 HTTP/1.1
Host: localhost:8080
```

### Search 
#### Using query params 
```http
GET /pix/search?firstName=Algusto&account=00000001&agency=1111&page=0&size=10 HTTP/1.1
Host: localhost:8080
```

#### Using body
```http
POST /pix/search HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 117

{
    "account": "00000001",
    "agency": "1111",
    "firstName": "Algusto",
    "lastName": "",
    "page":0,
    "size":10

}
```

### Update 

```http
PUT /pix/1 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 130

{
    "accountType": "corrente",
    "account": "00000001",
    "agency": "1111",
    "firstName": "Algusto",
    "lastName": ""
}
``` 
