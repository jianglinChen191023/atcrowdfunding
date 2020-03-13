/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:TestActiviti.java 
 * Package Name:junit.activiti 
 * Date:2019年6月9日下午9:03:55 
 * 
 */

package junit.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.atcrowdfunding.activiti.listener.NoListener;
import com.atguigu.atcrowdfunding.activiti.listener.YesListener;

/**
 * @ClassName: TestActiviti
 * @Description: Activiti测试类 伪单例 被容器单例所管理
 * @author Chen Jiang Lin
 * @date 2019-06-09 21:03
 * 
 */
public class TestActiviti {

	ApplicationContext ioc = new ClassPathXmlApplicationContext("spring/spring-*.xml");
	ProcessEngine processEngine = (ProcessEngine) ioc.getBean("processEngine");
	
	//12.查询流程监听器
	@Test
	public void test12() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		System.out.println(processDefinition);
		System.out.println("---");
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("yesListener", new YesListener());
		variables.put("noListener", new NoListener()); 
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),variables);
		System.out.println("processInstance"+processInstance);
	}
	
	//12.1.审批
	@Test
	public void test121() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		TaskService taskService = processEngine.getTaskService();
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("flag", "true");
		
		for (Task task : list) {
		//	taskService.setVariable(task.getId(), "flag", "true");
			taskService.complete(task.getId(),variables);
			
		}
		
	}
	
	//11.网关 - 包含网关 排他+并行
	@Test
	public void test11() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		System.out.println(processDefinition);
		System.out.println("---");
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		Map<String,Object> variables = new HashMap<String,Object>();
		variables.put("days", "5");
		variables.put("cost", "8000");
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),variables);
		System.out.println("processInstance"+processInstance);
	}
	
	//11.1.审批
	@Test
	public void test111() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		TaskService taskService = processEngine.getTaskService();
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> list = taskQuery.taskAssignee("lisi").list();
		
		for (Task task : list) {
			taskService.complete(task.getId());
		}
		
	}
	
	//10.网关 - 并行网关(会签)
	@Test
	public void test10() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
		System.out.println("processInstance"+processInstance);
	}
	
	//10.1.网关 - 并行网关(会签) - 项目经理和财务经理都审批后,流程结束;如果只有一个经理审批,流程需要等待
	@Test
	public void test101() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		TaskService taskService = processEngine.getTaskService();
		
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> list = taskQuery.taskAssignee("zhangsan").list();
		
		for (Task task : list) {
			taskService.complete(task.getId());
		}
		
	}
		
	//9.网关 - 排他网关(互斥)
	@Test
	public void test09() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		Map<String, Object> varibales = new HashMap<String,Object>();
		varibales.put("days", "5");
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),varibales);
		System.out.println("processInstance"+processInstance);
	}
	
	//9.1.审批 怎么审批? 1.查询任务
	@Test
	public void test091() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		//1.1获取服务
		TaskService taskService = processEngine.getTaskService();
		
		//1.2获取查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		//1.3查询zhangsan的任务
		List<Task> list = taskQuery.taskAssignee("lisi").list();
		
		for (Task task : list) {
			taskService.complete(task.getId());
		}
		
	}
	
	//流程变量
	//如果存在流程变量,那么,在启动流程实例时,要给流程变量赋值,否则流程实例会报错
	//org.activiti.engine.ActivitiException: Unknown property used in expression: ${tl}
	@Test
	public void test08() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		
		Map<String, Object> varibales = new HashMap<String,Object>();
		varibales.put("tl", "zhangsan");
		varibales.put("pm", "lisi");
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),varibales);
		System.out.println("processInstance"+processInstance);
	}
	
	// 7.领取任务
	@Test
	public void test07() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
	
		TaskService taskService = processEngine.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		//查询zhangsan的任务:
		System.out.println("查询zhangsan的任务:");
		long count1 = taskQuery.taskAssignee("zhangsan").count();
		System.out.println("查询组tl之前的zhangsan的任务数量:"+count1);
		
		List<Task> list = taskQuery.taskCandidateGroup("tl").list();	//taskQuery被占用:查询组,那么TaskQuery taskQuery = taskService.createTaskQuery();的taskQuery不能再查询zhangsan需要taskQuery = taskService.createTaskQuery();
		
		//查询zhangsan的任务:
		System.out.println("查询zhangsan的任务:");
		long count2 = taskQuery.taskAssignee("zhangsan").count();
		System.out.println("查询组tl之后的zhangsan的任务数量:"+count2);
		
		for (Task task : list) {
			System.out.println("id= "+task.getId());
			System.out.println("name= "+task.getName());
			taskService.claim(task.getId(), "zhangsan");
		}
		
		taskQuery = taskService.createTaskQuery();
		//查询zhangsan的任务:
		System.out.println("查询zhangsan的任务:");
		List<Task> list3 = taskQuery.taskAssignee("zhangsan").list();
		System.out.println("重新获取到taskQuery之后重新的zhangsan的任务数量:"+list3.size());
		
		System.out.println("------------------------------");
		
	}
	
	// 6.历史数据查询
	@Test
	public void test06() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
	
		HistoryService historyService = processEngine.getHistoryService();

		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		
		HistoricProcessInstance historicProcessInstance = historicProcessInstanceQuery.processInstanceId("301").finished().singleResult();
		
		System.out.println("historicProcessInstance= "+historicProcessInstance);
		
		
	}
	
	// 5.查询流程实例的任务数据
	@Test
	public void test05() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
	
		TaskService taskService = processEngine.getTaskService();
		TaskQuery taskQuery = taskService.createTaskQuery();
		
		List<Task> list1 = taskQuery.taskAssignee("zhangsan").list();
		List<Task> list2 = taskQuery.taskAssignee("lisi").list();
		//zhangsan的任务:
		System.out.println("zhangsan的任务:");
		for (Task task : list1) {
			System.out.println("id= "+task.getId());
			System.out.println("name= "+task.getName());
			//zhangsan完成任务
			taskService.complete(task.getId());
		}
		System.out.println("----------------------");
		//lisi的任务:
		System.out.println("lisi的任务:");
		for (Task task : list2) {
			System.out.println("id= "+task.getId());
			System.out.println("name= "+task.getName());
			//lisi完成任务
			taskService.complete(task.getId());
		}
		
		System.out.println("========================================");
		
		list1 = taskQuery.taskAssignee("zhangsan").list();
		list2 = taskQuery.taskAssignee("lisi").list();
		//zhangsan的任务:
		System.out.println("zhangsan的任务:");
		for (Task task : list1) {
			System.out.println("id= "+task.getId());
			System.out.println("name= "+task.getName());
			//zhangsan完成任务
			taskService.complete(task.getId());
		}
		System.out.println("----------------------");
		//lisi的任务:
		System.out.println("lisi的任务:");
		for (Task task : list2) {
			System.out.println("id= "+task.getId());
			System.out.println("name= "+task.getName());
		}
		
	}

	
	// 4.启动流程实例
	/**
	 * 
	 * test04: . <br/> 
	 * 
	 * act_hi_actinst,历史的活动的任务表
	 * act_hi_procinst,历史的流程实例表
	 * act_hi_taskinst,历史的流程任务表
	 * act_ru_execution,正在运行的任务表
	 * act_ru_task 正在执行(运行)的任务数据表
	 * 
	 * @author Chen Jiang Lin
	 */
	@Test
	public void test04() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().latestVersion().singleResult();
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
		System.out.println("processInstance"+processInstance);
	}

	// 3.查询部署流程定义
	@Test
	public void test03() {
		System.out.println("processEngine" + processEngine);

		RepositoryService repositoryService = processEngine.getRepositoryService();

		ProcessDefinitionQuery createProcessDefinitionQuery = repositoryService.createProcessDefinitionQuery();

		List<ProcessDefinition> list = createProcessDefinitionQuery.list(); // 查询所有

		for (ProcessDefinition processDefinition : list) {
			System.out.println("id" + processDefinition.getId());
			System.out.println("key" + processDefinition.getKey());
			System.out.println("name" + processDefinition.getName());
			System.out.println("version" + processDefinition.getVersion());
			System.out.println("-------------------------");

		}

		long count = createProcessDefinitionQuery.count(); // 查询流程定义的总记录数
		System.out.println("count=" + count);
		System.out.println("-------------------------");

		ProcessDefinition singleResult = createProcessDefinitionQuery.latestVersion().singleResult(); // 查询最后一次部署的流程定义
		System.out.println("id" + singleResult.getId());
		System.out.println("key" + singleResult.getKey());
		System.out.println("name" + singleResult.getName());
		System.out.println("version" + singleResult.getVersion());
		System.out.println("**************************");

		// 排序查询流程定义,分页查询流程定义
		ProcessDefinitionQuery desc = createProcessDefinitionQuery.orderByProcessDefinitionVersion().desc();
		List<ProcessDefinition> listPage = desc.listPage(0, 2);
		for (ProcessDefinition processDefinition : listPage) {
			System.out.println("id" + processDefinition.getId());
			System.out.println("key" + processDefinition.getKey());
			System.out.println("name" + processDefinition.getName());
			System.out.println("version" + processDefinition.getVersion());
			System.out.println("%%%%%%%%%%%%%");
		}

		// 根据流程定义的key,查询流程定义对象
		ProcessDefinition singleResult2 = createProcessDefinitionQuery.processDefinitionKey("myProcess").latestVersion()
				.singleResult();
		System.out.println("id" + singleResult2.getId());
		System.out.println("key" + singleResult2.getKey());
		System.out.println("name" + singleResult2.getName());
		System.out.println("version" + singleResult2.getVersion());
		System.out.println("-------------------------");

	}

	// 2.部署流程定义
	@Test
	public void test02() {
		System.out.println("processEngine" + processEngine);

		RepositoryService repositoryService = processEngine.getRepositoryService();

		Deployment deploy = repositoryService.createDeployment().addClasspathResource("auth.bpmn").deploy();

		System.out.println("deploy" + deploy);
	}

	// 1.创建流程引擎,创建23张表

	@Test
	public void test01() {
		System.out.println("processEngine" + processEngine);

	}

}
