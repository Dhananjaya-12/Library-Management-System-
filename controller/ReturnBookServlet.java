package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.service.ReturnBookService;
import com.lms.to.LoginTO;

public class ReturnBookServlet extends HttpServlet {
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
String BookId= request.getParameter("BookId");
HttpSession session= request.getSession();
LoginTO loginTO= (LoginTO) session.getAttribute("loginTO2");

int userID=loginTO.getLoginID();
ReturnBookService bookService= new ReturnBookService();
int validate=bookService.removeBook(BookId,userID);
if(validate != 0){
	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/userLoginSuccess.jsp");
	dispatcher.forward(request, response);

}else{


	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/index.html");
	dispatcher.forward(request, response);



}
}
}
