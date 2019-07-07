package application;

import domain.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDAO;

public class Program {

	public static void main(String[] args) {
		
		
		SellerDAO sellerDAO = DaoFactory.createSellerDao();
		System.out.println(" - - - Teste número 1: seller findById - - -");
		Seller s = sellerDAO.findById(3);
		System.out.println(s);
	}

}
