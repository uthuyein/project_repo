package com.mkt.ym.services;

import com.mkt.ym.entity.Student;
import com.mkt.ym.utils.CommonServices;

public interface StudentService extends CommonServices<Student>{
	
	static StudentService getStudentService() {
		return new StudentServiceImpl();
	}

}
