package database.dao;

import java.util.List;

import main.model.UserHistory;

public interface UserHistoryDAO {

	public List<UserHistory> getAllUserHistory();

	public List<UserHistory> getAllUserHistoryByIdUser(String idUser);

	public void createUserHistory(UserHistory userHistory);

	public UserHistory getUserHistoryByIdUser(String idUser);

	public void addIdProjectCard(String idUser, String idProjectCard);

}
