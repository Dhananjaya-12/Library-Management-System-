package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.service.AddBookService;

public class RemoveBookServlet extends HttpServlet {
 @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	 AddBookService addBookService= new AddBookService();
int noDel = new Integer(request.getParameter("noDel"));
String bookID = request.getParameter("bookID");
int validate=addBookService.removeBook(noDel,bookID);
if(validate != 0 ){

	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/adminLoginSuccess.jsp");
	dispatcher.forward(request, response);

}else{


	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/index.html");
	dispatcher.forward(request, response);



}

 }
}
