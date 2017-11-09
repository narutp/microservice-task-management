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
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import database.dao.CardDAO;
import database.dao.ProjectDAO;
import database.dao.RequestDAO;
import main.Application;
import main.model.Project;
import main.model.Request;
import main.model.Card;

@Named
@Path("/")
public class TaskManagementRest {

	private Project project;
	private Card card;
	private Request request;
	private ApplicationContext ctx = Application.database.getContext();
	private ProjectDAO projectDAO = ctx.getBean("projectDAO",ProjectDAO.class);
	private CardDAO cardDAO = ctx.getBean("cardDAO",CardDAO.class);
	private RequestDAO requestDAO = ctx.getBean("requestDAO",RequestDAO.class);
	
	private final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Inject
	private RestTemplate restemplate; 
	
	public TaskManagementRest() {
		this.project = new Project();
		this.card = new Card();
		this.request = new Request();
	}
	
	@POST
	@Path("create/project/{name}/{description}/{idUser}/{idDepartment}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createProjectAPI(
			@PathParam("name") String name,
			@PathParam("description") String description,
			@PathParam("idUser") String idUser,
			@PathParam("idDepartment") String idDepartment) {
		if(name.equals("")) 
			project.setName("-");
		else
			project.setName(name);
		if(description.equals("")) 
			project.setDescription("-");
		else 
			project.setDescription(description);
		project.setIdUser(idUser);
		
		//date convert to string
		Date date = new Date();
		date = Calendar.getInstance().getTime();  
		String regisDate = DATEFORMAT.format(date);
		project.setRegisteredDate(regisDate);
		project.setIdDepartment(idDepartment);
		
		projectDAO.createProject(project);
		return true;
	}
	
	@POST
	@Path("edit/project/{idProject}/{name}/{description}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean editProjectAPI(
			@PathParam("idProject") String idProject,
			@PathParam("name") String name,
			@PathParam("description") String description) {
		project = projectDAO.getProjectById(idProject);
		if(name.equals("")) 
			project.setName("-");
		else
			project.setName(name);
		if(description.equals("")) 
			project.setDescription("-");
		else 
			project.setDescription(description);

		projectDAO.editProjectById(project, idProject);
		return true;
	}
	
	@POST
	@Path("create/card/{idUser}/{idProject}/{name}/{description}/{startDate}/{endDate}/{internalParticipants}/{externalParticipants}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createCardAPI(
			@PathParam("idUser") String idUser,
			@PathParam("idProject") String idProject,
			@PathParam("name") String name,
			@PathParam("description") String description,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("internalParticipants") List<String> internalParticipants,
			@PathParam("externalParticipants") List<String> externalParticipants) {
	
		if(name.equals(""))
			card.setName("-");
		else
			card.setName(name);
		if(description.equals(""))
			card.setDescription("-");
		else
			card.setDescription(description);
		if(startDate.equals(""))
			card.setStartDate("-");
		else
			card.setStartDate(startDate);
		if(endDate.equals(""))
			card.setEndDate("-");
		else
			card.setEndDate(endDate);
		if(internalParticipants.equals(null))
			card.setInternalParticipants(null);
		else
			card.setInternalParticipants(internalParticipants);
		if(externalParticipants.equals(null))
			card.setExternalParticipants(null);
		else
			card.setExternalParticipants(externalParticipants);
		
		//date convert to string
		Date date = new Date();
		date = Calendar.getInstance().getTime();  
		String regisDate = DATEFORMAT.format(date);
		card.setRegisteredDate(regisDate);
		card.setStatus("In progress");
		card.setSubmitReason("-");
		card.setFinishDate("-");
		
		cardDAO.createCard(card);
		return true;
	}
	
	@GET
	@Path("create/card/{idUser}/{projectName}/{name}/{description}/{startDate}/{endDate}")
	@Produces(MediaType.TEXT_PLAIN)
	public String createCardWithoutParticipantAPI(
			@PathParam("idUser") String idUser,
			@PathParam("projectName") String projectName,
			@PathParam("name") String name,
			@PathParam("description") String description,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate) {
		
		card.setIdUser(idUser);
	
		if(name.equals(""))
			card.setName("-");
		else
			card.setName(name);
		card.setIdProject(projectDAO.getProjectByName(projectName).getIdProject());
		if(description.equals(""))
			card.setDescription("-");
		else
			card.setDescription(description);
		if(startDate.equals(""))
			card.setStartDate("-");
		else
			card.setStartDate(startDate);
		if(endDate.equals(""))
			card.setEndDate("-");
		else
			card.setEndDate(endDate);
		card.setInternalParticipants(new ArrayList<String>());
		card.setExternalParticipants(new ArrayList<String>());
		//date convert to string
		Date date = new Date();
		date = Calendar.getInstance().getTime();  
		String regisDate = DATEFORMAT.format(date);
		card.setRegisteredDate(regisDate);
		card.setStatus("In progress");
		card.setSubmitReason("-");
		card.setFinishDate("-");
		
		String idCard = cardDAO.createCard(card);
		System.out.println(idCard);
		return idCard;
	
	}
	
	@POST
	@Path("add/external-participant/{idCard}/{idUser}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addExternalParticipantAPI(
			@PathParam("idCard") String idCard,
			@PathParam("idUser") String idUser) {
		
		card = cardDAO.getCardByIdCard(idCard);
		List<String> exList = card.getExternalParticipants();
		exList.add(idUser);
		card.setExternalParticipants(exList);
		cardDAO.addExternalParticipantByIdCard(idCard,card);
		return true;
	}
	
	@POST
	@Path("add/external-participants/{idCard}/{idUserList}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addExternalParticipantsAPI(
			@PathParam("idCard") String idCard,
			@PathParam("idUserList") List<String> idList) {
		
		List<String> userList = Arrays.asList(idList.get(0).split("\\s*,\\s*"));
		List<String> idUserList = new ArrayList<String>();
		String temp = "";
		for(String user : userList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+","");
			idUserList.add(temp);
		}
		card = cardDAO.getCardByIdCard(idCard);
		List<String> exList = card.getExternalParticipants();
		for(String idUser : idUserList) {
			exList.add(idUser);
		}
		card.setExternalParticipants(exList);
		cardDAO.addExternalParticipantByIdCard(idCard,card);
		return true;
	}
	
	@POST
	@Path("add/internal-participant/{idCard}/{idUser}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addInternalParticipantAPI(
			@PathParam("idCard") String idCard,
			@PathParam("idUser") String idUser) {
		
		card = cardDAO.getCardByIdCard(idCard);
		List<String> inList = card.getInternalParticipants();
		inList.add(idUser);
		card.setInternalParticipants(inList);
		cardDAO.addInternalParticipantByIdCard(idCard,card);
		return true;
	}
	
	@POST
	@Path("add/internal-participants/{idCard}/{idUserList}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addInternalParticipantsAPI(
			@PathParam("idCard") String idCard,
			@PathParam("idUserList") String idList) {
		
		System.out.println("ININ");
		List<String> userList = Arrays.asList(idList.split("\\s*,\\s*"));
		List<String> idUserList = new ArrayList<String>();
		String temp = "";
		for(String user : userList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+","");
			idUserList.add(temp);
		}
		card = cardDAO.getCardByIdCard(idCard);
		List<String> inList = card.getInternalParticipants();
		for(String idUser : idUserList) {
			System.out.println(idUser);
			inList.add(idUser);
		}
		card.setInternalParticipants(inList);
		cardDAO.addInternalParticipantByIdCard(idCard,card);
		System.out.println("ds");
		return true;
	}
	
	@POST
	@Path("add/participants/{idCard}/{idInternalUserList}/{idExternalUserList}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean addParticipantsAPI(
			@PathParam("idCard") String idCard,
			@PathParam("idInternalUserList") List<String> idInList,
			@PathParam("idExternalUserList") List<String> idExList) {
		
		System.out.println("ININ");
		List<String> tempInUserList = Arrays.asList(idInList.get(0).split("\\s*,\\s*"));
		List<String> idInternalUserList = new ArrayList<String>();
		String temp = "";
		for(String user : tempInUserList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+","");
			idInternalUserList.add(temp);
		}
		
		List<String> tempExUserList = Arrays.asList(idExList.get(0).split("\\s*,\\s*"));
		List<String> idExternalUserList = new ArrayList<String>();
		for(String user : tempExUserList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+","");
			idExternalUserList.add(temp);
		}
		
		card = cardDAO.getCardByIdCard(idCard);
		List<String> inList = card.getInternalParticipants();
		List<String> exList = card.getExternalParticipants();
		for(String idUser : idInternalUserList) {
			inList.add(idUser);
		}
		for(String idUser : idExternalUserList) {
			exList.add(idUser);
		}
		card.setInternalParticipants(inList);
		card.setExternalParticipants(exList);
		cardDAO.addParticipantsByIdCard(idCard,card);
		return true;
	}
	
	@POST
	@Path("update/card/{idCard}/{name}/{description}/{startDate}/{endDate}/{internalParticipants}/{externalParticipants}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean updateCardAPI(
			@PathParam("idCard") String idCard,
			@PathParam("name") String name,
			@PathParam("description") String description,
			@PathParam("startDate") String startDate,
			@PathParam("endDate") String endDate,
			@PathParam("internalParticipants") List<String> internalParticipants,
			@PathParam("externalParticipants") List<String> externalParticipants) {
		
		card = cardDAO.getCardByIdCard(idCard);
		if(name.equals(""))
			card.setName("-");
		else
			card.setName(name);
		if(description.equals(""))
			card.setDescription("-");
		else
			card.setDescription(description);
		if(startDate.equals(""))
			card.setStartDate("-");
		else
			card.setStartDate(startDate);
		if(endDate.equals(""))
			card.setEndDate("-");
		else
			card.setEndDate(endDate);
		if(internalParticipants.equals(null))
			card.setInternalParticipants(null);
		else
			card.setInternalParticipants(internalParticipants);
		if(externalParticipants.equals(null))
			card.setExternalParticipants(null);
		else
			card.setExternalParticipants(externalParticipants);
		
		cardDAO.updateCardByIdCard(idCard, card);
		return true;
	}
	
	 @GET
	 @Path("get/all-request/{idUser}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<Request> getAllRequestByIdUserAPI(@PathParam("idUser") String idUser) {
		List<Request> requestList = requestDAO.getAllRequestByIdUser(idUser);
	 	return requestList;
	 }

//	 @GET
//	 @Path("get/request/{idUser}")
//	 @Produces(MediaType.APPLICATION_JSON)
//	 public Request getRequestInfoByIdAPI (@PathParam("idUser") String idUser) {
//	 	Request request = requestDAO.getRequestInfoByIdUser(idUser);
//	 	return request;
//	 }

	 @GET
	 @Path("approve/request/{idRequest}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public boolean approveRequestByIdUserAPI (@PathParam("idRequest") String idRequest) {
		Date date = new Date();
		date = Calendar.getInstance().getTime();  
		String approveDate = DATEFORMAT.format(date);
		
		requestDAO.approveRequestById(idRequest, approveDate);
	 	return true;
	 }

	 @GET
	 @Path("reject/request/{idRequest}")
	 @Produces(MediaType.APPLICATION_JSON) 
	 public boolean rejectRequestByIdAPI (@PathParam("idRequest") String idRequest) {		
		requestDAO.rejectRequestById(idRequest);
		
		return true;
	 }


	@GET
	@Path("get/all-card")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> getAllCardAPI() {
		List<Card> CardList = cardDAO.getAllCard();
		return  CardList;
	}
	
	@GET
	@Path("get/all-card/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> getAllCardByUserIdAPI(@PathParam("idUser") String idUser) {
		List<Card> cardList = cardDAO.getAllCardByIdUserParticipated(idUser);
		return  cardList;
	}
	
	@GET
	@Path("get/project/{idProject}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project getProjectByIdProjectAPI(@PathParam("idProject") String idProject) {
		Project project = projectDAO.getProjectById(idProject);
		return project;
	}
	
//	@GET
//	@Path("request/finish/{submitReason}/{idCard}/{idUser}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public boolean finishCardAPI(
//			@PathParam("submitReason") String submitReason,
//			@PathParam("idCard") String idCard,
//			@PathParam("idUser") String idUser
//			) {
//		request.setType("finish");
//		if(approveRequestByIdUserAPI(idUser))
//			card.setStatus("Done");
//		else 
//			rejectRequestByIdUserAPI(idUser);
//			
//		return true;
//	}
	
	
//	@GET
//	@Path("request/delete/{submitReason}/{idCard}/{idUser}")
//	@Produces(MediaType.TEXT_PLAIN)
//	public boolean deleteCardAPI(
//			@PathParam("submitReason") String submitReason,
//			@PathParam("idCard") String idCard,
//			@PathParam("idUser") String idUser) {
//		request.setType("delete");
//		if(approveRequestByIdUserAPI(idUser))
//			cardDAO.deleteCardByIdCard(idCard);
//		else
//			rejectRequestByIdUserAPI(idUser);
//		return true;
//	}
	
	@GET
	@Path("get/finish-card/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> getFinishedCardByIdUserAPI(@PathParam("idUser") String idUser) {
		return cardDAO.getFinishedCardByIdUser(idUser);
	}
	
	@GET
	@Path("get/department-card/{idUserList}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Card> getDepartmentCardByIdUserListAPI(@PathParam("idUserList") List<String> idUserList) {
		List<String> userList = Arrays.asList(idUserList.get(0).split("\\s*,\\s*"));
		List<String> returnedList = new ArrayList<String>();
		String temp = "";
		for(String user : userList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+","");
			returnedList.add(temp);
		}
		return cardDAO.getAllCardByIdUserList(returnedList);
	}
	
	@GET
	@Path("get/department-project/{idUserList}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getDepartmentProjectByIdUserListAPI(@PathParam("idUserList") List<String> idUserList) {
		List<String> userList = Arrays.asList(idUserList.get(0).split("\\s*,\\s*"));
		List<String> returnedList = new ArrayList<String>();
		String temp = "";
		for(String user : userList) {
			temp = user.replaceAll("[^a-zA-Z0-9]+","");
			returnedList.add(temp);
		}
		return projectDAO.getAllProjectByIdUserList(returnedList);
	}
	
	@GET
	@Path("get/count/department-card/{idUserList}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCountDepartmentCardByIdUserListAPI(@PathParam("idUserList") List<String> idUserList) {
		return cardDAO.getCountAllCardByIdUserList(idUserList);
	}
	
	@GET
	@Path("get/count/department-project/{idUserList}")
	@Produces(MediaType.APPLICATION_JSON)
	public int getCountDepartmentProjectByIdUserListAPI(@PathParam("idUserList") List<String> idUserList) {
		return projectDAO.getCountAllProjectByIdUserList(idUserList);
	}
	
	@POST
	@Path("request/finish/{idCard}/{reason}/{idRequester}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean requestToFinishCardAPI(
			@PathParam("idCard") String idCard,
			@PathParam("reason") String reason,
			@PathParam("idRequester") String idRequester){
		request.setIdCard(idCard);
		request.setType("Finish");
		request.setReason(reason);
		Date date = new Date();
		date = Calendar.getInstance().getTime();  
		String requestDate = DATEFORMAT.format(date);
		request.setDate(requestDate);
		request.setIdRequester(idRequester);
		requestDAO.createRequest(request);
		return true;
	}
	
	@POST
	@Path("request/delete/{idCard}/{reason}/{idRequester}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean requestToDeleteCardAPI(
			@PathParam("idCard") String idCard,
			@PathParam("reason") String reason,
			@PathParam("idRequester") String idRequester){
		request.setIdCard(idCard);
		request.setType("Delete");
		request.setReason(reason);
		Date date = new Date();
		date = Calendar.getInstance().getTime();  
		String requestDate = DATEFORMAT.format(date);
		request.setDate(requestDate);
		request.setIdRequester(idRequester);
		requestDAO.createRequest(request);
		return true;
	}
	
	@GET
	@Path("get/all-project")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllProjectAPI() {
		return projectDAO.getAllProject();
	}
	
	@GET
	@Path("delete/project/{idProject}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteProjectByIdAPI(@PathParam("idProject") String idProject) {
		projectDAO.deleteProjectById(idProject);
		return true;
	}
	
	@POST
	@Path("delete/card/{idCard}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteCardByIdAPI(@PathParam("idCard") String idCard) {
		cardDAO.deleteCardById(idCard);
		return true;
	}
	
	@GET
	@Path("delete/all-project")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteAllProjectAPI() {
		projectDAO.deleteAllProject();
		return true;
	}
	
	@GET
	@Path("delete/all-card")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteAllCardAPI() {
		cardDAO.deleteAllCard();
		return true;
	}
	
	@GET
	@Path("get/idDepartment/card/{idCard}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIdDepartmentByIdCardAPI(@PathParam("idCard") String idCard) {
		System.out.println(idCard);
		String idDepartment = projectDAO.getIdDepartmentByIdCard(idCard);
		System.out.println("In get idDepartment");
		System.out.println(idDepartment);
		return idDepartment;
	}
	
	@GET
	@Path("get/card/{idCard}")
	@Produces(MediaType.APPLICATION_JSON)
	public Card getCardByIdCardAPI(@PathParam("idCard") String idCard) {
		return cardDAO.getCardByIdCard(idCard);
	}
	
	@POST
	@Path("finish/card/{idCard}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean finishCardByIdCardAPI(@PathParam("idCard") String idCard) {
		card = cardDAO.getCardByIdCard(idCard);
		card.setStatus("Finish");
		Date date = new Date();
		date = Calendar.getInstance().getTime();  
		String finishDate = DATEFORMAT.format(date);
		System.out.println(finishDate);
		card.setFinishDate(finishDate);
		cardDAO.setFinish(idCard,card);
		return true;
	}
	
	
	
	
	
}
