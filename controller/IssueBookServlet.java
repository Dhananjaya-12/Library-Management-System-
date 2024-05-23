package com.lms.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.service.IssueBookService;
import com.lms.to.IssueBookTO;
import com.lms.to.LoginTO;

public class IssueBookServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	IssueBookTO issueBookTO = new IssueBookTO();
	String BookID=request.getParameter("BookID");
	DateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
	try {
		String Issue_DateText = request.getParameter("Issue_Date");
		String Return_DateText = request.getParameter("Return_Date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date Issue_Date = sdf.parse(Issue_DateText);
		Date Return_Date = sdf.parse(Return_DateText);
		issueBookTO.setBookID(BookID);
		issueBookTO.setIssueDate(Issue_Date);
		issueBookTO.setReturnDate(Return_Date);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	IssueBookService bookService= new IssueBookService();
	HttpSession session= request.getSession();
	LoginTO loginTO= (LoginTO) session.getAttribute("loginTO2");
	issueBookTO.setUserID(loginTO.getLoginID());
	
	
	issueBookTO=bookService.save(issueBookTO);
	if(issueBookTO.getIssueID() != 0){
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
