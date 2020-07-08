package reinty.study.seckill.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import reinty.study.seckill.autoconfig.database.DBSource;
import reinty.study.seckill.autoconfig.redis.JedisSource;

@Configuration
@EnableConfigurationProperties({ DBSource.class, JedisSource.class})
@ConditionalOnProperty(prefix = "seckill.autoconfig", value = "enable")
public class ConfigurationStarter {


}
