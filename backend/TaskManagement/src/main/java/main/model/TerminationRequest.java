package main.model;

import org.springframework.data.annotation.Id;

public class TerminationRequest {

	@Id
	private String idTerminationRequest;
	//Get the approverId from idProject
	//Add approveDate which is the date that approver approve this request
	private String idProjectCard, type, reason, date, idRequester, idProject;
	
	public String getIdRequester() {
		return idRequester;
	}
	public void setIdRequester(String idRequester) {
		this.idRequester = idRequester;
	}
	public String getIdTerminationRequest() {
		return idTerminationRequest;
	}
	public void setIdTerminationRequest(String idTerminationRequest) {
		this.idTerminationRequest = idTerminationRequest;
	}
	public String getIdProjectCard() {
		return idProjectCard;
	}
	public void setIdProjectCard(String idProjectCard) {
		this.idProjectCard = idProjectCard;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getIdProject() {
		return idProject;
	}
	public void setIdProject(String idProject) {
		this.idProject = idProject;
	}
	
	
}
