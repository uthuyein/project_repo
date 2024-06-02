package com.mkt.ym.services.impl;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkt.ym.entity.dto.StudentDto;

import jakarta.persistence.TypedQuery;

public abstract class StudentDtoSearchImpl {

	public List<StudentDto> searchStudentDto(StudentDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		try (var em = emf.createEntityManager()) {

			StringBuilder sb = new StringBuilder("""
					select new com.mkt.ym.entity.dto.StudentDto(
					s.id,s.name,s.email,s.primaryContact,s.secondaryContact,s.dob,s.image,s.nrc,s.religion,
					si.id,si.rollNum,si.totalMarks,
					p.id,p.fatherName,p.motherName,p.fatherNrc,p.motherNrc,
					a.id,a.city,a.township,a.street
					) 
					from Student s
					join s.schoolInfo si
					join s.parent p
					join s.address a
					where 1=1
					""");

			if (null != dto) {
				
				if (dto.name() != null && !dto.name().isEmpty()) {
					sb.append(" and s.name = :name");
					map.put("name", dto.name());
				}
				if(null != dto.city()) {
					sb.append(" and a.city = :city");
					map.put("city", dto.city());
				}
				if(null != dto.township()) {
					sb.append(" and a.township = :township");
					map.put("township", dto.township());
				}
				if(null != dto.dob()) {
					sb.append(" and s.dob = :dob");
					map.put("dob", dto.dob());
				}
				if(null != dto.nrc()) {
					sb.append(" and s.nrc = :nrc");
					map.put("nrc", dto.nrc());
				}
				if(null != dto.fNrc()) {
					sb.append(" and p.fatherNrc = :fNrc");
					map.put("fNrc", dto.fNrc());
				}
				if(null != dto.mNrc()) {
					sb.append(" and p.motherNrc = :mNrc");
					map.put("mNrc", dto.mNrc());
				}
				if(null != dto.rollNum()) {
					sb.append(" and si.rollNum = :schRoll");
					map.put("schRoll", dto.rollNum());
				}
				if(null != dto.totalMarks()) {
					sb.append(" and si.totalMarks = :schMarks");
					map.put("schMarks", dto.totalMarks());
				}				
			}
			TypedQuery<StudentDto> query = em.createQuery(sb.toString(), StudentDto.class);
			for (Map.Entry<String, Object> m : map.entrySet()) {
				query.setParameter(m.getKey(), m.getValue());
			}
			return query.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
