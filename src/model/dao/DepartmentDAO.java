package model.dao;

import java.util.List;

import domain.Department;

public interface DepartmentDAO {

	void insert(Department department);
	
	void delete(Integer id);
	
	void update(Department department);
	
	List<Department> findAll();
	
	Department findById(Integer id);
}
