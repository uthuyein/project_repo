package com.mkt.ym.services;

import com.mkt.ym.entity.Messenger;
import com.mkt.ym.services.impl.MessengerServiceImpl;
import com.mkt.ym.utils.CommonServices;

public interface MessengerService extends CommonServices<Messenger>{

	public static MessengerService getMessengerService() {
		return new MessengerServiceImpl();
	}
}
