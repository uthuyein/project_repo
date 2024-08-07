package com.mkt.ym.services;

import com.mkt.ym.entity.Account;
import com.mkt.ym.services.impl.AccountServiceImpl;

public interface AccountService extends CommonServices<Account>{

	public static AccountService getAccountService() {
		return new AccountServiceImpl();
	}
}
