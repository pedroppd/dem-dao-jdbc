package application;

import java.util.Date;

import domain.Department;
import domain.Seller;

public class Program {

	public static void main(String[] args) {
		Department dp = new Department(1, "Books");
		System.out.println(dp);
		
		Seller seller = new Seller(12, "Bob", "bob@gmail.com", new Date(), 1200.0, dp);
	System.out.println(seller);
	}

}
