# java-simple-crud
Simple RESTful API using Java Spring Boot, Spring JPA, PostgreSQL

### Run application with Docker
```shell
cd simpleapi
```
```shell
docker-compose -f docker-compose.yml up -d --build
```
```shell
[+] Building 51.6s (10/10) FINISHED                                                                                                             docker:orbstack
 => [simple-api internal] load build definition from Dockerfile                                                                                            0.0s
 => => transferring dockerfile: 216B                                                                                                                       0.0s
 => [simple-api internal] load metadata for docker.io/library/openjdk:24-slim                                                                              0.0s
 => [simple-api internal] load .dockerignore                                                                                                               0.0s
 => => transferring context: 2B                                                                                                                            0.0s
 => CACHED [simple-api 1/5] FROM docker.io/library/openjdk:24-slim                                                                                         0.0s
 => [simple-api internal] load build context                                                                                                               0.0s
 => => transferring context: 5.58kB                                                                                                                        0.0s
 => [simple-api 2/5] COPY . /app                                                                                                                           0.1s
 => [simple-api 3/5] WORKDIR /app                                                                                                                          0.1s
 => [simple-api 4/5] RUN ./mvnw clean package -DskipTests                                                                                                 50.8s
 => [simple-api 5/5] COPY target/*.jar app.jar                                                                                                             0.1s
 => [simple-api] exporting to image                                                                                                                        0.3s
 => => exporting layers                                                                                                                                    0.3s
 => => writing image sha256:766a9c129c7d0e65f65a912586c9293d185a7190e6ca88d6f6309c627b5d48b9                                                               0.0s
 => => naming to docker.io/library/simpleapi-simple-api                                                                                                    0.0s
[+] Running 2/2
 ✔ Container simple-api-db  Healthy                                                                                                                        0.6s
 ✔ Container simple-api     Started
```

### Swagger UI
```text
http://localhost:8082/swagger-ui/index.html
```