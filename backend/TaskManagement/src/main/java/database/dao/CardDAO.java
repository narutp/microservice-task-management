package database.dao;

import java.util.List;

import main.model.Card;

public interface CardDAO {
	
	public void createCard (Card Card);
	
	public List<Card> getAllCard ();
	
	public Card getCardByIdCard(String idCard);
	
	public void editCardByIdCard (String idCard, Card Card);
	
	public void deleteCardByIdCard (String idCard);
	
	public int getCountCard();
	
	public List<Card> getFinishedCardByIdUser(String idUser);
	
	public List<Card> getAllCardByIdUserList(List<String> userList);

	public List<Card> getAllCardByIdUserParticipated(String idUser);
	
	public int getCountAllCardByIdUserList(List<String> idUserList);
}
