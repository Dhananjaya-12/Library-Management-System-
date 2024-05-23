package com.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lms.utill.DBUtil;

public class ManageUserDAO {

	public int deleteUser(String username) {
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT `Login_id` FROM `login` where User_Name ='"+username+"'");
			while(resultSet.next()){
				int userId=resultSet.getInt("Login_id");
				statement.executeUpdate("DELETE FROM `userregistrations` WHERE `login_id`="+userId);
				statement.executeUpdate("DELETE FROM `login` WHERE `Login_id`="+userId);
				return 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}

}
