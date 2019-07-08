package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

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
	PreparedStatement stmt = null;
	try {
		stmt = con.prepareStatement("insert into seller (Name, Email, BirthDate, BaseSalary, DepartmentId) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, seller.getName());
		stmt.setString(2, seller.getEmail());
		stmt.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
		stmt.setDouble(4, seller.getBaseSalary());
		stmt.setInt(5, seller.getDepartment().getId());
		
		int row = stmt.executeUpdate();
		if(row > 0) {
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				seller.setId(id);
			}
		}
		else {
			throw new DbException("ERRO inesperado, nenhuma linha foi afetada! ");
		}
	}
	catch(SQLException e) {
		throw new DbException("ERROR: "+ e.getMessage());
	}finally {
		DB.closeConnection(stmt);
	}

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
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id ORDER BY Name");
			rs =  stmt.executeQuery();
			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while(rs.next()) {
				Department department = map.get(rs.getInt("DepartmentId"));
				if (department == null) {
					department = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), department);
				}
				Seller seller = instantiateSeller(rs, department);				
				sellers.add(seller);				
			}
			
			return sellers;
		} catch (SQLException e) {
			throw new DbException("ERROR: " + e.getMessage());
		} finally {
			DB.closeConnection(stmt, rs);

		}
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement(
					"SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE seller.Id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				// department
				Department department = instantiateDepartment(rs);
				// seller
				Seller seller = instantiateSeller(rs, department);

				return seller;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException("Erro: " + e.getMessage());
		} finally {
			DB.closeConnection();
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department department) throws SQLException {
		Seller seller = new Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthDate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(department);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("DepartmentId"));
		department.setName(rs.getString("DepName"));
		return department;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(
					"SELECT seller.*, department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId=? ORDER BY Name");
			stmt.setInt(1, department.getId());
			rs = stmt.executeQuery();
			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				if (dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller seller = instantiateSeller(rs, dep);
				sellers.add(seller);
			}

			return sellers;
		} catch (SQLException e) {
			throw new DbException("ERROR: " + e.getMessage());
		} finally {
			DB.closeConnection();
		}
	}

}
