package database.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;

import database.dao.ProjectCardDAO;
import database.dao.ProjectDAO;
import database.dao.TerminationRequestDAO;
import main.model.Project;
import main.model.TerminationRequest;
import main.model.ProjectCard;

public class MongoDAOImpl implements ProjectCardDAO, ProjectDAO, TerminationRequestDAO {

	private MongoOperations mongoOps;
	private static String collection = MongoDBMain.getProjectCollection();
	public static final MongoClient mongo = MongoDBMain.getMongoClient();

	public MongoDAOImpl(MongoOperations mongoOps) {
		this.mongoOps = mongoOps;
	}

	public MongoDAOImpl() {
		
	}
	
	public void createProject(Project project) {
		collection = MongoDBMain.getProjectCollection();
		System.out.println("DAO: Add new project");
		this.mongoOps.insert(project, collection);
	}
	
	public void createProjectCard(ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		System.out.println("DAO: Add new project card");
		this.mongoOps.insert(projectCard, collection);
	}
	
	public void createTerminationRequest(TerminationRequest terminationRequest) {
		collection = MongoDBMain.getTerminationRequestCollection();
		System.out.println("DAO: Add new termination request");
		this.mongoOps.insert(terminationRequest, collection);
	}
	
	public List<Project> getAllProject() {
		collection = MongoDBMain.getProjectCollection();
		System.out.println("DAO: Get all project");
		return this.mongoOps.findAll(Project.class, collection);
	}
	
	public List<ProjectCard> getAllProjectCard() {
		collection = MongoDBMain.getProjectCardCollection();
		System.out.println("DAO: Get all project card");
		return this.mongoOps.findAll(ProjectCard.class, collection);
	}
	
	public List<TerminationRequest> getAllTerminationRequest() {
		collection = MongoDBMain.getTerminationRequestCollection();
		System.out.println("DAO: Get all termination request");
		return this.mongoOps.findAll(TerminationRequest.class, collection);
	}
	
	public Project getProjectById(String idProject) {
		collection = MongoDBMain.getProjectCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProject").is(idProject));
		System.out.println("DAO: Get project by id: " + idProject);
		return this.mongoOps.findOne(query, Project.class, collection);
	}
	
	@Override
	public Project getProjectByIdProjectCard(String idProjectCard) {
		ProjectCard projectCard = getProjectCardByIdProjectCard(idProjectCard);
		System.out.println("DAO: Get project by id project card: " + idProjectCard);
		return getProjectById(projectCard.getIdProject());
	}
	
	@Override
	public Project getProjectByName(String name) {
		collection = MongoDBMain.getProjectCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		System.out.println("DAO: Get project by name: " + name);
		return this.mongoOps.findOne(query, Project.class, collection);
	}
	
	public ProjectCard getProjectCardByIdProjectCard(String idProjectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		System.out.println("DAO: Get project card by id: " + idProjectCard);
		return this.mongoOps.findOne(query, ProjectCard.class, collection);
	}
	
	public ProjectCard getProjectCardByProjectAndProjectCardName(String projectName, String projectCardName) {
		String idProject = getProjectByName(projectName).getIdProject();
		List<ProjectCard> allProjectCard = getAllProjectCard();
		for (ProjectCard projectCard : allProjectCard) {
			if (projectCard.getName().equals(projectCardName) && projectCard.getIdProject().equals(idProject)) {
				return projectCard;
			}
		}
		System.out.println("DAO: project card by project name and project card name");
		return null;
	}
	
	public TerminationRequest getTerminationRequestByIdTerminationRequest(String idTerminationRequest) {
		collection = MongoDBMain.getTerminationRequestCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idTerminationRequest").is(idTerminationRequest));
		System.out.println("DAO: Get termination request by id: " + idTerminationRequest);
		return this.mongoOps.findOne(query, TerminationRequest.class, collection);
	}
	
	public TerminationRequest getTerminationRequestByProjectAndProjectCardName(String projectName, String projectCardName) {
		Project project = getProjectByName(projectName);
		String idProject = project.getIdProject();
		List<TerminationRequest> allTerminationRequest = getAllTerminationRequest();
		List<TerminationRequest> terminationRequestList = new ArrayList<TerminationRequest>();
		List<ProjectCard> allProjectCard = getAllProjectCard();
		List<ProjectCard> projectCardList = new ArrayList<ProjectCard>();
		for (TerminationRequest terminationRequest : allTerminationRequest) {
			if (terminationRequest.getIdProject().equals(idProject)) {
				terminationRequestList.add(terminationRequest);
			}
		}
		for (ProjectCard projectCard : allProjectCard) {
			if (projectCard.getName().equals(projectCardName)) {
				projectCardList.add(projectCard);
			}
		}
		for (TerminationRequest terminationRequest : terminationRequestList) {
			for (ProjectCard projectCard : projectCardList) {
				if (projectCard.getIdProjectCard().equals(terminationRequest.getIdProjectCard())) {
					return terminationRequest;
				}
			}
		}
		System.out.println("DAO: Get termination request by project name and project card name");
		return null;

	}
	
	public void editProjectById(Project project, String idProject) {
		collection = MongoDBMain.getProjectCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProject").is(idProject));
		Update update = new Update();
		update.set("name", project.getName());
		update.set("description", project.getDescription());
		System.out.println("DAO: edit project by id: " + idProject);
		this.mongoOps.findAndModify(query, update, Project.class, collection);
	}
	
	public void updateProjectCardByIdProjectCard(String idProjectCard, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		Update update = new Update();
		update.set("name", projectCard.getName());
		update.set("description", projectCard.getDescription());
		update.set("endDate", projectCard.getEndDate());
		update.set("internalParticipants", projectCard.getInternalParticipants());
		update.set("externalParticipants", projectCard.getExternalParticipants());
		System.out.println("DAO: Update project card by id: " + idProjectCard);
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}
	
	public List<ProjectCard> getAllProjectCardByIdUserParticipated(String idUser) {
		collection = MongoDBMain.getProjectCardCollection();
		List<ProjectCard> projectCardList = new ArrayList<ProjectCard>();
		List<ProjectCard> allProjectCardList = getAllProjectCard();
		for (ProjectCard projectCard : allProjectCardList) {
			if ((projectCard.getInternalParticipants().contains(idUser) 
					|| projectCard.getExternalParticipants().contains(idUser))
					&& !projectCard.getStatus().equals("Finish")){
				projectCardList.add(projectCard);
			}
		}
		System.out.println("DAO: Get all project card by id user: " + idUser);
		return projectCardList;
	}
	
	public List<TerminationRequest> getAllTerminationRequestByIdUser(String idUser) {
		List<TerminationRequest> allTerminationRequestList = getAllTerminationRequest();
		List<TerminationRequest> terminationRequestList = new ArrayList<TerminationRequest>();
		Project project;
		ProjectCard projectCard;
		for (TerminationRequest terminationRequest : allTerminationRequestList) {
			projectCard = getProjectCardByIdProjectCard(terminationRequest.getIdProjectCard());
			project = getProjectById(projectCard.getIdProject());
			if (project.getIdUser().equals(idUser)) {
				terminationRequestList.add(terminationRequest);

			}
		}
		System.out.println("DAO: Get all termination request by id user: " + idUser);
		return terminationRequestList;
	}
	
	public void deleteTerminationRequestById(String idTerminationRequest) {
		collection = MongoDBMain.getTerminationRequestCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idTerminationRequest").is(idTerminationRequest));
		System.out.println("DAO: Delete termination request by id: " + idTerminationRequest);
		this.mongoOps.remove(query, TerminationRequest.class, collection);
	}
	
	public void approveTerminationRequestById(String idTerminationRequest, String approveDate) {
		TerminationRequest terminationRequest = getTerminationRequestByIdTerminationRequest(idTerminationRequest);
		if (terminationRequest.getType().equals("Request to finish")) {
			ProjectCard projectCard = getProjectCardByIdProjectCard(terminationRequest.getIdProjectCard());
			collection = MongoDBMain.getProjectCardCollection();
			Query query = new Query();
			query.addCriteria(Criteria.where("idProjectCard").is(projectCard.getIdProjectCard()));
			Update update = new Update();
			update.set("status", "Finish");
			update.set("finishedDate", approveDate);
			this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
		} else {
			ProjectCard projectCard = getProjectCardByIdProjectCard(terminationRequest.getIdProjectCard());
			collection = MongoDBMain.getProjectCardCollection();
			Query query = new Query();
			query.addCriteria(Criteria.where("idProjectCard").is(projectCard.getIdProjectCard()));
			this.mongoOps.remove(query, ProjectCard.class, collection);
		}
		System.out.println("DAO: Approve termination request by id: " + idTerminationRequest);
		deleteTerminationRequestById(idTerminationRequest);

	}
	
	public void rejectTerminationRequest(TerminationRequest terminationRequest, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(projectCard.getIdProjectCard()));
		Update update = new Update();
		update.set("status", "In progress");
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
		System.out.println("DAO: Reject termination request");
		deleteTerminationRequestById(terminationRequest.getIdTerminationRequest());
	}
	
	public List<Project> getAllProjectByIdUserList(List<String> idUserList) {
		collection = MongoDBMain.getProjectCollection();
		List<Project> projectList = new ArrayList<Project>();
		List<Project> allProjectList = getAllProject();
		for (String idUser : idUserList) {
			for (Project project : allProjectList) {
				if (project.getIdUser().equals(idUser))
					projectList.add(project);
			}
		}
		System.out.println("DAO: Get all project by id user list");
		return projectList;
	}
	
	public List<ProjectCard> getAllProjectCardByIdProjectList(List<String> idProjectList) {
		collection = MongoDBMain.getProjectCardCollection();
		List<ProjectCard> projectCardList = new ArrayList<ProjectCard>();
		List<ProjectCard> allProjectCardList = getAllProjectCard();
		for (String idProject : idProjectList) {
			for (ProjectCard projectCard : allProjectCardList) {
				if (projectCard.getIdProject().equals(idProject))
					projectCardList.add(projectCard);
			}
		}
		System.out.println("DAO: Get all project card by id project list");
		return projectCardList;
	}
	
	public List<ProjectCard> getAllProjectCardByIdUserList(List<String> userList) {
		List<Project> projectList = getAllProjectByIdUserList(userList);
		List<String> idProjectList = new ArrayList<String>();
		for (Project project : projectList)
			idProjectList.add(project.getIdProject());
		List<ProjectCard> projectCardList = getAllProjectCardByIdProjectList(idProjectList);
		System.out.println("DAO: Get all project card by id user list");
		return projectCardList;
	}
	
	public List<ProjectCard> getFinishProjectCardByIdUser(String idUser) {
		List<ProjectCard> finishedProjectCardList = new ArrayList<ProjectCard>();
		List<ProjectCard> projectCardList = getAllProjectCardByIdUserParticipated(idUser);
		for (ProjectCard projectCard : projectCardList) {
			if (projectCard.getStatus().equals("Finish")) {
				finishedProjectCardList.add(projectCard);
			}
		}
		System.out.println("DAO: Get finish project card by id user: " + idUser);
		return finishedProjectCardList;
	}
	
	@Override
	public void deleteProjectCardById(String idProjectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		boolean idProjectCardExist = false;
		List<ProjectCard> allProjectCard = getAllProjectCard();
		for (ProjectCard projectCard : allProjectCard) {
			if (projectCard.getIdProjectCard().equals(idProjectCard)) {
				idProjectCardExist = true;
				break;
			}
		}
		if (idProjectCardExist) {
			Query query = new Query();
			query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
			this.mongoOps.remove(query, ProjectCard.class, collection);
		}
		System.out.println("DAO: Delete project card by id: " + idProjectCard);
	}
	
	@Override
	public String getIdDepartmentByIdProjectCard(String idProjectCard) {
		Project project = getProjectByIdProjectCard(idProjectCard);
		System.out.println("DAO: Get id department by id project card: " + idProjectCard);
		return project.getIdDepartment();
	}
	
	@Override
	public void addParticipantsByIdProjectCard(String idProjectCard, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		Update update = new Update();
		update.set("internalParticipants", projectCard.getInternalParticipants());
		update.set("externalParticipants", projectCard.getExternalParticipants());
		System.out.println("DAO: Add participants by id project card: " + idProjectCard);
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}
	
	public void requestToFinishProjectCard(ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(projectCard.getIdProjectCard()));
		Update update = new Update();
		update.set("status", projectCard.getStatus());
		update.set("submitReason", projectCard.getSubmitReason());
		update.set("finishDate", projectCard.getFinishDate());
		System.out.println("DAO: Request to finish project card");
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}
	
	@Override
	public void requestToDeleteProjectCard(ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(projectCard.getIdProjectCard()));
		Update update = new Update();
		update.set("status", projectCard.getStatus());
		update.set("submitReason", projectCard.getSubmitReason());
		System.out.println("DAO: Request to delete project card");
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}
	
	@Override
	public boolean isProjectNameExist(String projectName) {
		List<Project> projectList = getAllProject();
		if (projectList == null)
			return false;
		for (Project project : projectList) {
			if (project.getName().equals(projectName)) {
				return true;
			}
		}
		System.out.println("DAO: Check project name is exist: " + projectName);
		return false;
	}
	
	@Override
	public boolean isProjectCardNameExist(String projectCardName, String projectName) {
		Project project = getProjectByName(projectName);
		String idProject = project.getIdProject();
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProject").is(idProject));
		List<ProjectCard> projectCardList = this.mongoOps.find(query, ProjectCard.class, collection);
		if(projectCardList == null)
			return false;
		for(ProjectCard projectCard : projectCardList) {
			if(projectCard.getName().equals(projectCardName)) {
				return true;
			}
		}
		System.out.println("DAO: Check project card name is exist in project: " + projectCardName);
		return false;
	}
	
	@Override
	public List<String> getInternalUserList(String idProjectCard) {
		ProjectCard projectCard = getProjectCardByIdProjectCard(idProjectCard);
		System.out.println("DAO: Get id internal user list by id project card: " + idProjectCard);
		return projectCard.getInternalParticipants();
	}

	@Override
	public List<String> getExternalUserList(String idProjectCard) {
		ProjectCard projectCard = getProjectCardByIdProjectCard(idProjectCard);
		System.out.println("DAO: Get id external user list by id project card: " + idProjectCard);
		return projectCard.getExternalParticipants();
		
	}
	
	public int getCountAllProjectByIdUserList(List<String> idUserList) {
		collection = MongoDBMain.getProjectCollection();
		int projectCounter = 0;
		List<Project> allProjectList = getAllProject();
		for (String idUser : idUserList) {
			for (Project project : allProjectList) {
				if (project.getIdUser().equals(idUser))
					projectCounter++;
			}
		}
		System.out.println("DAO: Get count all project by id user list");
		return projectCounter;
	}
	
	public int getCountAllProjectCardByIdProjectList(List<String> idProjectList) {
		collection = MongoDBMain.getProjectCardCollection();
		int projectCardCounter = 0;
		List<ProjectCard> allProjectCardList = getAllProjectCard();
		for (String idProject : idProjectList) {
			for (ProjectCard projectCard : allProjectCardList) {
				if (projectCard.getIdProject().equals(idProject))
					projectCardCounter++;
			}
		}
		System.out.println("DAO: Get count all project card by id project list");
		return projectCardCounter;
	}
	
	public int getCountAllProjectCardByIdUserList(List<String> userList) {
		List<Project> projectList = getAllProjectByIdUserList(userList);
		List<String> idProjectList = new ArrayList<String>();
		for (Project project : projectList) {
			idProjectList.add(project.getIdProject());
		}
		System.out.println("DAO: Get count all project card by id user list");
		return getCountAllProjectCardByIdProjectList(idProjectList);
	}
	
	@Override
	public int getCountProjectCardByIdProject(String idProject) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProject").is(idProject));
		System.out.println("DAO: Get count project card bu id project: " + idProject);
		return (int) this.mongoOps.count(query, collection);
	}

}
