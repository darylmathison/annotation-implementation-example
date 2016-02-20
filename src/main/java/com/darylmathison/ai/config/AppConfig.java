package com.darylmathison.ai.config;

import com.darylmathison.ai.cache.CacheAspect;
import com.darylmathison.ai.service.FibonacciService;
import com.darylmathison.ai.service.FibonacciServiceImpl;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daryl on 2/20/2016.
 */
@Configuration
@ComponentScan(basePackages = "com.darylmathison.ai")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public Map<String, Object> cache() {
        Config config = new Config();
        MapConfig mapConfig = new MapConfig();
        mapConfig.setEvictionPercentage(50);
        mapConfig.setEvictionPolicy(EvictionPolicy.LFU);
        mapConfig.setTimeToLiveSeconds(300);
        Map<String, MapConfig> mapConfigMap = new HashMap<>();
        mapConfigMap.put("cache", mapConfig);
        config.setMapConfigs(mapConfigMap);

        HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);
        return instance.getMap("cache");
    }

    @Bean
    public FibonacciService fibonacci() {
        return new FibonacciServiceImpl();
    }

    @Bean
    public CacheAspect cacheAspect() {
        return new CacheAspect();
    }
}
