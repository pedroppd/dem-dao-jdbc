package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import domain.Department;
import domain.Seller;
import model.dao.SellerDAO;

public class SellerDaoJDBC implements SellerDAO {

	private Connection con;
	
	public SellerDaoJDBC(Connection c) {
		this.con = c;
	}
	
	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Seller findById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;		
		try {		
			
			stmt = con.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			Seller seller = new Seller();
			Department department = new Department();
			
			if (rs.next()) {
				// department
				department.setId(rs.getInt("DepartmentId"));
				department.setName(rs.getString("DepName"));
				// seller
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setDepartment(department);
				
			}
			return seller;
			
		} 
		catch (SQLException e) {
			throw new DbException("Erro: " + e.getMessage());
		}finally {
			DB.closeConnection();
		}
	}

}
