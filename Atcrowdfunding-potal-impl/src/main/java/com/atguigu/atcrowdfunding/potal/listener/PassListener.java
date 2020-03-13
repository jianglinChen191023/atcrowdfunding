/** 
 * Project Name:Atcrowdfunding-potal-impl 
 * File Name:PassListener.java 
 * Package Name:com.atguigu.atcrowdfunding.potal.listener 
 * Date:2019年6月13日下午9:47:45 
 * 
 */

package com.atguigu.atcrowdfunding.potal.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.ApplicationContextUtils;

/**
 * @ClassName: PassListener
 * @Description: 监听 实名认证审核流程(auth)
 * @author Chen Jiang Lin
 * @date 2019-06-13 21:47
 * 
 */
public class PassListener implements ExecutionListener {

//	不能直接进行自动装配,因为流程监听器对象是自己创建的.
//	@Autowired
//	private TicketService ticketService;

	/**
	 * TODO 实名认证审核通过 - 执行的操作
	 * 
	 * @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution)
	 */
	public void notify(DelegateExecution execution) throws Exception {

		// 获取会员id
		Integer memberid = (Integer) execution.getVariable("memberid");

		// W.怎么获取TicketService,MemberService对象? 
		// D.通过IOC容器
		
		// 1.1获取IOC容器
		//(1)ApplicationContext ioc = new ClassPathXmlApplicationContext("");不能自己创建IOC容器,要保证IOC容器的唯一性
		//(2)ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application);//需要获取application对象,可以使用流程变量,或ThreadLocal
		//(3)通过自定义的工具类,实现Spring接口,以接口注入的方式获取IOC容器对象
		ApplicationContext applicationContext = ApplicationContextUtils.applicationContext;
		
		//2.获取TicketService,MemberService对象
		TicketService ticketService = applicationContext.getBean(TicketService.class);
		MemberService memberService = applicationContext.getBean(MemberService.class);
		
		// 更新t_member表的authstatus字段: 1 -> 2 - 已实名认证
		Member member = memberService.getMemberById(memberid);
		member.setAuthstatus("2");
		memberService.updateauthstatus(member);
		
		// 更新t_ticket表的status字段 0 -> 1 表示流程结束
		ticketService.updateStatus(member);
		
	}

}
