package application;

import java.util.List;

import domain.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDAO;

public class Program2 {

	public static void main(String[] args) {
  
		DepartmentDAO departmentdao = DaoFactory.createDepartmentDao();
		
		System.out.println(" - - - Teste número 1: list all- - -");
	Department departments = departmentdao.findById(2);
	System.out.println(departments);
      
	  
		
	}

}
