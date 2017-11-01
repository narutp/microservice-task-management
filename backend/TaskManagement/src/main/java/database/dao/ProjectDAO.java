package database.dao;

import java.util.List;

import main.model.Project;


public interface ProjectDAO {
	
	public List<Project> getAllProjects ();

	public void createProject (Project prject);
	
	public void editProjectById (Project project, String idProject);
	
	public Project getProjectById (String idProject);
	
	public int getCountProject ();
	
	public void deleteProjectById (String idProject);
	
	public List<Project> getAllProjectByIdUserList (List<String> idUserList);
	
	public int getCountAllProjectByIdUserList(List<String> idUserList);

}
