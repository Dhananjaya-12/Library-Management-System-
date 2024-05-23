package com.lms.utill;

import java.sql.Connection;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	try {
		Connection connection = DBUtil.getConnection();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	}

}
