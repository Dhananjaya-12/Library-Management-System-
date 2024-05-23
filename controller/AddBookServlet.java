package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.service.AddBookService;
import com.lms.to.BookTO;

public class AddBookServlet extends HttpServlet{

	BookTO bookTO= new BookTO();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddBookService addBookService= new AddBookService();
		bookTO.setBookID(request.getParameter("BookID"));
		bookTO.setBookName(request.getParameter("book_name"));
		bookTO.setAuther(request.getParameter("auther"));
bookTO.setQuantity(new Integer(request.getParameter("quality")));
bookTO.setDeptName(request.getParameter("depName"));
bookTO.setBookRack(request.getParameter("rack"));
bookTO.setEdition(request.getParameter("ey"));
bookTO.setPublisher(request.getParameter("pp"));
bookTO.setBillnumber(request.getParameter("bnd"));
bookTO.setCost(request.getParameter("cost"));
bookTO.setIsbnumber(request.getParameter("isb"));
bookTO=addBookService.saveBook(bookTO);
if(bookTO != null ){

	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/adminLoginSuccess.jsp");
	dispatcher.forward(request, response);

}else{


	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);



}
	}
	
}
