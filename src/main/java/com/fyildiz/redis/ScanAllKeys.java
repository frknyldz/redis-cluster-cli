package com.fyildiz.redis;

import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "scanAllKeys")
public class ScanAllKeys implements Callable<Integer> {

    private static final Logger logger = LogManager.getLogger(ScanAllKeys.class);

    @Option(names = {"-h", "--host"})
    String host = "localhost";

    @Option(names = {"-p", "--port"})
    Integer port = 6379;

    @Override
    public Integer call() throws Exception {
        RedisURI redisUri = RedisURI.Builder.redis(host, port)
                                            .build();

        RedisClusterClient clusterClient = RedisClusterClient.create(redisUri);
        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();

        KeyScanCursor<String> keyScanCursor = syncCommands.scan();
        while (!keyScanCursor.isFinished()) {
            keyScanCursor.getKeys()
                         .forEach(i -> {
                             String val = syncCommands.get(i);
                             logger.info(String.format("key : %s value : %s", i, val));
                         });
            keyScanCursor = syncCommands.scan(keyScanCursor);

        }

        connection.close();
        clusterClient.shutdown();
        return 0;
    }
}
