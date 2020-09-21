# Best Commerce 
## Run a local Rabbit MQ server
```
docker run -d --hostname bc-rabbit --name bc-rabbit -p5672:5672 -p15672:15672 rabbitmq:3-management
```

## Run a local Docker registry
```
docker run -d -p 5000:5000 --restart=always --name bc-registry registry:2
```

## Build and package all microservices as docker images
```
./mvnw package
```