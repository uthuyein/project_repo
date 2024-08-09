package com.mkt.ym.entity;

import java.util.ArrayList;
import java.util.List;

import com.mkt.ym.controller.listener.EnableTimesListener;
import com.mkt.ym.controller.listener.Times;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "university_tbl")
@NoArgsConstructor
public class UniversityInfo implements EnableTimesListener {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@Column(unique = true, nullable = false)
	private UniversityInfoPK id;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Student student;
	@Column(nullable = false, columnDefinition = "boolean default true")
	private boolean active = true;
	private Times times;
	
	@OneToMany(mappedBy = "uniInfo", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Messenger> messengers = new ArrayList<Messenger>();

	public UniversityInfo(UniversityInfoPK id, Student student) {
		this.id = id;
		this.student = student;
	}

	public void addMessage(Messenger message) {
		message.setUniInfo(this);
		messengers.add(message);
	}

}
