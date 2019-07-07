package model.dao;

import java.util.List;

import domain.Department;
import domain.Seller;

public interface SellerDAO {

	void insert(Seller seller);
	
	void delete(Integer id);
	
	void update(Seller seller);
	
	List<Seller> findAll();
	
	Seller findById(Integer id);
	
	List<Seller> findByDepartment(Department department);
}
