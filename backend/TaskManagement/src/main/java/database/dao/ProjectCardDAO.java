package database.dao;

import java.util.List;

import main.model.ProjectCard;

public interface ProjectCardDAO {
	
	public String createProjectCard (ProjectCard projectCard);
	
	public List<ProjectCard> getAllProjectCard ();
	
	public ProjectCard getProjectCardByIdProjectCard(String idProjectCard);
	
	public void updateProjectCardByIdProjectCard (String idProjectCard, ProjectCard ProjectCard);
	
	public void deleteProjectCardById (String idProjectCard);
	
	public int getCountProjectCard();
	
	public List<ProjectCard> getFinishProjectCardByIdUser(String idUser);
	
	public List<ProjectCard> getAllProjectCardByIdUserList(List<String> userList);

	public List<ProjectCard> getAllProjectCardByIdUserParticipated(String idUser);
	
	public int getCountAllProjectCardByIdUserList(List<String> idUserList);
	
	public void deleteAllProjectCard ();
	
	public void addInternalParticipantByIdProjectCard(String idProjectCard, ProjectCard projectCard);
	
	public void addExternalParticipantByIdProjectCard(String idProjectCard, ProjectCard projectCard);
	
	public void addParticipantsByIdProjectCard(String idProjectCard, ProjectCard projectCard);
	
	public void setFinish(String idProjectCard, ProjectCard projectCard);
	
	public void requestToFinishProjectCard(ProjectCard projectCard);
	
	public void requestToDeleteProjectCard(ProjectCard projectCard);

	public ProjectCard getProjectCardByProjectAndProjectCardName(String projectName, String projectCardName);
}
