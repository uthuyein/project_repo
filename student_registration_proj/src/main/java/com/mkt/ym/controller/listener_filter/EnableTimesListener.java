package com.mkt.ym.controller.listener_filter;

import java.io.Serializable;

public interface EnableTimesListener extends Serializable{

	void setTimes(Times times);
	Times getTimes();
}
