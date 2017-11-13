package database.dao;

import java.util.List;

import main.model.TerminationRequest;

public interface TerminationRequestDAO {
	
	public void createTerminationRequest(TerminationRequest terminationRequest);
	
	public List<TerminationRequest> getAllTerminationRequestByIdUser(String idUser);
	
	public void approveTerminationRequestById(String idTerminationRequest, String approveDate);
	
	public void rejectTerminationRequestById(String idTerminationRequest);
	
	public List<TerminationRequest> getAllTerminationRequest();
	
}
