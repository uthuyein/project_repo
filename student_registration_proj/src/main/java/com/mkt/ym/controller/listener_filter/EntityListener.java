package com.mkt.ym.controller.listener_filter;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class EntityListener {

	@PrePersist
	void beforeCreate(Object obj) {
		if(obj instanceof EnableTimesListener entity) {
			var times = entity.getTimes();
			if(null == times) {
				times = new Times();
				entity.setTimes(times);
			}
			times.setCreateTime(LocalDateTime.now());
		}
	}
	
	@PreUpdate
	void beforeUpdate(Object obj) {
		if(obj instanceof EnableTimesListener entity) {
			var times = entity.getTimes();
			if(null == times) {
				times = new Times();
				entity.setTimes(times);
			}
			times.setUpdateTime(LocalDateTime.now());
		}
	}
}
