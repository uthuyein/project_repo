package com.mkt.ym.utils;

import java.util.List;

public interface CommonServices<T> {

	void save(T t);
	int update(T t);
	List<T> search(T t);
}
