package jsu.lmh.project1.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisInfo {

    private String host;

    private Integer port;

    private Integer timeout;
}