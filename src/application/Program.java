package application;

import domain.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDAO;

public class Program {

	public static void main(String[] args) {
		
		
		SellerDAO sellerDAO = DaoFactory.createSellerDao();
		
		Seller s = sellerDAO.findById(3);
		System.out.println(s);
	}

}
