package com.mkt.ym.entity;

import com.mkt.ym.controller.listener_filter.EnableTimesListener;
import com.mkt.ym.controller.listener_filter.Times;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "university_tbl")
@NoArgsConstructor
@RequiredArgsConstructor
public class UniversityInfo implements EnableTimesListener{
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@NonNull
	private UniversityInfoPK id;
	@ManyToOne
	@NonNull
	private Student student;
	@Column(columnDefinition = "boolean not null default true")
	private boolean active;
	private Times times;


}
