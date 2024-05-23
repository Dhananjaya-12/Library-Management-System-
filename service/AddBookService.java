package com.lms.service;

import com.lms.dao.AddBookDAO;
import com.lms.to.BookTO;

public class AddBookService {

	public BookTO saveBook(BookTO bookTO) {
		AddBookDAO addBookDAO= new AddBookDAO();
		// TODO Auto-generated method stub
		return addBookDAO.save(bookTO);
	}

	public int removeBook(int noDel, String bookID) {
		// TODO Auto-generated method stub
		AddBookDAO addBookDAO= new AddBookDAO();
		return addBookDAO.removeBook(noDel,bookID);
	}

}
