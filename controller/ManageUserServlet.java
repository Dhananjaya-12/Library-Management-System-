package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.service.ManageUserService;

public class ManageUserServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username=request.getParameter("user_name");
		ManageUserService manageUserService= new ManageUserService();
		int res=manageUserService.deleteUser(username);
		if(res != 0 ){

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
