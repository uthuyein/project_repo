package com.mkt.ym.services;

import com.mkt.ym.entity.Address;
import com.mkt.ym.utils.CommonServices;

public interface AddressService extends CommonServices<Address>{
	
	static AddressService getAddressService() {
		return new AddressServiceImpl();
	}

}
