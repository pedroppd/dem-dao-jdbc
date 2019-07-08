package application;

import java.util.Date;
import java.util.List;

import domain.Department;
import domain.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDAO;

public class Program {

	public static void main(String[] args) {
  
		SellerDAO sellerDAO = DaoFactory.createSellerDao();
		/*
		System.out.println(" - - - Teste n�mero 1: seller findById - - -");
		Seller s = sellerDAO.findById(3);
		System.out.println(s);
       */
		
		System.out.println(" - - - Teste n�mero 2: seller findByDepartment - - -");
		Department department = new Department(2, null);
		/*
		List<Seller> list = sellerDAO.findByDepartment(department);
         
		for (Seller s2 : list) {
			System.out.println(s2);
		}
		*/
		/*
		System.out.println("- - - Teste n�mero 3: seller findAll - - -");
		List<Seller> list = sellerDAO.findAll();
		
		for (Seller s3 : list) {
			System.out.println(s3);
		}
		 
		*/
		System.out.println("--- teste numero 5: Seller insert ---");
		Seller seller = new Seller(null, "Joao", "joao@hotmail.com", new Date(), 3000.0, department);
		//sellerDAO.insert(seller);
		
		System.out.println("--- teste numero 4: Seller insert ---");
		
		sellerDAO.delete(4);
	}

}
