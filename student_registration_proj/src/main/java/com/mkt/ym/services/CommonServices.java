package com.mkt.ym.services;

import java.util.List;

public interface CommonServices<T> {

	void save(T t);
	int update(T t);
	int delete(T t);
	List<T> search(T t);
}
