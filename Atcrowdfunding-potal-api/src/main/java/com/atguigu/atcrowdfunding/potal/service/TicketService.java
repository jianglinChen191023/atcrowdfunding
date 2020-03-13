/** 
 * Project Name:Atcrowdfunding-potal-api 
 * File Name:TicketService.java 
 * Package Name:com.atguigu.atcrowdfunding.potal.service 
 * Date:2019年6月14日上午9:03:45 
 * 
 */  
      
package com.atguigu.atcrowdfunding.potal.service;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Ticket;

/** 
* @ClassName: TicketService 
* @Description: 流程记录接口
* @author Chen Jiang Lin 
* @date 2019-06-14 09:03
*  
*/
public interface TicketService {

	Ticket getTicketByMemberId(Integer id);

	void saveTicket(Ticket ticket);

	void updatePstep(Ticket ticket);

	void updatePiidAndPstep(Ticket ticket);

	Member getMemberByPiid(String processInstanceId);

	void updateStatus(Member member);


	
	
}
  
