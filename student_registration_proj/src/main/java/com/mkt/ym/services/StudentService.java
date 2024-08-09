package com.mkt.ym.services;

import java.util.List;

import com.mkt.ym.entity.Student;
import com.mkt.ym.entity.dto.StudentDto;
import com.mkt.ym.services.impl.StudentServiceImpl;

public interface StudentService extends CommonServices<Student>{
	
	List<StudentDto> searchStudentDto(StudentDto dto);
	
	static StudentService getStudentService() {
		return new StudentServiceImpl();
	}

}
