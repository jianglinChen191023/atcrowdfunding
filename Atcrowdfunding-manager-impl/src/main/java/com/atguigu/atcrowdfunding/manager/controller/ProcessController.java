/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:ProcessController.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.controller 
 * Date:2019年6月12日上午8:46:55 
 * 
 */

package com.atguigu.atcrowdfunding.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.manager.service.ProcessService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;

/**
 * @ClassName: ProcessController
 * @Description: 流程控制器
 * @author Chen Jiang Lin
 * @date 2019-06-12 08:46
 * 
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	private RepositoryService repositoryService;

	@RequestMapping("/index")
	public String index() {
		return "process/index";
	}

	@RequestMapping("/showing")
	public String showing() {
		return "process/showing";
	}

	/**
	 * 查询图片
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/showingProDef")
	public void showingProDef(String id, HttpServletResponse response) throws Exception { // 流程定义id

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id)
				.singleResult();

		InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
				processDefinition.getDiagramResourceName());

		ServletOutputStream servletOutputStream = response.getOutputStream();

		IOUtils.copy(inputStream, servletOutputStream);

	}

	/**
	 * 删除
	 */
	@RequestMapping("/doDeleteProDef")
	@ResponseBody
	public Object doDeleteProDef(String id) { // 流程定义id

		AjaxResult result = new AjaxResult();

		try {

			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(id).singleResult();

			repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true); // true表示级联删除

			result.setSuccess(true);

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("部署流程定义失败!");
			e.printStackTrace();
		}

		return result;// 将对象序列化为JSON字符串,一流的形式返回
	}

	/**
	 * 部署
	 */
	@RequestMapping("/doDeploy")
	@ResponseBody
	public Object doDeploy(HttpServletRequest request) {

		AjaxResult result = new AjaxResult();

		try {

			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

			MultipartFile multipartFile = multipartHttpServletRequest.getFile("processDefFile");

			repositoryService.createDeployment()
					.addInputStream(multipartFile.getOriginalFilename(), multipartFile.getInputStream()).deploy();

			result.setSuccess(true);

		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("部署流程定义失败!");
			e.printStackTrace();

		}

		return result;// 将对象序列化为JSON字符串,一流的形式返回
	}

	/**
	 * 分页查询
	 */
	@RequestMapping("/doIndex")
	@ResponseBody
	public Object index(@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageno,
			@RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pagesize,
			String queryText) {
		AjaxResult result = new AjaxResult();
		try {

			Page page = new Page(pageno, pagesize);

			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			// 查询流程定义集合数据,可能出现了自关联,导致jackson组件无法将集合序列化为JSON串
			List<ProcessDefinition> listPage = processDefinitionQuery.listPage(page.getStartIndex(), pagesize);

			List<Map<String, Object>> mylistPage = new ArrayList<Map<String, Object>>();

			for (ProcessDefinition processDefinition : listPage) {
				Map<String, Object> pd = new HashMap<String, Object>();
				pd.put("id", processDefinition.getId());
				pd.put("name", processDefinition.getName());
				pd.put("version", processDefinition.getVersion());
				pd.put("key", processDefinition.getKey());
				mylistPage.add(pd);
			}

			Long totalsize = processDefinitionQuery.count();

			// 模糊查询
			List<Map<String, Object>> likeName = null;

			if (StringUtil.isNotEmpty(queryText)) {

				likeName = new ArrayList<Map<String, Object>>();

				if (queryText.contains("%")) {
					queryText = queryText.replaceAll("%", "\\\\%");
				}

				if (queryText.contains("_")) {
					queryText = queryText.replaceAll("_", "\\\\_");
				}

				for (Map<String, Object> map : mylistPage) {
					if (map.get("name").toString().contains(queryText)) {
						likeName.add(map);
					}
				}

			}

			if (mylistPage != null && likeName != null) {
				page.setDatas(likeName);
			} else {
				page.setDatas(mylistPage);
			}

			page.setTotalsize(totalsize.intValue());

			System.out.println(page);
			result.setSuccess(true);
			result.setPage(page);

		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询流程定义失败!");
		}

		return result;// 将对象序列化为JSON字符串,一流的形式返回
	}

}
