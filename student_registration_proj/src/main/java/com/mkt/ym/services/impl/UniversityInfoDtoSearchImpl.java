package com.mkt.ym.services.impl;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkt.ym.entity.dto.UniversityInfoDto;

import jakarta.persistence.TypedQuery;

public abstract class UniversityInfoDtoSearchImpl {

	public List<UniversityInfoDto> searchUniversityInfo(UniversityInfoDto dto) {
		StringBuilder sb = new StringBuilder("""
				select new com.mkt.ym.entity.dto.UniversityInfoDto(
				u.id.openYear,u.id.uniYear,u.id.major,u.id.rollNumber,	
				s.id,s.name,s.email,s.primaryContact,s.secondaryContact,s.dob,s.image,s.nrc,s.religion,
				si.id,si.rollNum,si.totalMarks,
				p.id,p.fatherName,p.motherName,p.fatherNrc,p.motherNrc,
				a.id,a.city,a.township,a.street) from UniversityInfo u
				join u.student s
				join s.schoolInfo si
				join s.parent p
				join s.address a
				where u.active = true
				""");
		
		Map<String, Object> map = new HashMap<String, Object>();	
		try (var em = emf.createEntityManager()) {
			
			if (null != dto) {
				
				if(null != dto.rollNumber()) {
					sb.append(" and u.id.rollNumber = :roll");
					map.put("roll", dto.rollNumber());
				}
				if (dto.openYear() != null ) {
					sb.append(" and u.id.openYear = :openYear");	
					map.put("openYear", dto.openYear());
				}
				
				if (dto.uniYear() != null ) {
					sb.append(" and u.id.uniYear = :uniYear");
					
					map.put("uniYear", dto.uniYear());
				}
				if (dto.major() != null) {
					sb.append(" and u.id.major = :major");	
					map.put("major", dto.major());
				}
				
				if (dto.name() != null && !dto.name().isEmpty()) {
					sb.append(" and s.name = :name");
					map.put("name", dto.name());
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
				if(null != dto.schoolRollnum()) {
					sb.append(" and si.rollNum = :schRoll");
					map.put("schRoll", dto.schoolRollnum());
				}
				if(null != dto.schoolTotalMarks()) {
					sb.append(" and si.totalMarks = :schMarks");
					map.put("schMarks", dto.schoolTotalMarks());
				}
				
							
			}
			
			TypedQuery<UniversityInfoDto> query = em.createQuery(sb.toString(), UniversityInfoDto.class);
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
