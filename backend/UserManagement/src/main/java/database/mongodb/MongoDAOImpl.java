
package database.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import database.dao.DepartmentDAO;
import database.dao.PositionDAO;
import database.dao.UserDAO;
import database.dao.UserHistoryDAO;
import main.model.Department;
import main.model.Position;
import main.model.User;
import main.model.UserHistory;

public class MongoDAOImpl implements UserDAO, DepartmentDAO, PositionDAO, UserHistoryDAO {

	private MongoOperations mongoOps;
	private static String collection = MongoDBMain.getUserCollection();
	public static final MongoClient mongo = MongoDBMain.getMongoClient();

	public MongoDAOImpl(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;

//		String[] departmentArr = { "A", "B", "C", "D" };
//		String[] positionArr = { "Intern", "Professor", "Student" };
//		List<Department> departmentList = getAllDepartment();
//		List<Position> positionList = getAllPosition();
//		if (departmentList.size() != 4) {
//			for (int i = 0; i < departmentArr.length; i++) {
//				Department department = new Department();
//				department.setName(departmentArr[i]);
//				createDepartment(department);
//			}
//		}
//
//		if (positionList.size() != 3) {
//			for (int i = 0; i < positionArr.length; i++) {
//				Position position = new Position();
//				position.setName(positionArr[i]);
//				createPosition(position);
//			}
//		}
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
	public List<User> getAllUser() {
		System.out.println("ALL");
		collection = MongoDBMain.getUserCollection();
		return this.mongoOps.findAll(User.class, collection);
	}

	@Override
	public User getUserById(String id) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		System.out.println(id);
		query.addCriteria(Criteria.where("idUser").is(id));
		return this.mongoOps.findOne(query, User.class, collection);
	}

	@Override
	public User getUserByName(String name) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return this.mongoOps.findOne(query, User.class, collection);
	}

	@Override
	public void editUserById(String id, User user) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idUser").is(id));
		Update update = new Update();
		update.set("name", user.getName());
		update.set("birthday", user.getBirthdate());
		update.set("mobilePhone", user.getMobilePhone());
		update.set("idDepartment", user.getIdDepartment());
		update.set("idPosition", user.getIdPosition());
		update.set("email", user.getEmail());
		if (!user.getPassword().equals(""))
			update.set("password", user.getPassword());
		this.mongoOps.findAndModify(query, update, User.class, collection);
	}

	@Override
	public void deleteUserById(String id) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idUser").is(id));
		WriteResult result = this.mongoOps.remove(query, User.class, collection);
	}

	@Override
	public boolean checkLogin(String username, String password) {
		List<User> userList = getAllUser();
		for (User user : userList) {
			System.out.println("Check login: " + user.getUsername() + ", " + user.getPassword());
			if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isUsernameExist(String username) {
		List<User> userList = getAllUser();
		if (userList == null)
			return false;
		for (User user : userList) {
			System.out.println("Check Username: " + user.getUsername());
			if (username.equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isEmailExist(String email) {
		List<User> userList = getAllUser();
		if (userList == null)
			return false;
		for (User user : userList) {
			System.out.println("Check email: " + user.getEmail());
			if (email.equals(user.getEmail())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Position getPositionByName(String name) {
		collection = MongoDBMain.getPositionCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return this.mongoOps.findOne(query, Position.class, collection);
	}

	@Override
	public Department getDepartmentByName(String name) {
		collection = MongoDBMain.getDepartmentCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return this.mongoOps.findOne(query, Department.class, collection);
	}

	@Override
	public List<Position> getAllPosition() {
		collection = MongoDBMain.getPositionCollection();
		return this.mongoOps.findAll(Position.class, collection);
	}

	@Override
	public List<Department> getAllDepartment() {
		collection = MongoDBMain.getDepartmentCollection();
		return this.mongoOps.findAll(Department.class, collection);
	}

	@Override
	public boolean checkPasswordById(String id, String password) {
		collection = MongoDBMain.getUserCollection();
		User user = getUserById(id);
		return user.getPassword().equals(password);
	}

	@Override
	public void createPosition(Position position) {
		collection = MongoDBMain.getPositionCollection();
		System.out.println("DAO: Add new position");
		this.mongoOps.insert(position, collection);
	}

	@Override
	public void createDepartment(Department department) {
		collection = MongoDBMain.getDepartmentCollection();
		System.out.println("DAO: Add new department");
		this.mongoOps.insert(department, collection);
	}

	@Override
	public void deleteDepartment(String name) {
		collection = MongoDBMain.getDepartmentCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		WriteResult result = this.mongoOps.remove(query, Department.class, collection);
	}

	@Override
	public void deletePosition(String name) {
		collection = MongoDBMain.getPositionCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		WriteResult result = this.mongoOps.remove(query, Position.class, collection);
	}

	@Override
	public List<UserHistory> getAllUserHistory() {
		collection = MongoDBMain.getUserHistoryCollection();
		return this.mongoOps.findAll(UserHistory.class, collection);
	}

	@Override
	public List<UserHistory> getAllUserHistoryByIdUser(String idUser) {
		collection = MongoDBMain.getUserHistoryCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idUser").is(idUser));
		return this.mongoOps.find(query, UserHistory.class, collection);
	}

	@Override
	public User getUserByUsername(String username) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		return this.mongoOps.findOne(query, User.class, collection);
	}

	@Override
	public List<String> getIdUserListByDepartmentName(String departmentName) {
		String idDepartment = getDepartmentByName(departmentName).getIdDepartment();
		List<User> allUser = getAllUser();
		List<String> idUserList = new ArrayList<String>();
		for (User user : allUser) {
			if (user.getIdDepartment().equals(idDepartment))
				idUserList.add(user.getIdUser());
		}
		return idUserList;
	}

	@Override
	public void setManagerByUser(User user) {
		collection = MongoDBMain.getUserCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(user.getUsername()));
		Update update = new Update();
		update.set("taskAuthority", true);
		this.mongoOps.findAndModify(query, update, User.class, collection);
	}

	@Override
	public List<User> getInternalUserListByIdDepartment(String idDepartment) {
		System.out.println("ID : " + idDepartment);
		List<User> allUser = getAllUser();
		List<User> userList = new ArrayList<User>();
		for (User user : allUser) {
			System.out.println(user.getIdDepartment());
			if (user.getIdDepartment().equals(idDepartment)) {
				System.out.println("1");
				userList.add(user);
			}
		}
		return userList;
	}

	@Override
	public List<User> getExternalUserListByIdDepartment(String idDepartment) {
		List<User> allUser = getAllUser();
		List<User> userList = new ArrayList<User>();
		for (User user : allUser) {
			if (!user.getIdDepartment().equals(idDepartment))
				userList.add(user);
		}
		return userList;
	}

	@Override
	public Position getPositionById(String idPosition) {
		collection = MongoDBMain.getPositionCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idPosition").is(idPosition));
		return this.mongoOps.findOne(query, Position.class, collection);
	}

	@Override
	public Department getDepartmentById(String idDepartment) {
		collection = MongoDBMain.getDepartmentCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idDepartment").is(idDepartment));
		return this.mongoOps.findOne(query, Department.class, collection);
	}

	@Override
	public void deleteAllPosition() {
		collection = MongoDBMain.getPositionCollection();
		this.mongoOps.remove(new Query(), collection);
	}

	@Override
	public void deleteAllDepartment() {
		collection = MongoDBMain.getDepartmentCollection();
		this.mongoOps.remove(new Query(), collection);
	}

	@Override
	public void deleteAllUser() {
		collection = MongoDBMain.getUserCollection();
		this.mongoOps.remove(new Query(), collection);
	}

	@Override
	public List<String> getIdUserListByNameList(List<String> nameList) {
		collection = MongoDBMain.getUserCollection();
		List<String> idList = new ArrayList<String>();
		for (String name : nameList) {
			System.out.println(name);
			idList.add(getUserByName(name).getIdUser());
		}
		return idList;
	}

	@Override
	public void createUserHistory(UserHistory userHistory) {
		collection = MongoDBMain.getUserHistoryCollection();
		this.mongoOps.insert(userHistory, collection);
	}

	@Override
	public UserHistory getUserHistoryByIdUser(String idUser) {
		collection = MongoDBMain.getUserHistoryCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idUser").is(idUser));
		return this.mongoOps.findOne(query, UserHistory.class, collection);
	}

	@Override
	public void addIdProjectCard(String idUser, String idProjectCard) {
		System.out.println(idUser);
		UserHistory userHistory = getUserHistoryByIdUser(idUser);
		List<String> list = userHistory.getIdProjectCards();
		list.add(idProjectCard);
		userHistory.setIdProjectCards(list);

		collection = MongoDBMain.getUserHistoryCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idUser").is(idUser));
		Update update = new Update();
		update.set("idProjectCards", userHistory.getIdProjectCards());
		this.mongoOps.findAndModify(query, update, UserHistory.class, collection);
	}

	@Override
	public int getCountPositionInDepartmentByName(String departmentName, String positionName) {
		String idDepartment = getDepartmentByName(departmentName).getIdDepartment();
		String idPosition = getPositionByName(positionName).getIdPosition();
		List<User> userList = getAllUser();
		int counter = 0;
		for(User user : userList) {
			if(user.getIdDepartment().equals(idDepartment) && user.getIdPosition().equals(idPosition)) {
				counter++;
			}
		}
		return counter;
		
	}

	@Override
	public List<String> getIdProjectCardList(String idUser) {
		UserHistory userHistory = getUserHistoryByIdUser(idUser);
		return userHistory.getIdProjectCards();
	}

}
