package main.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class ProjectCard {
	
	@Id
	private String idProjectCard;
	//Move submitReason and finishDate to Request
	//Add idRequest
	private String name, status, description, startDate, endDate, registeredDate, idUser, idProject, submitReason, finishDate;
	private List<String> internalParticipants, externalParticipants;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIdProject() {
		return idProject;
	}
	public void setIdProject(String idProject) {
		this.idProject = idProject;
	}
	public String getSubmitReason() {
		return submitReason;
	}
	public void setSubmitReason(String submitReason) {
		this.submitReason = submitReason;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getIdProjectCard() {
		return idProjectCard;
	}
	public void setIdProjectCard(String idProjectCard) {
		this.idProjectCard = idProjectCard;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public List<String> getInternalParticipants() {
		return internalParticipants;
	}
	public void setInternalParticipants(List<String> internalParticipants) {
		this.internalParticipants = internalParticipants;
	}
	public List<String> getExternalParticipants() {
		return externalParticipants;
	}
	public void setExternalParticipants(List<String> externalParticipants) {
		this.externalParticipants = externalParticipants;
	}
	

}
