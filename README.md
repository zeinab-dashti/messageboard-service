# Message Board

### Description
This project leverages Spring Boot framework along with Sprint Data. For persistence layer, an integrated H2 database is used.


### How to test and run
This project is Maven based. Assuming to be at the root directory of the project, you can run test suite by the following command:
```
mvn test
```  
You can run the application by the following command:
```
mvn spring-boot:run
```


### API docs (Swagger)
Once the application is up and running, you can check the API spec and try it out by looking into the following address:
```
http://localhost:8080/swagger-ui.html
```


### How to deploy
To deploy this project using Docker, you can run the following command (you may use different port number):
```
docker build -t messageboard -f Dockerfile . && docker run --rm -p 8080:8080 messageboard
```
