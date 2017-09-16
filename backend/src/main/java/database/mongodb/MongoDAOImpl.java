
package database.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import database.dao.UserDAO;
import main.model.User;

public class MongoDAOImpl implements UserDAO {
	
	private MongoOperations mongoOps;
	private static String collection = MongoDBMain.getUserCollection();
	public static final MongoClient mongo = MongoDBMain.getMongoClient();
	
	public MongoDAOImpl (MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	public MongoDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createUser(User user) {
		collection = MongoDBMain.getUserCollection();
		System.out.println("DAO: Add new user");
		this.mongoOps.insert(user, collection);
	}

	@Override
	public List<User> getAllUsers() {
		collection = MongoDBMain.getUserCollection();
		return this.mongoOps.findAll(User.class, collection);
	}

	@Override
	public User getUserByName(String name) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return this.mongoOps.findOne(query, User.class, collection);
	}

	@Override
	public void updateUserByName(String name, User user) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		Update update = new Update();
		update.set("name", user.getName());
		update.set("password", user.getPassword());
		this.mongoOps.findAndModify(query, update, User.class, collection);
	}

	@Override
	public void deleteUserByName(String name) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		WriteResult result = this.mongoOps.remove(query, User.class, collection);
	}
	
	@Override
	public boolean checkLogin(String name, String password) {
		List<User> userList = getAllUsers();
		for(User user : userList) {
			System.out.println("Check login: " + user.getName() + ", " + user.getPassword());
			if (name.equals(user.getName()) && password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

}
