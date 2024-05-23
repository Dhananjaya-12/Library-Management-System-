package com.lms.service;

import com.lms.dao.ReturnBookDAO;

public class ReturnBookService {

	public int removeBook(String bookId, int userID) {
ReturnBookDAO bookDAO= new ReturnBookDAO();
		return bookDAO.removeBook(bookId, userID);
	}

}
