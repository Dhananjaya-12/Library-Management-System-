package com.lms.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.lms.service.LoginService;
import com.lms.service.UserRegistrationService;
import com.lms.to.LoginTO;
import com.lms.to.UserRegistrationsTO;

@MultipartConfig(maxFileSize = 16177215)
public class UserRegistrationServlet extends HttpServlet {
	private static final String SAVE_DIR="static";
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	            String savePath = "F:/LibraryManagementSystem/workspaces/CollegeLibraryManagement/WebContent/images" + File.separator + SAVE_DIR;
	                File fileSaveDir=new File(savePath);
	                if(!fileSaveDir.exists()){
	                    fileSaveDir.mkdir();
	                }
	                Part part=request.getPart("file");
	                String fileName=extractFileName(part);
	                /*if you may have more than one files with same name then you can calculate some random characters and append that characters in fileName so that it will  make your each image name identical.*/
	                part.write(savePath + File.separator + fileName);
		UserRegistrationsTO registrationsTO = new UserRegistrationsTO();
		registrationsTO.setFirstName(request.getParameter("first_name"));
		registrationsTO.setLastName(request.getParameter("last_name"));
		registrationsTO.setYearOfJoining(request.getParameter("yearOfJoining"));
		registrationsTO.setEmail(request.getParameter("email"));
		registrationsTO.setGender(request.getParameter("gender"));
		registrationsTO.setPhone(request.getParameter("phone"));
		registrationsTO.setDeoName(request.getParameter("depName"));
		registrationsTO.setRegistration_number(request.getParameter("regnum"));
		try {
			String dob = request.getParameter("dob");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dob_Date = sdf.parse(dob);
			registrationsTO.setDob(dob_Date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		registrationsTO.setPhoto(fileName);
		LoginTO loginTO = new LoginTO();
		loginTO.setUserName(request.getParameter("username"));
		loginTO.setPassword(request.getParameter("password"));
		InputStream inputStream = null; // input stream of the upload file
		LoginService loginService = new LoginService();
		LoginTO loginTO2 = loginService.checkLogin(loginTO);
		UserRegistrationService registrationService = new UserRegistrationService();
		if (loginTO2 == null) {
			loginTO.setLoginType("USER");
			loginTO.setStatus("A");
			loginTO = loginService.addLogin(loginTO);
			if (loginTO.getLoginID() != 0) {
				registrationsTO.setLoginID(loginTO.getLoginID());
				registrationsTO = registrationService.saveUser(registrationsTO);
				if (registrationsTO.getUserID() != 0) {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/UserRegistrationSuccess.jsp");
					dispatcher.forward(request, response);

				} else {
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
				}
			} else {

				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);

			}
		} else {

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

		}
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
