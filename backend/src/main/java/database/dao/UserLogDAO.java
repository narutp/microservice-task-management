package database.dao;

import java.util.List;

import main.model.UserLog;

public interface UserLogDAO {
	
	public List<UserLog> getAllUserLog();
	
	public List<UserLog> getAllUserLogByUserId(String userId);
	
	
}
