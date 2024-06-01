package com.mkt.ym.services;

import java.util.Optional;

import com.mkt.ym.entity.Student;
import com.mkt.ym.utils.CommonServices;

public interface StudentService extends CommonServices<Student>{
	
	//Optional<Student> getStudent(String name, String nrc);
	
	static StudentService getStudentService() {
		return new StudentServiceImpl();
	}

}
