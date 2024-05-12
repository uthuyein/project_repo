package com.mkt.ym.services;

import com.mkt.ym.entity.SchoolInfo;
import com.mkt.ym.utils.CommonServices;

public interface SchoolInfoService extends CommonServices<SchoolInfo>{
	
	static SchoolInfoService getSchoolInfoService() {
		return new SchoolInfoServiceImpl();
	}

}
