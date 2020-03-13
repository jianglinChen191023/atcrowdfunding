/** 
 * Project Name:Atcrowdfunding-potal-impl 
 * File Name:TicketServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.potal.service.impl 
 * Date:2019年6月14日上午9:04:22 
 * 
 */  
      
package com.atguigu.atcrowdfunding.potal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Ticket;
import com.atguigu.atcrowdfunding.potal.dao.TicketMapper;
import com.atguigu.atcrowdfunding.potal.service.TicketService;

/** 
* @ClassName: TicketServiceImpl 
* @Description: 流程记录实现类 
* @author Chen Jiang Lin 
* @date 2019-06-14 09:04
*  
*/
@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;

	/** 
	* TODO 查询流程单
	* @see com.atguigu.atcrowdfunding.potal.service.TicketService#getTicketByMemberId(java.lang.Integer) 
	*/
	public Ticket getTicketByMemberId(Integer id) {

		return ticketMapper.getTicketByMemberId(id); 
	}

	/** 
	* TODO 保存信息
	* @see com.atguigu.atcrowdfunding.potal.service.TicketService#saveTicket(com.atguigu.atcrowdfunding.bean.Ticket) 
	*/
	public void saveTicket(Ticket ticket) {

		ticketMapper.insert(ticket);
	}

	/** 
	* TODO 记录流程步骤
	* @see com.atguigu.atcrowdfunding.potal.service.TicketService#updatePstep(com.atguigu.atcrowdfunding.bean.Ticket) 
	*/
	public void updatePstep(Ticket ticket) {

		ticketMapper.updatePstep(ticket);
	}

	/** 
	* TODO 更新ticket
	* @see com.atguigu.atcrowdfunding.potal.service.TicketService#updatePiidAndPstep(com.atguigu.atcrowdfunding.bean.Ticket) 
	*/
	public void updatePiidAndPstep(Ticket ticket) {

		ticketMapper.updatePiidAndPstep(ticket);
	}

	/** 
	* TODO 根据流程实例的id查询流程单,查询用户的信息
	* @see com.atguigu.atcrowdfunding.potal.service.TicketService#getMemberByPiid(java.lang.String) 
	*/
	public Member getMemberByPiid(String processInstanceId) {

		return ticketMapper.getMemberByPiid(processInstanceId); 
	}

	/** 
	* TODO 通过流程 - status 0 -> 1 结束流程
	* @see com.atguigu.atcrowdfunding.potal.service.TicketService#updateStatus(com.atguigu.atcrowdfunding.bean.Member) 
	*/
	public void updateStatus(Member member) {

		ticketMapper.updateStatus(member);
	}
	
}
  
