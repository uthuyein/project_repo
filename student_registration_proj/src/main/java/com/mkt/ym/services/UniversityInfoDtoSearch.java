package com.mkt.ym.services;

import static com.mkt.ym.utils.FactoryServices.emf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mkt.ym.entity.dto.UniversityInfoDto;

import jakarta.persistence.TypedQuery;

public abstract class UniversityInfoDtoSearch {

	public List<UniversityInfoDto> searchUniversityInfo(UniversityInfoDto dto) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		try (var em = emf.createEntityManager()) {

			StringBuilder sb = new StringBuilder("""
					select new com.mkt.ym.entity.dto.UniversityInfoDto(
					u.id.uniOpenYear,u.id.uniYear,u.id.major,u.id.rollNumber,
					s.name,s.email,s.primaryContact,s.secondaryContact,s.dob,s.image,s.nrc,s.religion,
					si.rollNum,si.totalMarks,
					p.fatherName,p.motherName,p.fatherNrc,p.motherNrc,
					a.city,a.township,a.street) from UniversityInfo u
					join u.student s
					join s.schoolInfo si
					join s.parent p
					join s.address a
					where 1=1
					""");

			if (null != dto) {
				System.out.println("Searching =====================    " +dto);

				if (dto.uniOpenYear() != null) {
					sb.append(" and u.id.uniOpenYear = :openYear");
					map.put("openYear", dto.uniOpenYear());
				}
				if (dto.uniYear() != null) {
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
			}
			TypedQuery<UniversityInfoDto> query = em.createQuery(sb.toString(), UniversityInfoDto.class);
			for (Map.Entry<String, Object> m : map.entrySet()) {
				query.setParameter(m.getKey(), m.getValue());
			}
			var list = query.getResultList();
			System.out.println(list);
			return list;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
