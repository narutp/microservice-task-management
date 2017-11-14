package main.rest;

import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import main.Application;
import main.model.Department;
import main.model.Position;
import main.model.User;
import main.model.UserHistory;
import database.dao.*;
import database.mongodb.MongoDAOImpl;

@Named
@Path("/")
public class TaskManagementRest {

	private User user;
	private Department department;
	private Position position;
	private UserHistory userHistory;
	private ApplicationContext ctx = Application.database.getContext();
	private UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
	private DepartmentDAO departmentDAO = ctx.getBean("departmentDAO", DepartmentDAO.class);
	private PositionDAO positionDAO = ctx.getBean("positionDAO", PositionDAO.class);
	private UserHistoryDAO userHistoryDAO = ctx.getBean("userHistoryDAO", UserHistoryDAO.class);

	private final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Inject
	private RestTemplate restemplate;

	public TaskManagementRest() {
		this.user = new User();
		this.department = new Department();
		this.position = new Position();
		this.userHistory = new UserHistory();
	}

	@GET
	@Path("login/{username}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean loginAPI(@PathParam("username") String username, @PathParam("password") String password) {
		System.out.println("GET: /name/" + username + password);
		boolean check = userDAO.checkLogin(username, password);
		System.out.println(check);
		return check;
	}

	@POST
	@Path("register/{name}/{birth}/{phone}/{department}/{position}/{email}/{username}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean registerAPI(@PathParam("name") String name, @PathParam("birth") String birth,
			@PathParam("phone") String phone, @PathParam("department") String department,
			@PathParam("position") String position, @PathParam("email") String email,
			@PathParam("username") String username, @PathParam("password") String password) {
		user.setName(name);

		System.out.println("BIRTH : " + birth);
		user.setBirthdate(birth);
		user.setMobilePhone(phone);
		String idDepartment = departmentDAO.getDepartmentByName(department).getIdDepartment();
		user.setIdDepartment(idDepartment);
		System.out.println(position);
		String idPosition = positionDAO.getPositionByName(position).getIdPosition();
		user.setIdPosition(idPosition);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		userDAO.createUser(user);
		userHistory.setIdUser(user.getIdUser());
		userHistory.setIdProjectCards(new ArrayList<String>());
		userHistoryDAO.createUserHistory(userHistory);
		System.out.println("Set Id: " + user.getIdUser());
		System.out.println("Set Name: " + user.getName());
		System.out.println("Set Birth: " + user.getPassword());
		System.out.println("Set Phone: " + user.getPassword());
		System.out.println("Set Department: " + user.getPassword());
		System.out.println("Set Position: " + user.getPassword());
		System.out.println("Set Email: " + user.getPassword());
		System.out.println("Set Username: " + user.getPassword());
		System.out.println("Set Password: " + user.getPassword());
		return true;
	}

	@GET
	@Path("check/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkEmailAPI(@PathParam("email") String email) {
		return userDAO.isEmailExist(email);
	}

	@GET
	@Path("check/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkUsernameAPI(@PathParam("username") String username) {
		System.out.println("aefhkeahfahfaeilefhail");
		return userDAO.isUsernameExist(username);
	}

	@GET
	@Path("get/user/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByIdAPI(@PathParam("id") String id) {
		return userDAO.getUserById(id);
	}

	@GET
	@Path("get/all-user")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUserAPI() {
		List<User> userList = userDAO.getAllUser();
		return userList;
	}

	@GET
	@Path("check/password/{id}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkPasswordByIdAPI(@PathParam("id") String id, @PathParam("password") String password) {
		return userDAO.checkPasswordById(id, password);
	}

	@GET
	@Path("get/user-history/{idUser}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserHistory getUserHistoryByIdUserAPI(@PathParam("idUser") String idUser) {
		return userHistoryDAO.getUserHistoryByIdUser(idUser);
	}

	@GET
	@Path("get/all-user-history")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserHistory> getAllUserHistoryAPI() {
		return userHistoryDAO.getAllUserHistory();
	}

	@POST
	@Path("edit/user/{username}/{name}/{birth}/{phone}/{department}/{position}/{email}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean editUserAPI(@PathParam("username") String username, @PathParam("name") String name,
			@PathParam("birth") String birth, @PathParam("phone") String phone,
			@PathParam("department") String department, @PathParam("position") String position,
			@PathParam("email") String email, @PathParam("password") String password) {
		user = userDAO.getUserByUsername(username);
		user.setName(name);
		user.setBirthdate(birth);
		user.setMobilePhone(phone);
		System.out.println(department);
		String idDepartment = departmentDAO.getDepartmentByName(department).getIdDepartment();
		user.setIdDepartment(idDepartment);
		System.out.println(position);
		position = position.replaceAll("\\s+", "");
		String idPosition = positionDAO.getPositionByName(position).getIdPosition();
		user.setIdPosition(idPosition);
		user.setEmail(email);
		user.setPassword(password);
		userDAO.editUserById(user.getIdUser(), user);
		System.out.println("Set Id: " + user.getIdUser());
		System.out.println("Set Name: " + user.getName());
		System.out.println("Set Birth: " + user.getPassword());
		System.out.println("Set Phone: " + user.getPassword());
		System.out.println("Set Department: " + user.getPassword());
		System.out.println("Set Position: " + user.getPassword());
		System.out.println("Set Email: " + user.getPassword());
		System.out.println("Set Username: " + user.getPassword());
		System.out.println("Set Password: " + user.getPassword());
		return true;
	}

	@POST
	@Path("create/department/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createDepartmentAPI(@PathParam("name") String name) {
		department.setName(name);
		departmentDAO.createDepartment(department);
		System.out.println("ID : " + department.getIdDepartment());
		System.out.println("Set Name: " + department.getName());
		return true;
	}

	@POST
	@Path("create/position/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createPositionAPI(@PathParam("name") String name) {
		position.setName(name);
		positionDAO.createPosition(position);
		System.out.println("ID : " + position.getIdPosition());
		System.out.println("Set Name: " + position.getName());
		return true;
	}

	@GET
	@Path("get/all-department")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Department> getAllDepartmentAPI() {
		List<Department> departmentList = departmentDAO.getAllDepartment();
		return departmentList;
	}

	@GET
	@Path("get/all-position")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Position> getAllPositionAPI() {
		List<Position> positionList = positionDAO.getAllPosition();
		return positionList;
	}

	@GET
	@Path("delete-department/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteDepartmentAPI(@PathParam("name") String name) {
		departmentDAO.deleteDepartment(name);
		return true;
	}

	@GET
	@Path("delete-position/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deletePositionAPI(@PathParam("name") String name) {
		positionDAO.deletePosition(name);
		return true;
	}

	@GET
	@Path("get/user/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByUsernameAPI(@PathParam("username") String username) {
		return userDAO.getUserByUsername(username);
	}

	@GET
	@Path("get/idUser/department/{departmentName}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getIdUserListByDepartmentNameAPI(@PathParam("departmentName") String departmentName) {
		return userDAO.getIdUserListByDepartmentName(departmentName);
	}

	@POST
	@Path("set/manager/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean setManagerByUsernameAPI(@PathParam("username") String username) {
		user = userDAO.getUserByUsername(username);
		userDAO.setManagerByUser(user);
		return true;
	}

	@GET
	@Path("get/internal-user-list/department/{idDepartment}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getInternalUserListByIdDepartmentAPI(@PathParam("idDepartment") String idDepartment) {
		return userDAO.getInternalUserListByIdDepartment(idDepartment);
	}

	@GET
	@Path("get/external-user-list/department/{idDepartment}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getExternalUserListByIdDepartmentAPI(@PathParam("idDepartment") String idDepartment) {
		return userDAO.getExternalUserListByIdDepartment(idDepartment);
	}

	@GET
	@Path("get/department/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Department getDepartmentByIdAPI(@PathParam("id") String id) {
		return departmentDAO.getDepartmentById(id);
	}

	@GET
	@Path("get/position/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Position getPositionById(@PathParam("id") String id) {
		return positionDAO.getPositionById(id);
	}

	@GET
	@Path("delete/all-user")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteAllUserAPI() {
		userDAO.deleteAllUser();
		return true;
	}

	@GET
	@Path("delete/all-position")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteAllPositionAPI() {
		positionDAO.deleteAllPosition();
		return true;
	}

	@GET
	@Path("delete/all-department")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteAllDepartmentAPI() {
		departmentDAO.deleteAllDepartment();
		return true;
	}

	@GET
	@Path("get/position/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Position getPositionByNameAPI(@PathParam("name") String name) {
		return positionDAO.getPositionByName(name);
	}

	@GET
	@Path("get/idUserList/nameList/{nameList}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getIdUserListByNameListAPI(@PathParam("nameList") List<String> nameList) {
		System.out.println(nameList.get(0));
		if (!nameList.get(0).equals("null")) {
			List<String> userList = Arrays.asList(nameList.get(0).split("\\s*,\\s*"));
			List<String> returnedList = new ArrayList<String>();
			String temp = "";
			for (String user : userList) {
				temp = user.replaceAll("[^.a-zA-Z0-9 ]+", "");
				returnedList.add(temp);
			}
			return userDAO.getIdUserListByNameList(returnedList);
		}
		System.out.println("Set Blank");
		List<String> list = new ArrayList<String>();
		list.add("null");
		return list;

	}

	@POST
	@Path("add/history/{idProjectCard}/{idInternalList}/{idExternalList}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addHistoryByIdUserList(@PathParam("idProjectCard") String idProjectCard,
			@PathParam("idInternalList") List<String> idInternalList,
			@PathParam("idExternalList") List<String> idExternalList) {
		List<String> idUserList = new ArrayList<String>();
		System.out.println(idInternalList.get(0));
		if (!idInternalList.get(0).equals("null")) {
			List<String> userList = Arrays.asList(idInternalList.get(0).split("\\s*,\\s*"));
			List<String> returnedList = new ArrayList<String>();
			String temp = "";
			for (String user : userList) {
				temp = user.replaceAll("[^.a-zA-Z0-9 ]+", "");
				returnedList.add(temp);
			}
			for (String id : returnedList) {
				idUserList.add(id);
			}
		}
		if (!idExternalList.get(0).equals("null")) {
			List<String> userList = Arrays.asList(idExternalList.get(0).split("\\s*,\\s*"));
			List<String> returnedList = new ArrayList<String>();
			String temp = "";
			for (String user : userList) {
				temp = user.replaceAll("[^.a-zA-Z0-9 ]+", "");
				returnedList.add(temp);
			}
			for (String id : returnedList) {
				idUserList.add(id);
			}
		}
		for (String idUser : idUserList) {
			System.out.println(idUser);
			userHistoryDAO.addIdProjectCard(idUser, idProjectCard);
		}
		return true;
	}

}