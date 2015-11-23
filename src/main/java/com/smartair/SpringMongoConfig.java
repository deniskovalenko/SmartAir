package com.smartair;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class SpringMongoConfig extends AbstractMongoConfiguration {

@Override
public String getDatabaseName() {
    return "co2";
}

    @Override
    @Bean
    public Mongo mongo() throws Exception {
      //  return new MongoClient( new MongoClientURI("mongodb://smartair:xnndxdfkoavg@ds053894.mongolab.com:53894"));
        return new MongoClient();
    }
}