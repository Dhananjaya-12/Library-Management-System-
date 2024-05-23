package com.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lms.utill.DBUtil;

public class ReturnBookDAO {

	public int removeBook(String bookId, int userID) {
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			int a=	statement.executeUpdate("DELETE FROM `issuebook` WHERE `Book_ID`= '"+bookId+"' and user_id = "+userID);
				return a;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}

}
