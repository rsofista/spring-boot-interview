# spring-boot-interview

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup and run](#setup-and-run)
* [Endpoints](#endpoints)
---
* [Contributing](#contributing)
* [History](#history)
* [Credits](#credits)
* [Licence](#license)

## General info
This project is a simple crud of clients and their respective cities. It aims to show the maintener's proficience with the Spring Boot Java Framework.

## Technologies
Project is created with:
* Java: 11
* Spring boot: 2.3.4.RELEASE
* Maven: 3.6.3
	
## Setup and run
To run this project, install it locally using maven:

```
$ mvn install
$ java -jar ./target/spring-boot-interview-0.0.1-SNAPSHOT.jar
```

Or just let your IDE solve the dependencies and run it for you.

## Endpoints

After running the application, a Swagger documentation is generated at http://localhost:8080/swagger-ui.html where you can see all the endpois for adding, updating and deleting stuff.

Every request needs a JWT token, to get one make a POST request to /auth passing the username and password (by default it has the user "lucas" with password "123456")

```
POST /auth HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
    "username": "lucas",
    "password": "123456"
}
```

Once you get the token - something similar to this:

```
{
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJici5jb20uY29tcGFzc28uc3RlZmZlbi5sdWNhcy5zcHJpbmdib290aW50ZXJ2aWV3Iiwic3ViIjoiMSIsImlhdCI6MTYwMzM3Mjc4MiwiZXhwIjoxNjAzMzczNjQyfQ.cP8jmowYpgnDiEblIYMbVw1d_HtZA6vPHerU8hjoGNievvHI363Qdma0FmsYhFYc",
    "type": "Bearer",
    "expiration": 860000
}
```

Use it in the "Authorization" header for the next requests, prefix it with the "type" (most likely "Bearer"), like this:

```
GET /clients HTTP/1.1
Host: localhost:8080
Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJpc3MiOiJici5jb20uY29tcGFzc28uc3RlZmZlbi5sdWNhcy5zcHJpbmdib290aW50ZXJ2aWV3Iiwic3ViIjoiMSIsImlhdCI6MTYwMzM3Mjc4MiwiZXhwIjoxNjAzMzczNjQyfQ.cP8jmowYpgnDiEblIYMbVw1d_HtZA6vPHerU8hjoGNievvHI363Qdma0FmsYhFYc
```

Use the awesomely auto-generated documentation by Swagger to know every endpoint and parameters you can use.

---

## Contributing
 
1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D
 
## History
 
Version 0.0.1-SNAPSHOT (2020-10-22) - defining the main functionalities
 
## Credits
 
Lead Developer - Lucas Steffen

## License
 
The MIT License (MIT)

Copyright (c) 2020 Lucas Steffen

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
