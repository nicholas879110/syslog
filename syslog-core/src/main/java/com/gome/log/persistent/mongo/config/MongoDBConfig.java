package com.gome.log.persistent.mongo.config;

/**
 * @Description
 * @Author zhangliewei
 * @Date 2016/7/1
 * @Copyright(c) gome inc Gome Co.,LTD
 */
public class MongoDBConfig {

    private String host;
    private int port;

    private boolean autoConnectRetry = true;
    private int connectionsPerHost = 1000;
    private int maxWaitTime = 5000;
    private int socketTimeout = 0;
    private int connectTimeout = 15000;
    private int threadsAllowedToBlockForConnectionMultiplier = 5000;

    public MongoDBConfig() {
    }

    public MongoDBConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public MongoDBConfig(String host, int port, boolean autoConnectRetry, int connectionsPerHost, int maxWaitTime, int socketTimeout, int connectTimeout, int threadsAllowedToBlockForConnectionMultiplier) {
        this.host = host;
        this.port = port;
        this.autoConnectRetry = autoConnectRetry;
        this.connectionsPerHost = connectionsPerHost;
        this.maxWaitTime = maxWaitTime;
        this.socketTimeout = socketTimeout;
        this.connectTimeout = connectTimeout;
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isAutoConnectRetry() {
        return autoConnectRetry;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry) {
        this.autoConnectRetry = autoConnectRetry;
    }

    public int getConnectionsPerHost() {
        return connectionsPerHost;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }
}
