package main.rest;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
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
	
	@GET
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
	@Path("user/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserAPI(@PathParam("id") String id) {
		User user = userDAO.getUserById(id);
		return user;
	}
	
	@GET
	@Path("check/username/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkUsernameExistAPI(@PathParam("username") String username) {
		return userDAO.isUsernameExist(username);
	}
	
	@GET
	@Path("check/email/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean checkEmailExistAPI(@PathParam("email") String email) {
		return userDAO.isEmailExist(email);
	}
	
}