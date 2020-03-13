package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Ticket;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    Ticket selectByPrimaryKey(Integer id);
 
    List<Ticket> selectAll();

    int updateByPrimaryKey(Ticket record);

	Ticket getTicketByMemberId(Integer memberid);

	void updatePstep(Ticket ticket);

	void updatePiidAndPstep(Ticket ticket);

	Member getMemberByPiid(@Param("processInstanceId")String processInstanceId);

	void updateStatus(Member member);

}