package com.lms.service;

import com.lms.dao.UserRegistrationDAO;
import com.lms.to.UserRegistrationsTO;

public class UserRegistrationService {

	public UserRegistrationsTO saveUser(UserRegistrationsTO userRegistrationsTO){
		UserRegistrationDAO registrationDAO = new UserRegistrationDAO();
		return registrationDAO.saveUser(userRegistrationsTO);
	}
}
