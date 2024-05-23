package com.lms.dao;
import java.sql.Date;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lms.to.IssueBookTO;
import com.lms.utill.DBUtil;

public class IssueBookDAO {

	public IssueBookTO save(IssueBookTO issueBookTO) {
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			String sql = "insert into issuebook(Issue_Date,user_id,Return_Date,Book_ID)VALUES("
					+ "'"
					+ new Date(issueBookTO.getIssueDate().getTime())
					+ "'"
					+ ","
					+ issueBookTO.getUserID()
					+ ","
					+ "'"
					+ new Date(issueBookTO.getReturnDate().getTime())
					+ "'"
					+ ","
					+ "'"
					+ issueBookTO.getBookID()
					+ "'"
					+ ")";

			int a = statement.executeUpdate(sql);
			if (a == 1) {
				ResultSet resultSet = statement
						.executeQuery("SELECT `Issue_id` FROM `issuebook` ORDER BY Issue_id DESC LIMIT 1");
				while (resultSet.next()) {
					issueBookTO.setIssueID(resultSet.getInt("Issue_id"));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return issueBookTO;
	}

}
