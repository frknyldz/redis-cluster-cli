package com.fyildiz.redis;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * @author Furkan Yildiz
 * @created 27.02.2022
 */
@Command(name = "RedisClusterCli", subcommands = {FlushAll.class, ScanAllKeys.class, GenerateRandomKeys.class, ClusterInfo.class, ClusterNodes.class})
public class RedisClusterCli implements Callable<Integer> {

    public static void main(String[] args) {
        int rc = new CommandLine(new RedisClusterCli()).execute(args);
        System.exit(rc);
    }

    @Override
    public Integer call() throws Exception {
        new CommandLine(new RedisClusterCli()).usage(System.out);
        return 0;
    }
}
