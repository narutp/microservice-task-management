package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import database.dao.DatabaseModel;
import database.mongodb.MongoDBMain;

@SpringBootApplication
public class Application {
	public static DatabaseModel database;
	public static void main(String[] args) {
		System.out.println("Starting Spring Application...");
		
		database = new MongoDBMain();
		database.run();

		SpringApplication.run(Application.class, args);
	}
}