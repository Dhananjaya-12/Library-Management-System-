package com.lms.service;

import com.lms.dao.IssueBookDAO;
import com.lms.to.IssueBookTO;

public class IssueBookService {

	public IssueBookTO save(IssueBookTO issueBookTO) {
IssueBookDAO bookDAO= new IssueBookDAO();

		return bookDAO.save(issueBookTO);
	}

}
