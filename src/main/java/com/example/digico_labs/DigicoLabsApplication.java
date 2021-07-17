package com.example.digico_labs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan({"com.example.digico_labs.repository."})
@EnableMongoRepositories(value = "com.example.digico_labs.repository")
@EnableTransactionManagement
@EnableMongoAuditing
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class DigicoLabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigicoLabsApplication.class, args);
	}

}
