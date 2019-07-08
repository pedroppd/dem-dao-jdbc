package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;

import db.DB;
import db.DbException;
import domain.Department;
import model.dao.DepartmentDAO;

public class DepartmentDaoJDBC implements DepartmentDAO {

	private Connection con;

	public DepartmentDaoJDBC(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Department department) {
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("insert into department (Name) values (?)", Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, department.getName());
			int row = stmt.executeUpdate();
			
			if (row > 0) {
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					department.setId(id);
				}
			} 
			else {
				throw new DbException("ERRO inesperado, nenhuma linha foi afetada! ");
			}
		} catch (SQLException e) {
			throw new DbException("ERROR: " + e.getMessage());
		} finally {
			DB.closeConnection(stmt);
		}

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
