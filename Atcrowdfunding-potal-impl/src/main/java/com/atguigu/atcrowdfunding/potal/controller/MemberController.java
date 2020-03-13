/** 
 * Project Name:Atcrowdfunding-potal-impl 
 * File Name:MemberController.java 
 * Package Name:com.atguigu.atcrowdfunding.potal.controller 
 * Date:2019年6月12日下午3:55:52 
 * 
 */

package com.atguigu.atcrowdfunding.potal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.bean.Ticket;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.potal.listener.PassListener;
import com.atguigu.atcrowdfunding.potal.listener.RefuseListener;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.vo.Data;

/**
 * @ClassName: MemberController
 * @Description: 会员控制器
 * @author Chen Jiang Lin
 * @date 2019-06-12 15:55
 * 
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private CertService certService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@RequestMapping("/accttype")
	public String accttype() {

		return "member/accttype";
	}

	@RequestMapping("/basicinfo")
	public String basicinfo() {

		return "member/basicinfo";
	}

	@RequestMapping("/uploadCert")
	public String uploadCert(HttpSession session) {
		Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);
		/*
		 * 根据当前用户查询账户类型: SELECT id, NAME FROM t_cert WHERE id IN (SELECT certid FROM
		 * t_account_type_cert WHERE accttype = #{accttype}) 然后根据账户类型查找需要上传的图片
		 */
		List<Cert> queryCertByAccttype = certService.queryCertByAccttype(member.getAccttype());
		session.setAttribute("queryCertByAccttype", queryCertByAccttype);

		return "member/uploadCert";
	}

	@RequestMapping("/checkmail")
	public String checkmail() {

		return "member/checkmail";
	}

	@RequestMapping("/checkauthcode")
	public String checkauthcode() {

		return "member/checkauthcode";
	}

	@RequestMapping("/finishApply")
	@ResponseBody
	public Object finishApply(HttpSession session, String authcode) {
		AjaxResult result = new AjaxResult();
		try {
			// 获取登录会员信息
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);

			// 让当前系统用户完成:验证码审核任务.
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			if(ticket.getAuthcode().equals(authcode)) {
				//完成审核验证码任务
				Task task = taskService.createTaskQuery().processInstanceId(ticket.getPiid()).taskAssignee(loginMember.getLoginacct()).singleResult();
				taskService.complete(task.getId());

				//更新用户申请状态
				loginMember.setAuthstatus("1");
				memberService.updateauthstatus(loginMember);
				
				// 记录流程步骤
				ticket.setPstep("finishapply");
				ticketService.updatePiidAndPstep(ticket);
				
			}else {
				result.setSuccess(false);
				result.setMessage("验证码不正确,请重新输入!");
			}
			
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	@RequestMapping("/startProcess")
	@ResponseBody
	public Object startProcess(HttpSession session, String email) {
		AjaxResult result = new AjaxResult();

		try {

			// 获取登录会员信息
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);

			// 如果用户输入新的邮箱,将旧的邮箱地址替换
			if (loginMember.getEmail().equals(email)) {
				loginMember.setEmail(email);
				memberService.updateEmail(loginMember);
			}

			// 启动实名认证流程 - 系统自动发送邮件,生成验证码,验证邮箱地址是否合法(模拟:银行卡是否有效)
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionKey("auth").singleResult();

			/*
			 * ${toEmail } ${loginacct } ${flag } //审核实名认证时提供 ${passListener }
			 * ${refuseListener } ${authcode} 实名审核验证码
			 */
			StringBuilder authcode = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				authcode.append(new Random().nextInt(10));
			}

			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("toEmail", email);
			variables.put("authcode", authcode);
			variables.put("loginacct", loginMember.getLoginacct());
			variables.put("passListener", new PassListener());
			variables.put("refuseListener", new RefuseListener());

			// ProcessInstance processInstance =
			// runtimeService.startProcessInstanceByKey("auth");
			ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),
					variables);

			// 记录流程单
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			ticket.setPstep("checkEmail");
			ticket.setPiid(processInstance.getId().toString());
			ticket.setAuthcode(authcode.toString());
			ticketService.updatePiidAndPstep(ticket);

			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	@RequestMapping("/doUploadCert")
	@ResponseBody
	public Object doUploadCert(HttpSession session, Data ds) {
		AjaxResult result = new AjaxResult();

		try {

			// 获取登录会员信息
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);

			// 保存会员与资质的关系.
			// 真实路径
			String realPath = session.getServletContext().getRealPath("/pics");

			// 资质文件上传.
			List<MemberCert> memberCertImgs = ds.getMemberCertImgs();
			for (MemberCert memberCert : memberCertImgs) {
				// 获取文件
				MultipartFile multipartFile = memberCert.getImgfile();
				String extName = multipartFile.getOriginalFilename()
						.substring(multipartFile.getOriginalFilename().lastIndexOf("."));

				String tmpName = UUID.randomUUID().toString() + extName;
				String filename = realPath + "/cert/" + tmpName;

				multipartFile.transferTo(new File(filename)); // 上传

				// 准备数据
				memberCert.setIconpath(tmpName); // 封装数据,保存到数据库
				memberCert.setMemberid(loginMember.getId());
			}

			certService.saveMemberCert(memberCertImgs);

			// 记录流程单
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			ticket.setPstep("uploadcert");
			ticketService.updatePstep(ticket);

			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	@RequestMapping("/apply")
	public String apply(HttpSession session) {

		Member member = (Member) session.getAttribute(Const.LOGIN_MEMBER);

		Ticket ticket = ticketService.getTicketByMemberId(member.getId());

		if (ticket == null) {
			ticket = new Ticket(); // 封装数据
			ticket.setMemberid(member.getId());
			ticket.setPstep("apply");
			ticket.setStatus("0");

			ticketService.saveTicket(ticket);

		} else {
			String pstep = ticket.getPstep();

			if ("accttype".equals(pstep)) {

				return "redirect:/member/basicinfo.htm";
			} else if ("basicinfo".equals(pstep)) {

				/*
				 * 根据当前用户查询账户类型: SELECT id, NAME FROM t_cert WHERE id IN (SELECT certid FROM
				 * t_account_type_cert WHERE accttype = #{accttype}) 然后根据账户类型查找需要上传的图片
				 */
				List<Cert> queryCertByAccttype = certService.queryCertByAccttype(member.getAccttype());
				session.setAttribute("queryCertByAccttype", queryCertByAccttype);

				return "redirect:/member/uploadCert.htm";
			} else if ("uploadcert".equals(pstep)) {

				return "redirect:/member/checkmail.htm";
			} else if ("checkEmail".equals(pstep)) {

				return "redirect:/member/checkauthcode.htm";
			}

		}

		return "member/accttype";
	}

	/**
	 * 更新账户类型
	 */
	@ResponseBody
	@RequestMapping("/updateAcctType")
	public Object updateAcctType(HttpSession session, String accttype) {
		AjaxResult result = new AjaxResult();

		try {

			// 获取登录会员信息
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setAccttype(accttype);
			// 更新账户类型
			memberService.updateAcctType(loginMember);

			// 记录流程单
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			ticket.setPstep("accttype");
			ticketService.updatePstep(ticket);

			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 更新基本信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo(HttpSession session, Member member) {
		AjaxResult result = new AjaxResult();

		try {

			// 获取登录会员信息
			Member loginMember = (Member) session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());

			loginMember.setTel(member.getTel());
			// 更新会员的基本信息
			memberService.updateBasicinfo(loginMember);

			// 记录流程单
			Ticket ticket = ticketService.getTicketByMemberId(loginMember.getId());
			ticket.setPstep("basicinfo");
			ticketService.updatePstep(ticket);

			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

}
