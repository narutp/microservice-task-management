package database.dao;

import java.util.List;

import main.model.Department;

public interface DepartmentDAO {
	
	public Department getDepartmentByName(String name);
	
	public List<Department> getAllDepartment();
	
	public void createDepartment (Department department);
	
	public void deleteDepartment (String name);
}
