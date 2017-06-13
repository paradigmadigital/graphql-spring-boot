package com.paradigma.graphql.fongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

@Configuration
public class FongoConfiguration extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "cars";
    }

    @Bean
    @Override
    public Mongo mongo() {
        return new Fongo("mongo-test").getMongo();
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.paradigma";
    }
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertiesResolver() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}