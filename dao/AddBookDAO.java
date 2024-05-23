package com.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.lms.to.BookTO;
import com.lms.utill.DBUtil;

public class AddBookDAO {

	public BookTO save(BookTO bookTO) {
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			if(bookTO.getBookID() != null){
				Statement statement2= connection.createStatement();
				ResultSet resultSet= statement2.executeQuery("SELECT `Quantity` FROM `book`  WHERE Book_ID='"+bookTO.getBookID()+"'");
				int updateQuantity = 0;
				while (resultSet.next()) {
					updateQuantity=(resultSet.getInt("Quantity")+bookTO.getQuantity());
					
				}
		int a=	statement.executeUpdate("UPDATE `book` SET `Quantity`= "+updateQuantity+" WHERE `Book_ID`='"+bookTO.getBookID()+"'");
		if(a != 1){
			return null;
		}
			}else{
			ResultSet resultSet = statement
					.executeQuery("SELECT `Book_ID` FROM `book` ORDER BY Book_ID DESC LIMIT 1");
			String existingBookID=null;
			while (resultSet.next()) {
				String book_id=resultSet.getString("Book_ID");
				String[] book=book_id.split("_");
				existingBookID=book[1];
			}
			if(existingBookID == null){
				existingBookID=bookTO.getBookName()+"_"+"1";
			}else{
				int b_id= ((new Integer(existingBookID))+1);
				
				existingBookID=bookTO.getBookName()+"_"+String.valueOf(b_id);
			}
			statement.executeUpdate("insert into book (Book_ID,BookName,Quantity,Auther,ststus,Dept_ID,Book_Rack,edition,publisher,billnumber,cost,isbnumber) values("
+ "'"
+ existingBookID
+ "'"
+ ","
+ "'"
+ bookTO.getBookName()
+ "'"
+ ","
+ "'"
+ bookTO.getQuantity()
+ "'"
+ ","
+ "'"
+ bookTO.getAuther()
+ "'"
+ ","
+ "'"
+ "Active"
+ "' , '"+bookTO.getDeptName()
 + " ' , '"+bookTO.getBookRack()+"' , '"+bookTO.getEdition()+"' , '"+bookTO.getPublisher()+"' , '"+bookTO.getBillnumber()+"' , '"+bookTO.getCost()+"' , '"+bookTO.getIsbnumber()+"')");

			ResultSet resultSet1 = statement
					.executeQuery("SELECT `Book_ID` FROM `book` ORDER BY Book_ID DESC LIMIT 1");
			while(resultSet1.next()){
				bookTO.setBookID(resultSet1.getString("Book_ID"));
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return bookTO;
	}

	public int removeBook(int noDel, String bookID) {
		try {
			Connection connection = DBUtil.getConnection();
			Statement statement = connection.createStatement();
			int remaining=0;
		ResultSet resultSet=	statement.executeQuery("SELECT `Quantity` FROM `book` where Book_ID = '"+bookID+"'");
		while(resultSet.next()){
			remaining=((resultSet.getInt("Quantity"))-noDel);
		}
		int a=	statement.executeUpdate("UPDATE `book` SET `Quantity`= "+remaining+" WHERE `Book_ID`='"+bookID+"'");
		
			return a;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}

}
