package database.dao;

import java.util.List;

import main.model.ProjectCard;
import main.model.TerminationRequest;

public interface TerminationRequestDAO {

	public void createTerminationRequest(TerminationRequest terminationRequest);

	public List<TerminationRequest> getAllTerminationRequestByIdUser(String idUser);

	public void approveTerminationRequestById(String idTerminationRequest, String approveDate);

	public void rejectTerminationRequest(TerminationRequest terminationRequest, ProjectCard projectCard);

	public List<TerminationRequest> getAllTerminationRequest();

	public TerminationRequest getTerminationRequestByProjectAndProjectCardName(String projectName,
			String projectCardName);

}
