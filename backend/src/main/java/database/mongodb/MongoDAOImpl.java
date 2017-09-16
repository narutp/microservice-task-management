
package database.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.MongoClient;

public class MongoDAOImpl {
	
	private MongoOperations mongoOps;
	private static String collection = MongoDBMain.getUserCollection();
	public static final MongoClient mongo = MongoDBMain.getMongoClient();
	
	public MongoDAOImpl (MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	public MongoDAOImpl() {
		// TODO Auto-generated constructor stub
	}

}
