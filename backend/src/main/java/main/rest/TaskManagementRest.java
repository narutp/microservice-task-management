package main.rest;

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

import main.Application;
import main.model.Department;
import main.model.Position;
import main.model.User;
import main.model.UserLog;
import database.dao.*;
import database.mongodb.MongoDAOImpl;

@Named
@Path("/")
public class TaskManagementRest {
	
	private User user;
	private Department department;
	private Position position;
	private UserLog userLog;
	private ApplicationContext ctx = Application.database.getContext();
	private UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
	private DepartmentDAO departmentDAO = ctx.getBean("departmentDAO", DepartmentDAO.class);
	private PositionDAO positionDAO = ctx.getBean("positionDAO", PositionDAO.class);
	private UserLogDAO userLogDAO = ctx.getBean("userlogDAO", UserLogDAO.class);
	
	@Inject
	private RestTemplate restemplate; 
	
	public TaskManagementRest() {
		this.user = new User();
		this.department = new Department();
		this.position = new Position();
		this.userLog = new UserLog();
	}
	
	@GET
	@Path("login/{username}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean loginAPI(
			@PathParam("username") String username, 
			@PathParam("password") String password ){
		System.out.println("GET: /name/"+username+password);
		boolean check = userDAO.checkLogin(username, password);
		System.out.println(check);
		return check;
	}
	
	@POST
	@Path("register/{name}/{birth}/{phone}/{department}/{position}/{email}/{username}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean registerAPI(
			@PathParam("name") String name, 
			@PathParam("birth") String birth, 
			@PathParam("phone") String phone, 
			@PathParam("department") String department, 
			@PathParam("position") String position, 
			@PathParam("email") String email, 
			@PathParam("username") String username, 
			@PathParam("password") String password ){
		if(userDAO.isUsernameExist(username)) {
			System.out.println(username + " : Username has been used");
			return false;
		}
		if(userDAO.isEmailExist(email)) {
			System.out.println(email + " : Email has been used");
			return false;
		}
		user.setName(name);
		user.setBirthday(birth);
		user.setMobilePhone(phone);
		String idDepartment = departmentDAO.getDepartmentByName(department).getIdDepartment();
		System.out.println("GJEILGAELIHGGEALEAGBLBGAELGELEGBLGEAB");
		user.setIdDepartment(idDepartment);
		String idPosition = positionDAO.getPositionByName(position).getIdPosition();
		user.setIdPosition(idPosition);
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(password);
		userDAO.createUser(user);
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
		return userDAO.isUsernameExist(username);
	}
	
	@GET
	@Path("get/user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserByIdAPI(@PathParam("id") String id) {
		User user = userDAO.getUserById(id);
		return user;
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
	public boolean checkPasswordByIdAPI(
			@PathParam("id") String id, 
			@PathParam("password") String password ) {
		return userDAO.checkPasswordById(id, password);
	}
	
	@GET
	@Path("get/all-user-log/{user-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserLog> getAllUserLogByUserIdAPI(@PathParam("user-id") String userId) {
		return userLogDAO.getAllUserLogByUserId(userId);
	}
	
	@GET
	@Path("get/all-user-log")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserLog> getAllUserLog() {
		return userLogDAO.getAllUserLog();
	}
	
	@GET
	@Path("edit/user/{id}/{name}/{birth}/{phone}/{department}/{position}/{email}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean editUserAPI(
			@PathParam("id") String id,
			@PathParam("name") String name, 
			@PathParam("birth") String birth, 
			@PathParam("phone") String phone, 
			@PathParam("department") String department, 
			@PathParam("position") String position, 
			@PathParam("email") String email,
			@PathParam("password") String password ){
		user = userDAO.getUserById(id);
		if(userDAO.isEmailExist(email)) {
			System.out.println(email + " : Email has been used");
			return false;
		}
		user.setName(name);
		user.setBirthday(birth);
		user.setMobilePhone(phone);
		String idDepartment = departmentDAO.getDepartmentByName(department).getIdDepartment();
		user.setIdDepartment(idDepartment);
		String idPosition = positionDAO.getPositionByName(position).getIdPosition();
		user.setIdPosition(idPosition);
		user.setEmail(email);
		user.setPassword(password);
		userDAO.editUserById(id, user);
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
	@Path("create/department/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createDepartmentAPI(@PathParam("name") String name){
		department.setName(name);
		departmentDAO.createDepartment(department);
		System.out.println("ID : " + department.getIdDepartment());
		System.out.println("Set Name: " + department.getName());
		return true;
	}
	
	@GET
	@Path("create/position/{name}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createPositionAPI(@PathParam("name") String name){
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
	public boolean deleteAPI(@PathParam("name") String name) {
		departmentDAO.deleteDepartment(name);;
		return true;
	}
	
	@GET
	@Path("delete-position/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deletepAPI(@PathParam("name") String name) {
		positionDAO.deletePosition(name);;
		return true;
	}
	
}