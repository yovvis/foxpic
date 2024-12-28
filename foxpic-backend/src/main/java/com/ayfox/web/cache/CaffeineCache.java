package com.ayfox.web.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * 本地缓存
 *
 * @author tenyon
 * @date 2024/12/27
 */
@Configuration
public class CaffeineCache {
    @Bean("localCache")
    public Cache<String, String> localCache() {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(10_000L)
                .expireAfterWrite(Duration.ofMinutes(5))
                .build();
        return cache;
    }
}
