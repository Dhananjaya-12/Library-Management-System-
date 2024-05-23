package com.lms.service;

import com.lms.dao.DepartmentDAO;
import com.lms.to.DepartmentTO;

public class DepartmentService {

	public DepartmentTO addDept(DepartmentTO departmentTO) {
		DepartmentDAO dao= new DepartmentDAO();
		return dao.addDept(departmentTO);
	}

}
