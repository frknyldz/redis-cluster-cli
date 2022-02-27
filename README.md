# redis-cluster-cli

## Compile

```shell
mvn clean compile assembly:single
```

## Usage

```
Usage: RedisClusterCli [COMMAND]
Commands:
  flushAll
  scanAllKeys
  generateRandomKeys
  clusterInfo
  clusterNodes
```

## Execute

```shell
java -jar target/redis-cluster-cli-1.0-SNAPSHOT-jar-with-dependencies.jar

java -jar target/redis-cluster-cli-1.0-SNAPSHOT-jar-with-dependencies.jar flushAll -h localhost -p 6379 

java -jar target/redis-cluster-cli-1.0-SNAPSHOT-jar-with-dependencies.jar scanAllKeys 
```

