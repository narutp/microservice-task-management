package database.dao;

import java.util.List;

import main.model.ProjectCard;

public interface ProjectCardDAO {

	public void createProjectCard(ProjectCard projectCard);

	public List<ProjectCard> getAllProjectCard();

	public ProjectCard getProjectCardByIdProjectCard(String idProjectCard);

	public void updateProjectCardByIdProjectCard(String idProjectCard, ProjectCard ProjectCard);

	public void deleteProjectCardById(String idProjectCard);

	public List<ProjectCard> getFinishProjectCardByIdUser(String idUser);

	public List<ProjectCard> getAllProjectCardByIdUserList(List<String> userList);

	public List<ProjectCard> getAllProjectCardByIdUserParticipated(String idUser);

	public int getCountAllProjectCardByIdUserList(List<String> idUserList);

	public void addParticipantsByIdProjectCard(String idProjectCard, ProjectCard projectCard);

	public void requestToFinishProjectCard(ProjectCard projectCard);

	public void requestToDeleteProjectCard(ProjectCard projectCard);

	public ProjectCard getProjectCardByProjectAndProjectCardName(String projectName, String projectCardName);

	public int getCountProjectCardByIdProject(String idProject);

	public boolean isProjectCardNameExist(String projectCardName, String projectName);

	public List<String> getInternalUserList(String idProjectCard);

	public List<String> getExternalUserList(String idProjectCard);
}
