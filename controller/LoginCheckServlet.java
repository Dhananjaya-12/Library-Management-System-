package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.service.LoginService;
import com.lms.to.LoginTO;

public class LoginCheckServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("servlet path *************"+request.getServletPath());
		System.out.println("context path **************"+ request.getContextPath());
		System.out.println(getServletContext().getRealPath(""));
		
		System.out.println("inside a LoginCheckServlet dopost");
		LoginTO loginTO= new LoginTO();
		loginTO.setUserName(request.getParameter("username"));
		loginTO.setPassword(request.getParameter("password"));
		LoginService loginService= new LoginService();
		LoginTO loginTO2=loginService.checkLogin(loginTO);
if(loginTO2 == null){

	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);


}else{
	if(loginTO2.getLoginType().equalsIgnoreCase("USER")){
HttpSession session= request.getSession();
session.setAttribute("loginTO2", loginTO2);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/userLoginSuccess.jsp");
		dispatcher.forward(request, response);

	
	}else{

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/adminLoginSuccess.jsp");
		dispatcher.forward(request, response);

			
	}
}

	}
}
