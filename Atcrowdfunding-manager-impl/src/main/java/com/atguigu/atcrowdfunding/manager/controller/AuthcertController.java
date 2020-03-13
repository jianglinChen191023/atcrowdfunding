/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:AuthcertController.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.controller 
 * Date:2019年6月17日上午8:40:34 
 * 
 */

package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.potal.service.TicketService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;

/**
 * @ClassName: AuthcertController
 * @Description: 实名认证
 * @author Chen Jiang Lin
 * @date 2019-06-17 08:40
 * 
 */
@Controller
@RequestMapping("/authcert")
public class AuthcertController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private MemberService memberServer;

	@RequestMapping("/index")
	public String index() {

		return "authcert/index";
	}
	
	@RequestMapping("/show")
	public String show(Integer memberid,String taskid,Map<String, Object> map) {

		try {
			Member member = memberServer.getMemberById(memberid);

			List<Map<String, Object>> list = memberServer.queryCertByMemberid(memberid);

			map.put("member", member);
			map.put("certimgs", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "authcert/show";
	} 
	
	@ResponseBody
	@RequestMapping("/pass")
	public Object pass(String taskid, Integer memberid) {
		AjaxResult result = new AjaxResult();
		
		try {
			taskService.setVariable(taskid, "flag", true);
			System.out.println("MEMBERID"+memberid);
			taskService.setVariable(taskid, "memberid", memberid);
			//传递参数,让流程继续
			taskService.complete(taskid);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMessage("审批异常!");
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/refuse")
	public Object refuse(String taskId, Integer memberid) {
		AjaxResult result = new AjaxResult();
		
		try {
			taskService.setVariable(taskId, "flag", false);
			taskService.setVariable(taskId, "memberid", memberid);
			//传递参数,让流程继续
			taskService.complete(taskId);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMessage("审批异常!");
			result.setSuccess(false);
			e.printStackTrace();
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize) {
		AjaxResult result = new AjaxResult();

		try {

			Page page = new Page(pageno, pagesize);

			// 1.查询后台backuser委托组任务
			TaskQuery taskQuery = taskService.createTaskQuery();
			List<Task> listPage = taskQuery.processDefinitionKey("auth").taskCandidateGroup("backuser")
					.listPage(page.getStartIndex(), pagesize);

			// 2.根据任务查询流程定义(流程定义的名称,流程定义的版本)
			List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

			for (Task task : listPage) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", task.getId());
				map.put("name", task.getName());

				// 开始根据任务查询流程定义
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
						.processDefinitionId(task.getParentTaskId()).singleResult();

				map.put("procDefName", processDefinition.getName());
				map.put("procDefVersion", processDefinition.getVersion());

				// 3.根据我们的任务查询流程实例(根据流程实例的id查询流程单,查询用户的信息)
				Member member = ticketService.getMemberByPiid(task.getProcessInstanceId().toString());

				map.put("member", member);

				data.add(map);
			}

			page.setDatas(data);
			Long count = taskQuery.count();
			page.setTotalsize(count.intValue());

			result.setPage(page);
			
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("任务查询列表失败!");
			e.printStackTrace();
		}

		return result;
	}

}
