package reinty.study.seckill.autoconfig.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redis.config")
public class JedisSource {

    private String host;

    private int port;

    private int timeout;

    private int maxIdle;

    private int maxWaitMillis;

    private Boolean blockWhenExhausted;

    private Boolean JmxEnabled;


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

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Boolean getBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(Boolean blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }

    public Boolean getJmxEnabled() {
        return JmxEnabled;
    }

    public void setJmxEnabled(Boolean jmxEnabled) {
        JmxEnabled = jmxEnabled;
    }

    @Override
    public String toString() {
        return "JedisSource{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", timeout=" + timeout +
                ", maxIdle=" + maxIdle +
                ", maxWaitMillis=" + maxWaitMillis +
                ", blockWhenExhausted=" + blockWhenExhausted +
                ", JmxEnabled=" + JmxEnabled +
                '}';
    }
}
