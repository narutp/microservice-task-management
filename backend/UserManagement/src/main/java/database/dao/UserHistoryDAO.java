package database.dao;

import java.util.List;

import main.model.UserHistory;

public interface UserHistoryDAO {

	public List<UserHistory> getAllUserHistory();

	public void createUserHistory(UserHistory userHistory);

	public UserHistory getUserHistoryByIdUser(String idUser);

	public void addIdProjectCard(String idUser, String idProjectCard);

	public List<String> getIdProjectCardList(String idUser);

}
