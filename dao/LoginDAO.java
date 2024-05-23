package com.lms.dao;

import com.lms.to.LoginTO;
import com.lms.utill.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class LoginDAO {

	public LoginTO checkLogin(LoginTO loginTO) {
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT * FROM login WHERE User_Name='"
							+ loginTO.getUserName() + "' && Password ='" + loginTO.getPassword() + "'");
			while (resultSet.next()) {
				loginTO.setLoginID(resultSet.getInt("Login_id"));
				loginTO.setUserName(resultSet.getString("User_Name"));
				loginTO.setPassword(resultSet.getString("Password"));
				loginTO.setLoginType(resultSet.getString("Login_type"));
				loginTO.setStatus(resultSet.getString("status"));
			}
			if(loginTO.getLoginID() != 0){
				Statement statement2=connection.createStatement();
				ResultSet resultSet2= statement2.executeQuery("select dept_id from userregistrations where login_id = "+loginTO.getLoginID()); 
				while(resultSet2.next()){
					loginTO.setDeptName(resultSet2.getString("dept_id"));
				}
			return loginTO;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block=
			e.printStackTrace();
		}
		return null;

	}

	public LoginTO addLogin(LoginTO loginTO) {
		Connection connection;
		try {
			connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			String sql = "insert into login(User_Name,Password,Login_type,status)VALUES("
					+ "'"
					+ loginTO.getUserName()
					+ "'"
					+ ","
					+ "'"
					+ loginTO.getPassword()
					+ "'"
					+ ","
					+ "'"
					+ loginTO.getLoginType()
					+ "'"
					+ ","
					+ "'"
					+ loginTO.getStatus() + "'" + ")";
			int a = statement.executeUpdate(sql);
			if (a == 1) {
				ResultSet resultSet = statement
						.executeQuery("SELECT `Login_id` FROM `login` ORDER BY Login_id DESC LIMIT 1");
				while (resultSet.next()) {
					loginTO.setLoginID(resultSet.getInt("Login_id"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginTO;
	}

}
