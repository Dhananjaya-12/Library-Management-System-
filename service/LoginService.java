package com.lms.service;

import com.lms.dao.LoginDAO;
import com.lms.to.LoginTO;

public class LoginService {

	public LoginTO checkLogin(LoginTO loginTO){
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.checkLogin(loginTO);
	}
	public LoginTO addLogin(LoginTO loginTO) {
		LoginDAO loginDAO = new LoginDAO();
		return loginDAO.addLogin(loginTO);
	}
}
