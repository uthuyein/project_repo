package com.mkt.ym.services;

import java.util.List;

import com.mkt.ym.entity.UniversityInfo;
import com.mkt.ym.entity.dto.UniversityInfoDto;
import com.mkt.ym.services.impl.UniversityInfoServiceImpl;
import com.mkt.ym.utils.CommonServices;

public interface UniversityInfoService extends CommonServices<UniversityInfo>{

	List<UniversityInfoDto> searchUniversityInfo(UniversityInfoDto t);
	
	public static UniversityInfoService getUniversityInfoService() {
		return new UniversityInfoServiceImpl();
	}
}
