package com.jdc.mkt.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.jdc.mkt.ds.Member;
import com.jdc.mkt.ds.Role;
import com.jdc.mkt.service.MemberService;
import static com.jdc.mkt.utils.ConnectionManager.getConnector;

public class MemberServiceImpl implements MemberService {

	@Override
	public void saveMember(Member m) {
		String sql = "insert into member_tbl (name,password,isActive,role)values(?,?,?,?)";

		try (var con = getConnector(); var stmt = con.prepareStatement(sql)) {

			stmt.setString(1, m.getName());
			stmt.setString(2, m.getPassword());
			stmt.setBoolean(3, m.isActive());
			stmt.setString(4, m.getRole().name());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Member> getMember(int id,String name) {
		StringBuffer sql = new StringBuffer( "select * from member_tbl where isActive=true ");
		List<Member> list = new ArrayList<>();
		List<Object> temp = new ArrayList<>();
		
		if(id > 0) {
			sql.append(" and id =?");
			temp.add(id);
		}
		
		if(null != name) {
			sql.append(" and name =?");
			temp.add(name);
		}
		try (var con = getConnector(); var stmt = con.prepareStatement(sql.toString())) {

			for(int i = 0 ; i < temp.size() ; i++) {
				stmt.setObject(i+1, temp.get(i));
			}
			
			var rs = stmt.executeQuery();

			while (rs.next()) {
				Member m = new Member();
				m.setId(rs.getInt("id"));
				m.setName(rs.getString("name"));
				m.setPassword(rs.getString("password"));
				m.setRole(Role.valueOf(rs.getString("role")));
				list.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateMember(Member m) {
		String sql = "update member_tbl set password=?,role=? where id =?";
		try (var con = getConnector(); var stmt = con.prepareStatement(sql)) {
			stmt.setString(1, m.getPassword());
			stmt.setString(2, m.getRole().name());
			stmt.setInt(3, m.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteMember(int id) {
		String sql = "update member_tbl set isActive=false where id =?";
		try (var con = getConnector(); var stmt = con.prepareStatement(sql)) {
			stmt.setInt(1,id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
