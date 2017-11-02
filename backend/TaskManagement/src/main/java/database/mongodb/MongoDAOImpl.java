package database.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;

import database.dao.CardDAO;
import database.dao.ProjectDAO;
import database.dao.RequestDAO;
import main.model.Project;
import main.model.Request;
import main.model.Card;

public class MongoDAOImpl implements CardDAO, ProjectDAO, RequestDAO{
	
	private MongoOperations mongoOps;
	private static String collection = MongoDBMain.getProjectCollection();
	public static final MongoClient mongo = MongoDBMain.getMongoClient();
	
	public MongoDAOImpl (MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}
	
	public MongoDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Project> getAllProject() {
		collection = MongoDBMain.getProjectCollection();
		return this.mongoOps.findAll(Project.class, collection);
	}
	
	public Project getProjectById(String idProject) {
		collection = MongoDBMain.getProjectCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProject").is(idProject));
		return this.mongoOps.findOne(query, Project.class, collection);
	}

	public void createProject(Project project) {
		collection = MongoDBMain.getProjectCollection();
		System.out.println("DAO: Add new project");
		this.mongoOps.insert(project, collection);
	}

	public void editProjectById(Project project, String idProject) {
		collection = MongoDBMain.getProjectCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProject").is(idProject));
		Update update = new Update();
		update.set("name", project.getName());
		update.set("description", project.getDescription());
		this.mongoOps.findAndModify(query, update, Project.class, collection);
	}

	public int getCountProject() {
		collection = MongoDBMain.getProjectCollection();
		return (Integer)(this.mongoOps.getCollection(collection).getStats().get("count"));
	}

	public void createCard(Card card) {
		collection = MongoDBMain.getCardCollection();
		System.out.println("DAO: Add new Card");
		this.mongoOps.insert(card, collection);
	}

	public List<Card> getAllCard() {
		collection = MongoDBMain.getCardCollection();
		return this.mongoOps.findAll(Card.class, collection);
	}

	public List<Card> getAllCardByIdUserParticipated(String idUser) {
		collection = MongoDBMain.getCardCollection();
		List<Card> cardList = new ArrayList<Card>();
		List<Card> allCardList = getAllCard();
		for(Card card : allCardList) {
			if(card.getInternalParticipants().contains(idUser) || card.getExternalParticipants().contains(idUser))
				cardList.add(card);
		}
		return cardList;
	}
	
	public Card getCardByIdCard(String idCard) {
		collection = MongoDBMain.getCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idCard").is(idCard));
		return this.mongoOps.findOne(query, Card.class, collection);
	}

	public void editCardByIdCard(String idCard, Card card) {
		collection = MongoDBMain.getCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idCard").is(idCard));
		Update update = new Update();
		update.set("status", card.getStatus());
		update.set("name",card.getName());
		update.set("description",card.getDescription());
		update.set("startDate",card.getStartDate());
		update.set("endDate",card.getEndDate());
		update.set("registeredDate", card.getRegisteredDate());
		update.set("internalParticipants", card.getInternalParticipants());
		update.set("externalParticipants", card.getExternalParticipants());
		update.set("submitReason", card.getSubmitReason());
		update.set("finishedDate", card.getFinishedDate());
		this.mongoOps.findAndModify(query, update, Card.class, collection);
	}

	public int getCountCard() {
		collection = MongoDBMain.getCardCollection();
		return (Integer)(this.mongoOps.getCollection(collection).getStats().get("count"));
	}
	
	public Request getRequestByIdRequest(String idRequest) {
		collection = MongoDBMain.getRequestCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idRequest").is(idRequest));
		return this.mongoOps.findOne(query, Request.class, collection);
	}
	
	public List<Request> getAllRequest() {
		collection = MongoDBMain.getRequestCollection();
		return this.mongoOps.findAll(Request.class, collection);
	}

	public List<Request> getAllRequestByIdUser(String idUser) {
		List<Request> allRequestList = getAllRequest();
		List<Request> requestList = new ArrayList<Request>();
		Project project;
		Card card;
		for(Request request : allRequestList) {
			card = getCardByIdCard(request.getIdCard());
			project = getProjectById(card.getIdProject());
			if(project.getIdUser().equals(idUser)) {
				requestList.add(request);
				
			}
		}
		return requestList;
	}
	
	public void deleteRequestById(String idRequest) {
		collection = MongoDBMain.getRequestCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idRequest").is(idRequest));
		this.mongoOps.remove(query, Request.class, collection);
	}

	public void approveRequestById(String idRequest, String approveDate) {
		Request request = getRequestByIdRequest(idRequest);
		if(request.getType().equals("finish")) {
			Card card = getCardByIdCard(request.getIdCard());
			collection = MongoDBMain.getCardCollection();
			Query query = new Query();
			query.addCriteria(Criteria.where("idCard").is(card.getIdCard()));
			Update update = new Update();
			update.set("status", "Done");
			update.set("name",card.getName());
			update.set("description",card.getDescription());
			update.set("startDate",card.getStartDate());
			update.set("endDate",card.getEndDate());
			update.set("registeredDate", card.getRegisteredDate());
			update.set("internalParticipants", card.getInternalParticipants());
			update.set("externalParticipants", card.getExternalParticipants());
			update.set("submitReason", card.getSubmitReason());
			update.set("finishedDate", approveDate);
			this.mongoOps.findAndModify(query, update, Card.class, collection);
		} 
		else {
			Card card = getCardByIdCard(request.getIdCard());
			collection = MongoDBMain.getCardCollection();
			Query query = new Query();
			query.addCriteria(Criteria.where("idCard").is(card.getIdCard()));
			this.mongoOps.remove(query, Card.class, collection);
		}
		
		deleteRequestById(idRequest);
		
	}

	public void rejectRequestById(String idRequest) {
		deleteRequestById(idRequest);
	}

	public List<Project> getAllProjectByIdUserList(List<String> idUserList) {
		collection = MongoDBMain.getProjectCollection();
		List<Project> projectList = new ArrayList<Project>();
		List<Project> allProjectList = getAllProject();
		for(String idUser : idUserList) {
			for(Project project : allProjectList) {
				if(project.getIdUser().equals(idUser)) 
					projectList.add(project);
			}
		}
		return projectList;
	}
	
	public int getCountAllProjectByIdUserList(List<String> idUserList) {
		collection = MongoDBMain.getProjectCollection();
		int projectCounter = 0;
		List<Project> allProjectList = getAllProject();
		for(String idUser : idUserList) {
			for(Project project : allProjectList) {
				if(project.getIdUser().equals(idUser)) 
					projectCounter++;
			}
		}
		return projectCounter;
	}
	
	public List<Card> getAllCardByIdProjectList(List<String> idProjectList) {
		collection = MongoDBMain.getCardCollection();
		List<Card> cardList = new ArrayList<Card>();
		List<Card> allCardList = getAllCard();
		for(String idProject : idProjectList) {
			for(Card card : allCardList) {
				if(card.getIdProject().equals(idProject)) 
					cardList.add(card);
			}
		}
		return cardList;
	}
	
	public int getCountAllCardByIdProjectList(List<String> idProjectList) {
		collection = MongoDBMain.getCardCollection();
		int cardCounter = 0;
		List<Card> allCardList = getAllCard();
		for(String idProject : idProjectList) {
			for(Card card : allCardList) {
				if(card.getIdProject().equals(idProject)) 
					cardCounter++;
			}
		}
		return cardCounter;
	}
	
	public List<Card> getAllCardByIdUserList(List<String> userList) {
		List<Project> projectList = getAllProjectByIdUserList(userList);
		List<String> idProjectList = new ArrayList<String>();
		for(Project project : projectList)
			idProjectList.add(project.getIdProject());
		List<Card> cardList = getAllCardByIdProjectList(idProjectList);
		return cardList;
	}
	
	public int getCountAllCardByIdUserList(List<String> userList) {
		List<Project> projectList = getAllProjectByIdUserList(userList);
		List<String> idProjectList = new ArrayList<String>();
		for(Project project : projectList)
			idProjectList.add(project.getIdProject());
		return getCountAllCardByIdProjectList(idProjectList);
	}
	
	public List<Card> getFinishedCardByIdUser(String idUser) {
		List<Card> finishedCardList = new ArrayList<Card>();
		List<Card> cardList = getAllCardByIdUserParticipated(idUser);
		for(Card card : cardList) {
			if(card.getStatus().equals("Finish"))
				finishedCardList.add(card);
		}
		return finishedCardList;
	}
	
	public void createRequest(Request request) {
		collection = MongoDBMain.getRequestCollection();
		System.out.println("DAO: Add new Request");
		this.mongoOps.insert(request, collection);
	}

	public Request getRequestInfoByIdUser(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void deleteProjectById(String idProject) {
		collection = MongoDBMain.getProjectCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProject").is(idProject));
		this.mongoOps.remove(query, Project.class, collection);
	}

	@Override
	public void deleteAllProject() {
		collection = MongoDBMain.getProjectCollection();
		this.mongoOps.remove(new Query(), collection);
	}
	
	@Override
	public void deleteCardById(String idCard) {
		collection = MongoDBMain.getCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idCard").is(idCard));
		this.mongoOps.remove(query, Card.class, collection);
	}

	@Override
	public void deleteAllCard() {
		collection = MongoDBMain.getCardCollection();
		this.mongoOps.remove(new Query(), collection);
	}
	
	@Override
	public Project getProjectByIdCard(String idCard) {
		Card card = getCardByIdCard(idCard);
		return getProjectById(card.getIdProject());
	}

	@Override
	public void addInternalParticipantByIdCard(String idCard, Card card) {
		collection = MongoDBMain.getCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idCard").is(idCard));
		Update update = new Update();
		update.set("internalParticipants", card.getInternalParticipants());
		this.mongoOps.findAndModify(query, update, Card.class, collection);
	}

	@Override
	public void addExternalParticipantByIdCard(String idCard, Card card) {
		collection = MongoDBMain.getCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idCard").is(idCard));
		Update update = new Update();
		update.set("externalParticipants", card.getExternalParticipants());
		this.mongoOps.findAndModify(query, update, Card.class, collection);
	}

	@Override
	public String getIdDepartmentByIdCard(String idCard) {
		Project project = getProjectByIdCard(idCard);
		return project.getIdDepartment();
	}


}
