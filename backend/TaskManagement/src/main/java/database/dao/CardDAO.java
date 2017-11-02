package database.dao;

import java.util.List;

import main.model.Card;

public interface CardDAO {
	
	public String createCard (Card Card);
	
	public List<Card> getAllCard ();
	
	public Card getCardByIdCard(String idCard);
	
	public void editCardByIdCard (String idCard, Card Card);
	
	public void deleteCardById (String idCard);
	
	public int getCountCard();
	
	public List<Card> getFinishedCardByIdUser(String idUser);
	
	public List<Card> getAllCardByIdUserList(List<String> userList);

	public List<Card> getAllCardByIdUserParticipated(String idUser);
	
	public int getCountAllCardByIdUserList(List<String> idUserList);
	
	public void deleteAllCard ();
	
	public void addInternalParticipantByIdCard(String idCard, Card card);
	
	public void addExternalParticipantByIdCard(String idCard, Card card);
}
