package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Member;
import java.util.List;
import java.util.Map;

public interface MemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Member record);

    Member selectByPrimaryKey(Integer id);
 
    List<Member> selectAll();

    int updateByPrimaryKey(Member record);

	Member getMemberLogin(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	void updateBasicinfo(Member loginMember);

	void updateEmail(Member loginMember);

	void updateauthstatus(Member loginMember);

	List<Map<String, Object>> queryCertByMemberid(Integer memberid);

	Member getMemberById(Integer memberid);
}