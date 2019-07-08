package model.dao.impl;

import java.sql.Connection;
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
			} else {
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
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("delete from department WHERE Id = ?");
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("ERROR: " + e.getMessage());
		} finally {
			DB.closeConnection(stmt);
		}

	}

	@Override
	public void update(Department department) {
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update seller set Name = ? WHERE Id = ?");
			stmt.setString(1, department.getName());
			stmt.setInt(2, department.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new DbException("ERROR: " + e.getMessage());
		} finally {
			DB.closeConnection(stmt);
		}

	}

	@Override
	public List<Department> findAll() {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * from department ORDER BY Name");
			rs = stmt.executeQuery();
			List<Department> departments = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			while (rs.next()) {
				Department department = map.get(rs.getInt("Id"));
				if (department == null) {
					department = instantiateDepartment(rs);
					map.put(rs.getInt("Id"), department);
				}

				departments.add(department);
			}

			return departments;
		} catch (SQLException e) {
			throw new DbException("ERROR: " + e.getMessage());
		} finally {
			DB.closeConnection(stmt, rs);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * from department where Id = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			Department departments = new Department();
			Map<Integer, Department> map = new HashMap<>();
			if (rs.next()) {
				Department department = map.get(rs.getInt("Id"));
				if (department == null) {
					department = instantiateDepartment(rs);
					map.put(rs.getInt("Id"), department);
				}
				departments = department;
			}
			return departments;
		} catch (SQLException e) {
			throw new DbException("ERROR: " + e.getMessage());
		} finally {
			DB.closeConnection(stmt, rs);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department department = new Department();
		department.setId(rs.getInt("Id"));
		department.setName(rs.getString("Name"));
		return department;
	}

}
