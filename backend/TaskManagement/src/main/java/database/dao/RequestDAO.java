package database.dao;

import java.util.List;

import main.model.Request;

public interface RequestDAO {
	
	public void createRequest(Request request);
	
	public List<Request> getAllRequestByIdUser(String idUser);
	
	public void approveRequestById(String idRequest, String approveDate);
	
	public void rejectRequestById(String idRequest);
	
	public List<Request> getAllRequest();
	
}
