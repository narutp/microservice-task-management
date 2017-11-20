package database.mongodb;

import java.net.UnknownHostException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mongodb.MongoClient;

import database.dao.DatabaseModel;

public class MongoDBMain implements DatabaseModel {

	private static final String DB_NAME = "projectdb";
	private static final String PROJECT_CARD_COLLECTION = "project_card";
	private static final String PROJECT_COLLECTION = "project";
	private static final String TERMINATION_REQUEST_COLLECTION = "termination_request";
	private static final String MONGO_HOST = "localhost";
	private static final int MONGO_PORT = 27017;
	private static MongoClient mongo;
	private ClassPathXmlApplicationContext ctx = null;

	public void run() {
		try {
			System.out.println("connecting to mongodb at " + MONGO_HOST + ":" + MONGO_PORT + "...\ndatabase name: "
					+ DB_NAME + "\ncollection: " + PROJECT_COLLECTION);
			mongo = new MongoClient(MONGO_HOST, MONGO_PORT);
			System.out.println("connected!");

		} catch (UnknownHostException e) {
			System.err.println("Cannot connect to " + MONGO_HOST + ":" + MONGO_PORT);
			e.printStackTrace();
		}
	}

	public static MongoClient getMongoClient() {
		return mongo;
	}

	public static String getDBName() {
		return DB_NAME;
	}

	public static String getProjectCollection() {
		return PROJECT_COLLECTION;
	}

	public static String getProjectCardCollection() {
		return PROJECT_CARD_COLLECTION;
	}

	public static String getTerminationRequestCollection() {
		return TERMINATION_REQUEST_COLLECTION;
	}

	public static String getHost() {
		return MONGO_HOST;
	}

	public static int getPort() {
		return MONGO_PORT;
	}

	public ClassPathXmlApplicationContext getContext() {
		if (ctx == null) {
			ctx = new ClassPathXmlApplicationContext("mongodb/Spring-MongoDB.xml");
		}
		return ctx;
	}

}
