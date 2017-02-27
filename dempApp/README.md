To build tuningForkDemoApp you need to install:
- Java 1.8
- maven
- Docker
- Docker Compose

Run the build:

```mvn clean install```

Will build docker image tuningfork/demoapp:latest
If you want to create a specific version:

```mvn clean install -Ddocker.image.tag=<your version>```

To run tuningForkDemoApp:

```export JAVA_OPTS=<your Java Opts>```
```export VERSION=<latest or your version>``` 
```docker-compose up -d```
