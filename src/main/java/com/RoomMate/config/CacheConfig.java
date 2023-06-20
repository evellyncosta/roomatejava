package com.RoomMate.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(
                new ConcurrentMapCache("rooms",
                        Caffeine.newBuilder()
                                .maximumSize(500)
                                .expireAfterAccess(180, TimeUnit.SECONDS)
                                .build()
                                .asMap(), false)));
        return manager;
    }

    /*@Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cache = new ConcurrentMapCacheManager("rooms");
        return new ConcurrentMapCacheManager("rooms");
    }*/
}