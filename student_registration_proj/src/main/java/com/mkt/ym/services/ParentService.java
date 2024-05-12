package com.mkt.ym.services;

import com.mkt.ym.entity.Parent;
import com.mkt.ym.utils.CommonServices;

public interface ParentService  extends CommonServices<Parent>{
	
	static ParentService getParentService() {
		return new ParentServiceImpl();
	}

}
