package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.service.DepartmentService;
import com.lms.to.DepartmentTO;

public class DepartmentServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
DepartmentTO departmentTO = new DepartmentTO();
departmentTO.setDeptName(request.getParameter("dept_name"));
DepartmentService departmentService= new DepartmentService();
departmentTO=departmentService.addDept(departmentTO);

if(departmentTO.getDept_id() != 0){

	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/departmentAddSuccess.jsp");
	dispatcher.forward(request, response);

}else{


	RequestDispatcher dispatcher = request
			.getRequestDispatcher("/index.jsp");
	dispatcher.forward(request, response);



}
}
}
