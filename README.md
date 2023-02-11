# Itau - PIX Case
[![codecov](https://codecov.io/gh/greijal/pix/branch/master/graph/badge.svg?token=J9ABG5PDA1)](https://codecov.io/gh/greijal/pix) 
![GitHub repo file count](https://img.shields.io/github/directory-file-count/greijal/pix)
![GitHub top language](https://img.shields.io/github/languages/top/greijal/pix)


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


