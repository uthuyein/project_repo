package com.mkt.ym.entity;

import com.mkt.ym.controller.listener.EnableTimesListener;
import com.mkt.ym.controller.listener.Times;

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
	@NonNull
	@EmbeddedId
	@Column(unique = true ,nullable = false)
	private UniversityInfoPK id;
	@NonNull
	@ManyToOne
	private Student student;
	@Column(nullable = false,columnDefinition = "boolean default true")
	private boolean active = true;
	private Times times;


}
