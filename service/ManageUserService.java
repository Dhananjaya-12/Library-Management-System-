package com.lms.service;

import com.lms.dao.ManageUserDAO;

public class ManageUserService {

	public int deleteUser(String username) {
		ManageUserDAO manageUserDAO= new ManageUserDAO();
		
		return manageUserDAO.deleteUser(username);
	}

}
