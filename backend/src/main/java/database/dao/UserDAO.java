package database.dao;

import java.util.List;

import main.model.User;

public interface UserDAO {
	
	public void createUser (User user);
	
	public List<User> getAllUsers ();
	
	public User getUserByName (String name);
	
	public void updateUserByName (String name, User user);
	
	public void deleteUserByName (String name);
	
	public boolean checkLogin(String name, String password);
}
