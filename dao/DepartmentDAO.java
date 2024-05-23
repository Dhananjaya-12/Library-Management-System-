package com.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lms.to.DepartmentTO;
import com.lms.utill.DBUtil;

public class DepartmentDAO {

	public DepartmentTO addDept(DepartmentTO departmentTO) {
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate("insert into department (Name) values("
+ "'"
+ departmentTO.getDeptName()
+ "'"
 + ")");

			ResultSet resultSet1 = statement
					.executeQuery("SELECT `Dept_ID` FROM `department` ORDER BY Dept_ID DESC LIMIT 1");
			while(resultSet1.next()){
				departmentTO.setDept_id(resultSet1.getInt("Dept_ID"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return departmentTO;
	}

}
