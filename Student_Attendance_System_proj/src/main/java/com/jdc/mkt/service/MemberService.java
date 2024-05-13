package com.jdc.mkt.service;

import java.util.List;

import com.jdc.mkt.ds.Member;

public interface MemberService {

	void saveMember(Member m);
	List<Member>getMember(int id ,String name);
	void updateMember(Member m);
	void deleteMember(int id);
	
}
