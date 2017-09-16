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
import main.model.User;
import database.dao.*;
import database.mongodb.MongoDAOImpl;

@Named
@Path("/")
public class TaskManagementRest {
	
	private User user;
	private ApplicationContext ctx = Application.database.getContext();
	private UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
	
	@Inject
	private RestTemplate restemplate; 
	
	public TaskManagementRest() {
		this.user = new User();
	}
	
	@GET
	@Path("login/{name}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean loginAPI(
			@PathParam("name") String name, 
			@PathParam("password") String password ){
		System.out.println("GET: /name/"+name+password);
		boolean check = userDAO.checkLogin(name, password);
		System.out.println(check);
		return check;
	}
	
	@GET
	@Path("register/{name}/{password}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean registerAPI(
			@PathParam("name") String name, 
			@PathParam("password") String password ){
		user.setName(name);
		user.setPassword(password);
		userDAO.createUser(user);
		System.out.println("Set Name: " + user.getName());
		System.out.println("Set Pass: " + user.getPassword());
		return true;
	}
	
	@GET
	@Path("get/user/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserAPI(@PathParam("name") String name) {
		User user = userDAO.getUserByName(name);
		return user;
	}
	
}