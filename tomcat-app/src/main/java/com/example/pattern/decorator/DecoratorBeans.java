package com.example.pattern.decorator;

import com.example.pattern.adapter.FindTaskByIdSpi;
import com.example.pattern.adapter.FindTaskByIdSpiMappingSqlQuery;
import org.springframework.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DecoratorBeans {

    @Bean
    public FindTaskByIdSpi findTaskByIdSpi(DataSource dataSource, Cache cache) {
        return new SpringCachingFindTaskByIdDecorator(new FindTaskByIdSpiMappingSqlQuery(dataSource), cache);
    }
}
