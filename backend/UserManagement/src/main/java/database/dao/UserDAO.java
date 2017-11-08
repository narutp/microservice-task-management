package database.dao;

import java.util.List;

import main.model.User;

public interface UserDAO {
	
	public void createUser (User user);
	
	public List<User> getAllUser ();
	
	public User getUserById (String id);
	
	public void editUserById (String id, User user);
	
	public void deleteUserById (String name);
	
	public boolean checkLogin (String username, String password);
	
	public boolean isUsernameExist (String username);
	
	public boolean isEmailExist (String email);
	
	public boolean checkPasswordById (String id, String password);
	
	public User getUserByUsername (String username);
	
	public List<String> getIdUserListByDepartmentName (String idDepartment);
	
	public void setManagerByUser (User user);
	
	public List<User> getInternalUserListByIdDepartment (String idDepartment);
	
	public List<User> getExternalUserListByIdDepartment (String idDepartment);
	
	public void deleteAllUser ();
}
