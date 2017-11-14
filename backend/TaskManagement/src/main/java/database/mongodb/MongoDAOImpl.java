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
		return (Integer) (this.mongoOps.getCollection(collection).getStats().get("count"));
	}

	public String createProjectCard(ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		System.out.println("DAO: Add new ProjectCard");
		this.mongoOps.insert(projectCard, collection);
		return projectCard.getIdProjectCard();
	}

	public List<ProjectCard> getAllProjectCard() {
		collection = MongoDBMain.getProjectCardCollection();
		return this.mongoOps.findAll(ProjectCard.class, collection);
	}

	public List<ProjectCard> getAllProjectCardByIdUserParticipated(String idUser) {
		collection = MongoDBMain.getProjectCardCollection();
		List<ProjectCard> projectCardList = new ArrayList<ProjectCard>();
		List<ProjectCard> allProjectCardList = getAllProjectCard();
		for (ProjectCard projectCard : allProjectCardList) {
			if (projectCard.getInternalParticipants().contains(idUser)
					|| projectCard.getExternalParticipants().contains(idUser))
				projectCardList.add(projectCard);
		}
		return projectCardList;
	}

	public ProjectCard getProjectCardByIdProjectCard(String idProjectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		return this.mongoOps.findOne(query, ProjectCard.class, collection);
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
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}

	public int getCountProjectCard() {
		collection = MongoDBMain.getProjectCardCollection();
		return (Integer) (this.mongoOps.getCollection(collection).getStats().get("count"));
	}

	public TerminationRequest getTerminationRequestByIdTerminationRequest(String idTerminationRequest) {
		collection = MongoDBMain.getTerminationRequestCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idTerminationRequest").is(idTerminationRequest));
		return this.mongoOps.findOne(query, TerminationRequest.class, collection);
	}

	public List<TerminationRequest> getAllTerminationRequest() {
		collection = MongoDBMain.getTerminationRequestCollection();
		return this.mongoOps.findAll(TerminationRequest.class, collection);
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
		return terminationRequestList;
	}

	public void deleteTerminationRequestById(String idTerminationRequest) {
		collection = MongoDBMain.getTerminationRequestCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idTerminationRequest").is(idTerminationRequest));
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

		deleteTerminationRequestById(idTerminationRequest);

	}

	public TerminationRequest getTerminationRequestById(String idTerminationReuqest) {
		collection = MongoDBMain.getTerminationRequestCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idTerminationReuqest").is(idTerminationReuqest));
		return this.mongoOps.findOne(query, TerminationRequest.class, collection);
	}

	public void rejectTerminationRequestById(TerminationRequest terminationRequest, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(projectCard.getIdProjectCard()));
		Update update = new Update();
		update.set("status", "In progress");
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
		deleteTerminationRequestById(terminationRequest.getIdTerminationRequest());
	}

	public List<Project> getAllProjectByIdUserList(List<String> idUserList) {
		collection = MongoDBMain.getProjectCollection();
		List<Project> projectList = new ArrayList<Project>();
		List<Project> allProjectList = getAllProject();
		for (String idUser : idUserList) {
			System.out.println("ID USER : " + idUser);
			for (Project project : allProjectList) {
				if (project.getIdUser().equals(idUser))
					projectList.add(project);
			}
		}
		return projectList;
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
		return projectCounter;
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
		return projectCardList;
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
		return projectCardCounter;
	}

	public List<ProjectCard> getAllProjectCardByIdUserList(List<String> userList) {
		System.out.println(userList);
		List<Project> projectList = getAllProjectByIdUserList(userList);
		List<String> idProjectList = new ArrayList<String>();
		for (Project project : projectList)
			idProjectList.add(project.getIdProject());
		List<ProjectCard> projectCardList = getAllProjectCardByIdProjectList(idProjectList);
		return projectCardList;
	}

	public int getCountAllProjectCardByIdUserList(List<String> userList) {
		List<Project> projectList = getAllProjectByIdUserList(userList);
		List<String> idProjectList = new ArrayList<String>();
		for (Project project : projectList)
			idProjectList.add(project.getIdProject());
		return getCountAllProjectCardByIdProjectList(idProjectList);
	}

	public List<ProjectCard> getFinishProjectCardByIdUser(String idUser) {
		List<ProjectCard> finishedProjectCardList = new ArrayList<ProjectCard>();
		List<ProjectCard> projectCardList = getAllProjectCardByIdUserParticipated(idUser);
		for (ProjectCard projectCard : projectCardList) {
			if (projectCard.getStatus().equals("Finish"))
				finishedProjectCardList.add(projectCard);
		}
		return finishedProjectCardList;
	}

	public void createTerminationRequest(TerminationRequest terminationRequest) {
		collection = MongoDBMain.getTerminationRequestCollection();
		System.out.println("DAO: Add new Request");
		this.mongoOps.insert(terminationRequest, collection);
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
	}

	@Override
	public void deleteAllProjectCard() {
		collection = MongoDBMain.getProjectCardCollection();
		this.mongoOps.remove(new Query(), collection);
	}

	@Override
	public Project getProjectByIdProjectCard(String idProjectCard) {
		ProjectCard projectCard = getProjectCardByIdProjectCard(idProjectCard);
		System.out.println(idProjectCard);
		return getProjectById(projectCard.getIdProject());
	}

	@Override
	public void addInternalParticipantByIdProjectCard(String idProjectCard, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		Update update = new Update();
		update.set("internalParticipants", projectCard.getInternalParticipants());
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}

	@Override
	public void addExternalParticipantByIdProjectCard(String idProjectCard, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		Update update = new Update();
		update.set("externalParticipants", projectCard.getExternalParticipants());
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}

	@Override
	public String getIdDepartmentByIdProjectCard(String idProjectCard) {
		Project project = getProjectByIdProjectCard(idProjectCard);
		return project.getIdDepartment();
	}

	@Override
	public Project getProjectByName(String name) {
		collection = MongoDBMain.getProjectCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return this.mongoOps.findOne(query, Project.class, collection);
	}

	@Override
	public void addParticipantsByIdProjectCard(String idProjectCard, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		Update update = new Update();
		update.set("internalParticipants", projectCard.getInternalParticipants());
		update.set("externalParticipants", projectCard.getExternalParticipants());
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}

	@Override
	public void setFinish(String idProjectCard, ProjectCard projectCard) {
		collection = MongoDBMain.getProjectCardCollection();
		Query query = new Query();
		query.addCriteria(Criteria.where("idProjectCard").is(idProjectCard));
		Update update = new Update();
		update.set("status", projectCard.getStatus());
		update.set("finishDate", projectCard.getFinishDate());
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
		this.mongoOps.findAndModify(query, update, ProjectCard.class, collection);
	}

	public TerminationRequest getTerminationRequestByProjectAndProjectCardName(String projectName,
			String projectCardName) {
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
		return null;

	}

	public ProjectCard getProjectCardByProjectAndProjectCardName(String projectName, String projectCardName) {
		String idProject = getProjectByName(projectName).getIdProject();
		List<ProjectCard> allProjectCard = getAllProjectCard();
		for (ProjectCard projectCard : allProjectCard) {
			if (projectCard.getName().equals(projectCardName) && projectCard.getIdProject().equals(idProject)) {
				return projectCard;
			}
		}
		return null;
	}

	public void deleteAllTerminationRequest() {
		collection = MongoDBMain.getTerminationRequestCollection();
		this.mongoOps.remove(new Query(), collection);
	}

}
