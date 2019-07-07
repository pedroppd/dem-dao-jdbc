package application;

import java.util.List;

import domain.Department;
import domain.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDAO;

public class Program {

	public static void main(String[] args) {
  
		SellerDAO sellerDAO = DaoFactory.createSellerDao();
		/*
		System.out.println(" - - - Teste número 1: seller findById - - -");
		Seller s = sellerDAO.findById(3);
		System.out.println(s);
       
		
		System.out.println(" - - - Teste número 2: seller findByDepartment - - -");
		Department department = new Department(2, null);
		List<Seller> list = sellerDAO.findByDepartment(department);

		for (Seller s2 : list) {
			System.out.println(s2);
		}
		 */
		
		System.out.println("- - - Teste número 3: seller findAll - - -");
		List<Seller> list = sellerDAO.findAll();
		
		for (Seller s3 : list) {
			System.out.println(s3);
		}
	}

}
