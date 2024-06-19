package com.mkt.ym.controller.listener;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Times  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
}
