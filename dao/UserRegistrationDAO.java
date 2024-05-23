package com.lms.dao;

import com.lms.to.UserRegistrationsTO;
import com.lms.utill.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class UserRegistrationDAO {

	public UserRegistrationsTO saveUser(UserRegistrationsTO userRegistrationsTO) {
		try {
			Connection connection = DBUtil.getConnection();
			String sql = "INSERT INTO userregistrations (FirstName,LastName,YearOfJoining,Email,Gender,phone,login_id,photo,dept_id,dob,registration_number) values (?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, userRegistrationsTO.getFirstName());
			statement.setString(2, userRegistrationsTO.getLastName());
			statement.setString(3, userRegistrationsTO.getYearOfJoining());
			statement.setString(4, userRegistrationsTO.getEmail());
			statement.setString(5, userRegistrationsTO.getGender());
			statement.setString(6, userRegistrationsTO.getPhone());
			statement.setInt(7, userRegistrationsTO.getLoginID());
			statement.setString(8, userRegistrationsTO.getPhoto());
statement.setString(9, userRegistrationsTO.getDeoName());
statement.setDate(10, new Date(userRegistrationsTO.getDob().getTime()));
statement.setString(11, userRegistrationsTO.getRegistration_number());
			int a = statement.executeUpdate();
			if (a == 1) {
				ResultSet resultSet = statement
						.executeQuery("SELECT `user_id` FROM `userregistrations` ORDER BY user_id DESC LIMIT 1");
				while (resultSet.next()) {
					userRegistrationsTO.setUserID(resultSet.getInt("user_id"));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userRegistrationsTO;
	}

}
