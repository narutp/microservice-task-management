package database.dao;

import java.util.List;

import main.model.UserHistory;

public interface UserHistoryDAO {
	
	public List<UserHistory> getAllUserHistory();
	
	public List<UserHistory> getAllUserHistoryByIdUser(String idUser);
	
	
}
