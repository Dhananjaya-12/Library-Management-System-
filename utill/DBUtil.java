package com.lms.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class DBUtil {
	static Connection connection=null;
	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/librarymanagement", "root", "root");
		 System.out.println("+++++++++++++" + connection);
		//	DBUtil.checkIssueDate();
		return connection;
	}
	
	public static void checkIssueDate(){

		   String result;
		   // Recipient's email ID needs to be mentioned.
		   final String fromEmail = "gubbiprasana@gmail.com"; //requires valid gmail id
		   final String password = "9591657988"; // correct password for gmail id
		   
		   String toEmail = null; // can be any email id 
		    
		   System.out.println("TLSEmail Start");
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		   props.put("mail.smtp.port", "587"); //TLS Port
		   props.put("mail.smtp.auth", "true"); //enable authentication
		   props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		    
		           //create Authenticator object to pass in Session.getInstance argument
		   Authenticator auth = new Authenticator() {
		       //override the getPasswordAuthentication method
		       protected PasswordAuthentication getPasswordAuthentication() {
		           return new PasswordAuthentication(fromEmail, password);
		       }
		   };
		   try {
			   String todayDate = null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				todayDate = dateFormat.format(date);
			Statement statement2 = connection.createStatement();
			ResultSet resultSet = statement2.executeQuery("SELECT r.`Email`, r.`FirstName` ,b.`BookName`,i.`Return_Date` FROM `issuebook` i JOIN `login` l ON i.`user_id`=l.`Login_id` JOIN `userregistrations` r ON l.`Login_id`=r.`login_id`  JOIN `book` b ON i.`Book_ID`=b.`Book_ID` WHERE `Return_Date` <= '"+todayDate+"'"); 
		while(resultSet.next()){
			toEmail=resultSet.getString(1);
			Session ses = Session.getInstance(props, auth);
			EmailUtill.sendEmail(ses, toEmail,"Collage Library Management System Notification Return Book", "Hello "+resultSet.getString(2)+" the Library Book is not returnrd Book Name is "+resultSet.getString(3)+" Return Date is "+resultSet.getString(4)+"Please Come and return the book ");
		}
		   } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}
}
