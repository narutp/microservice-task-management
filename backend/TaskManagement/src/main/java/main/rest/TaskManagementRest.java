package main.rest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import database.dao.ProjectCardDAO;
import database.dao.ProjectDAO;
import database.dao.TerminationRequestDAO;
import main.Application;
import main.model.Project;
import main.model.TerminationRequest;
import main.model.ProjectCard;

@Named
@Path("/")
public class TaskManagementRest {

	private Project project;
	private ProjectCard projectCard;
	private TerminationRequest terminationRequest;
	private ApplicationContext ctx = Application.database.getContext();
	private ProjectDAO projectDAO = ctx.getBean("projectDAO", ProjectDAO.class);
	private ProjectCardDAO projectCardDAO = ctx.getBean("projectCardDAO", ProjectCardDAO.class);
	private TerminationRequestDAO terminationRequestDAO = ctx.getBean("terminationRequestDAO",
			TerminationRequestDAO.class);

	private final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Inject
	private RestTemplate restemplate;

	public TaskManagementRest() {
		this.project = new Project();
		this.projectCard = new ProjectCard();
		this.terminationRequest = new TerminationRequest();
	}

	@POST
	@Path("create/project")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createProjectAPI(@QueryParam("name") String name, @QueryParam("description") String description,
			@QueryParam("idUser") String idUser, @QueryParam("idDepartment") String idDepartment) {
		if (name.equals(""))
			return false;
		else
			project.setName(name);
		if (description.equals(""))
			project.setDescription("-");
		else
			project.setDescription(description);
		project.setIdUser(idUser);

		Date date = new Date();
		date = Calendar.getInstance().getTime();
		String regisDate = DATEFORMAT.format(date);
		project.setRegisteredDate(regisDate);
		project.setIdDepartment(idDepartment);

		projectDAO.createProject(project);
		return true;
	}
	
	@POST
	@Path("create/project-card")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createProjectCardAPI(@QueryParam("idUser") String idUser,
			@QueryParam("projectName") String projectName, @QueryParam("name") String name,
			@QueryParam("description") String description, @QueryParam("startDate") String startDate,
			@QueryParam("endDate") String endDate) {

		if(name.equals(""))
			return false;
		else
			projectCard.setName(name);
		if(projectName.equals(""))
			return false;
		else
			projectCard.setIdProject(projectDAO.getProjectByName(projectName).getIdProject());
		if (description.equals(""))
			projectCard.setDescription("-");
		else
			projectCard.setDescription(description);
		if(startDate.equals(""))
			return false;
		else
			projectCard.setStartDate(startDate);
		if(endDate.equals(""))
			return false;
		else
			projectCard.setEndDate(endDate);
		projectCard.setIdUser(idUser);
		projectCard.setInternalParticipants(new ArrayList<String>());
		projectCard.setExternalParticipants(new ArrayList<String>());
		// date convert to string
		Date date = new Date();
		date = Calendar.getInstance().getTime();
		String regisDate = DATEFORMAT.format(date);
		projectCard.setRegisteredDate(regisDate);
		projectCard.setStatus("In progress");
		projectCard.setSubmitReason("-");
		projectCard.setFinishDate("-");

		projectCardDAO.createProjectCard(projectCard);
		return true;
	}
	
	@GET
	@Path("get/all-project")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllProjectAPI() {
		return projectDAO.getAllProject();
	}
	
	@GET
	@Path("get/all-project-card")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectCard> getAllProjectCardAPI() {
		List<ProjectCard> projectCardList = projectCardDAO.getAllProjectCard();
		return projectCardList;
	}
	
	@GET
	@Path("get/all-termination-request")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TerminationRequest> getAllTerminationRequestAPI() {
		return terminationRequestDAO.getAllTerminationRequest();
	}
	
	@GET
	@Path("get/project")
	@Produces(MediaType.APPLICATION_JSON)
	public Project getProjectByIdProjectAPI(@QueryParam("idProject") String idProject) {
		Project project = projectDAO.getProjectById(idProject);
		return project;
	}
	
	@GET
	@Path("get/project-card/idProjectCard")
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectCard getProjectCardByIdProjectCardAPI(@QueryParam("idProjectCard") String idProjectCard) {
		return projectCardDAO.getProjectCardByIdProjectCard(idProjectCard);
	}

	@GET
	@Path("get/project-card/projectName")
	@Produces(MediaType.APPLICATION_JSON)
	public ProjectCard getProjectCardByProjectAndProjectCardNameAPI(@QueryParam("projectName") String projectName,
			@QueryParam("projectCardName") String projectCardName) {
		return projectCardDAO.getProjectCardByProjectAndProjectCardName(projectName, projectCardName);
	}

	@POST
	@Path("edit/project")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean editProjectAPI(@QueryParam("idProject") String idProject, @QueryParam("name") String name,
			@QueryParam("description") String description) {
		project = projectDAO.getProjectById(idProject);
		if (name.equals(""))
			return false;
		else
			project.setName(name);
		if (description.equals(""))
			project.setDescription("-");
		else
			project.setDescription(description);
		projectDAO.editProjectById(project, idProject);
		return true;
	}
	
	@POST
	@Path("update/project-card")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateProjectCardAPI(@QueryParam("idProjectCard") String idProjectCard, @QueryParam("name") String name,
			@QueryParam("description") String description, @QueryParam("endDate") String endDate,
			@QueryParam("internalParticipants") List<String> internalParticipants,
			@QueryParam("externalParticipants") List<String> externalParticipants) {

		projectCard = projectCardDAO.getProjectCardByIdProjectCard(idProjectCard);
		if (name.equals(""))
			return false;
		else
			projectCard.setName(name);
		if (description.equals(""))
			projectCard.setDescription("-");
		else
			projectCard.setDescription(description);
		if (endDate.equals(""))
			projectCard.setEndDate("-");
		else
			projectCard.setEndDate(endDate);
		if (internalParticipants.equals(null))
			projectCard.setInternalParticipants(null);
		else
			projectCard.setInternalParticipants(internalParticipants);
		if (externalParticipants.equals(null))
			projectCard.setExternalParticipants(null);
		else
			projectCard.setExternalParticipants(externalParticipants);

		projectCardDAO.updateProjectCardByIdProjectCard(idProjectCard, projectCard);
		return true;
	}

	@POST
	@Path("add/participants")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addParticipantsAPI(@QueryParam("idProjectCard") String idProjectCard,
			@QueryParam("idInternalUserList") List<String> idInList,
			@QueryParam("idExternalUserList") List<String> idExList) {

		projectCard = projectCardDAO.getProjectCardByIdProjectCard(idProjectCard);
		if (!idInList.get(0).equals("null")) {
			List<String> tempInUserList = Arrays.asList(idInList.get(0).split("\\s*,\\s*"));
			List<String> idInternalUserList = new ArrayList<String>();
			String temp = "";
			for (String user : tempInUserList) {
				temp = user.replaceAll("[^a-zA-Z0-9]+", "");
				idInternalUserList.add(temp);
			}

			List<String> inList = projectCard.getInternalParticipants();

			boolean exist = false;
			for (String idUser : idInternalUserList) {
				exist = false;
				for (String eachInList : inList) {
					if (eachInList.equals(idUser)) {
						exist = true;
						break;
					}
				}
				if (!exist) {
					inList.add(idUser);
				}
			}

			projectCard.setInternalParticipants(inList);

		}
		if (!idExList.get(0).equals("null")) {
			String temp = "";
			List<String> tempExUserList = Arrays.asList(idExList.get(0).split("\\s*,\\s*"));
			List<String> idExternalUserList = new ArrayList<String>();
			for (String user : tempExUserList) {
				temp = user.replaceAll("[^a-zA-Z0-9]+", "");
				idExternalUserList.add(temp);
			}
			List<String> exList = projectCard.getExternalParticipants();
			boolean exist = false;
			for (String idUser : idExternalUserList) {
				exist = false;
				for (String eachExList : exList) {
					if (eachExList.equals(idUser)) {
						exist = true;
						break;
					}
				}
				if (!exist) {
					exList.add(idUser);
				}
			}

			projectCard.setExternalParticipants(exList);
		}

		projectCardDAO.addParticipantsByIdProjectCard(idProjectCard, projectCard);
		return true;
	}

	@GET
	@Path("get/all-termination-request/idUser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TerminationRequest> getAllTerminationRequestByIdUserAPI(@QueryParam("idUser") String idUser) {
		List<TerminationRequest> terminationRequestList = terminationRequestDAO
				.getAllTerminationRequestByIdUser(idUser);
		return terminationRequestList;
	}

	@POST
	@Path("approve/request")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean approveTerminationRequestAPI(@QueryParam("projectName") String projectName,
			@QueryParam("projectCardName") String projectCardName) {
		Date date = new Date();
		date = Calendar.getInstance().getTime();
		String approveDate = DATEFORMAT.format(date);

		terminationRequest = terminationRequestDAO.getTerminationRequestByProjectAndProjectCardName(projectName,
				projectCardName);
		terminationRequestDAO.approveTerminationRequestById(terminationRequest.getIdTerminationRequest(), approveDate);
		return true;
	}

	@POST
	@Path("reject/request")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean rejectTerminationRequestAPI(@QueryParam("projectName") String projectName,
			@QueryParam("projectCardName") String projectCardName) {
		terminationRequest = terminationRequestDAO.getTerminationRequestByProjectAndProjectCardName(projectName,
				projectCardName);
		projectCard = projectCardDAO.getProjectCardByIdProjectCard(terminationRequest.getIdProjectCard());
		projectCard.setStatus("In progress");
		terminationRequestDAO.rejectTerminationRequest(terminationRequest, projectCard);

		return true;
	}

	@GET
	@Path("get/all-project-card/idUser")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectCard> getAllProjectCardByUserIdAPI(@QueryParam("idUser") String idUser) {
		List<ProjectCard> projectCardList = projectCardDAO.getAllProjectCardByIdUserParticipated(idUser);
		return projectCardList;
	}

	@GET
	@Path("get/finish-project-card")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectCard> getFinishProjectCardByIdUserAPI(@QueryParam("idUser") String idUser) {
		return projectCardDAO.getFinishProjectCardByIdUser(idUser);
	}

	@GET
	@Path("get/department-project-card")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProjectCard> getDepartmentProjectCardByIdUserListAPI(@QueryParam("idUserList") List<String> idUserList) {
		List<String> userList = Arrays.asList(idUserList.get(0).split("\\s*,\\s*"));
		List<String> returnedList = new ArrayList<String>();
		String temp = "";
		for (String user : userList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+", "");
			returnedList.add(temp);
		}
		return projectCardDAO.getAllProjectCardByIdUserList(returnedList);
	}

	@GET
	@Path("get/department-project")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getDepartmentProjectByIdUserListAPI(@QueryParam("idUserList") List<String> idUserList) {
		List<String> userList = Arrays.asList(idUserList.get(0).split("\\s*,\\s*"));
		List<String> returnedList = new ArrayList<String>();
		String temp = "";
		for (String user : userList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+", "");
			returnedList.add(temp);
		}
		return projectDAO.getAllProjectByIdUserList(returnedList);
	}

	@POST
	@Path("request/finish")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean requestToFinishProjectCardAPI(@QueryParam("idProjectCard") String idProjectCard,
			@QueryParam("reason") String reason, @QueryParam("idRequester") String idRequester) {
		projectCard = projectCardDAO.getProjectCardByIdProjectCard(idProjectCard);
		projectCard.setStatus("Request to finish");
		projectCard.setSubmitReason(reason);
		Date date = new Date();
		date = Calendar.getInstance().getTime();
		String requestDate = DATEFORMAT.format(date);
		projectCard.setFinishDate(requestDate);
		projectCardDAO.requestToFinishProjectCard(projectCard);
		terminationRequest.setIdProjectCard(idProjectCard);
		terminationRequest.setType("Request to finish");
		terminationRequest.setIdProject(projectCard.getIdProject());
		terminationRequest.setIdRequester(idRequester);
		terminationRequest.setReason(reason);
		terminationRequest.setDate(requestDate);
		terminationRequestDAO.createTerminationRequest(terminationRequest);
		return true;
	}

	@POST
	@Path("request/delete")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean requestToDeleteProjectCardAPI(@QueryParam("idProjectCard") String idProjectCard,
			@QueryParam("reason") String reason, @QueryParam("idRequester") String idRequester) {
		projectCard = projectCardDAO.getProjectCardByIdProjectCard(idProjectCard);
		projectCard.setStatus("Request to delete");
		projectCard.setSubmitReason(reason);
		projectCardDAO.requestToDeleteProjectCard(projectCard);
		terminationRequest.setIdProjectCard(idProjectCard);
		terminationRequest.setType("Request to delete");
		terminationRequest.setIdProject(projectCard.getIdProject());
		terminationRequest.setIdRequester(idRequester);
		terminationRequest.setReason(reason);
		Date date = new Date();
		date = Calendar.getInstance().getTime();
		String requestDate = DATEFORMAT.format(date);
		terminationRequest.setDate(requestDate);
		terminationRequestDAO.createTerminationRequest(terminationRequest);
		return true;
	}

	@POST
	@Path("delete/project-card/idProjectCard")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteProjectCardByIdAPI(@QueryParam("idProjectCard") String idProjectCard) {
		projectCardDAO.deleteProjectCardById(idProjectCard);
		return true;
	}

	@GET
	@Path("get/idDepartment/project-card")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIdDepartmentByIdProjectCardAPI(@QueryParam("idProjectCard") String idProjectCard) {
		String idDepartment = projectDAO.getIdDepartmentByIdProjectCard(idProjectCard);
		return idDepartment;
	}
	
	@GET
	@Path("check/project-name")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkProjectNameAPI(@QueryParam("projectName") String projectName) {
		return !projectDAO.isProjectNameExist(projectName);
	}
	
	@GET
	@Path("check/project-card-name")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkProjectCardNameAPI(@QueryParam("projectCardName") String projectCardName,
			@QueryParam("projectName") String projectName) {
		return !projectCardDAO.isProjectCardNameExist(projectCardName,projectName);
	}
	
	@GET
	@Path("get/internal-participants")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getInternalUserListAPI(@QueryParam("idProjectCard") String idProjectCard) {
		return projectCardDAO.getInternalUserList(idProjectCard);
	}
	
	@GET
	@Path("get/external-participants")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getExternalUserListAPI(@QueryParam("idProjectCard") String idProjectCard) {
		return projectCardDAO.getExternalUserList(idProjectCard);
	}
	
	@GET
	@Path("get/count/project-card/idProject")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCountProjectCardByIdProjectAPI(@QueryParam("idProject") String idProject) {
		return projectCardDAO.getCountProjectCardByIdProject(idProject);
	}
	
	@GET
	@Path("get/count/department-project-card")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCountDepartmentProjectCardByIdUserListAPI(@QueryParam("idUserList") List<String> idUserList) {
		return projectCardDAO.getCountAllProjectCardByIdUserList(idUserList);
	}

	@GET
	@Path("get/count/department-project")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCountDepartmentProjectByIdUserListAPI(@QueryParam("idUserList") List<String> idUserList) {
		return projectDAO.getCountAllProjectByIdUserList(idUserList);
	}

	
	
	
	

}
