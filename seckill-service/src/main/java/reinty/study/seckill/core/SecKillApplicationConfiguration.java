package reinty.study.seckill.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import reinty.study.seckill.autoconfig.database.DBSource;
import reinty.study.seckill.autoconfig.redis.JedisSource;

@SpringBootApplication
public class SecKillApplicationConfiguration {

    @Autowired
    private DBSource dbSource;

    @Autowired
    private JedisSource jedisSource;

    public static void main(String[] args) {
        SpringApplication.run(SecKillApplicationConfiguration.class);
    }

    @Bean
    public JedisPool jedisPoolFactory() {
        System.out.println("JedisPool注入开始., jedisSource:{}"+jedisSource);
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisSource.getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis( jedisSource.getMaxWaitMillis());
        // 连接耗尽时是否阻塞, false报异常,true阻塞直到超时, 默认true
        jedisPoolConfig.setBlockWhenExhausted( jedisSource.getBlockWhenExhausted());
        // 是否启用pool的jmx管理功能, 默认tru
        jedisPoolConfig.setJmxEnabled( jedisSource.getJmxEnabled());
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, jedisSource.getHost(), jedisSource.getPort(), jedisSource.getTimeout());
        System.out.println("JedisPool注入成功...jedisSource:" +jedisSource );
        return jedisPool;
    }

}
