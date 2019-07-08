package application;

import java.util.Date;

import domain.Department;
import domain.Seller;
import model.dao.DaoFactory;
import model.dao.DepartmentDAO;

public class Program2 {

	public static void main(String[] args) {
  
		DepartmentDAO departmentdao = DaoFactory.createDepartmentDao();
		
		System.out.println(" - - - Teste número 1: department insert- - -");
	    Department department = new Department(null, "Teste");
		departmentdao.insert(department);
      
		/*
		System.out.println(" - - - Teste número 2: seller findByDepartment - - -");
		Department department = new Department(2, null);
		
		List<Seller> list = sellerDAO.findByDepartment(department);
         
		for (Seller s2 : list) {
			System.out.println(s2);
		}
		*/
		/*
		System.out.println("- - - Teste número 3: seller findAll - - -");
		List<Seller> list = sellerDAO.findAll();
		
		for (Seller s3 : list) {
			System.out.println(s3);
		}
		 
		*/
		
	}

}
