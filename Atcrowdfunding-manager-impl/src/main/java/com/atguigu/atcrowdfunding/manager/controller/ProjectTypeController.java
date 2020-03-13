/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:ProjectTypeController.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.controller 
 * Date:2019年6月2日下午7:47:26 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: ProjectTypeController 
* @Description: 项目分类控制器
* @author Chen Jiang Lin 
* @date 2019-06-02 19:47
*  
*/
@RequestMapping("project_type")
@Controller
public class ProjectTypeController {

	@Autowired
	private ProjectTypeService projectTypeService;
	
	@RequestMapping("index")
	public String index() {
		
		return "projcettype/index";
	}
	
	@RequestMapping("toIndex")
	public String toIndex() {
		
		return "projcettype/index";
	}
	
	@RequestMapping("toAdd")
	public String toAdd() {
		
		return "projcettype/add";
	}
	
	@RequestMapping("toUpdate")
	public String toUpdate() {
		
		return "projcettype/update";
	}

	
	/**
	*  分页查询
	*/
	@RequestMapping("/doIndex")
	@ResponseBody
	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize,
			String queryText) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			
			if(StringUtil.isNotEmpty(queryText)) {
				
				if(queryText.contains("%")) {
					queryText = queryText.replaceAll("%", "\\\\%");
				}
				
				if(queryText.contains("_")) {
					queryText = queryText.replaceAll("_", "\\\\_");
				}
				
				paramMap.put("queryText", queryText);
			}
			
			Page page = projectTypeService.queryPage(paramMap);
			System.out.println(page);
			result.setSuccess(true);
			result.setPage(page);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败!");
		}
		
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
		
	
//	/**
//	 *  批量 删除功能
//	 * @author Chen Jiang Lin 
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/doDeleteBatch")
//	@ResponseBody 
//	public Object doDeleteBatch(Data data) {
//		
//		AjaxResult result = new AjaxResult();
//		try {
//			result.setSuccess(userService.deleteBatchUserByVO(data) == data.getDatas().size());
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setMessage("删除失败!");
//			
//		}
//		
//		return result;
//	}
//	
//	
//	/** 
//	 *  根据id 删除功能
//	 * @author Chen Jiang Lin 
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/doDelete")
//	@ResponseBody 
//	public Object doDelete(Integer id) {
//		AjaxResult result = new AjaxResult();
//		try {
//			result.setSuccess(userService.deleteUser(id) == 1);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setMessage("删除失败!");
//			
//		}
//		
//		return result;
//	}
//	
//	/**
//	 * 
//	 * update:(修改功能-表单回显). <br/> 
//	 * @author Chen Jiang Lin 
//	 * @param id
//	 * @param map
//	 * @return
//	 */
//	@RequestMapping("/toUpdate")
//	public String update(Integer id,Map<String, User> map) {
//		
//		User user = userService.getUserById(id);
//		map.put("user", user);
//		
//		return "user/update";
//	}
//	
//	/**
//	 * 修改功能实现	
//	 * Controller-><-ServiceImpl return-><-ECCAccout return
//	 */
//	@RequestMapping("/doUpdate")
//	@ResponseBody
//	public Object doUpdate(User user,AjaxResult result) throws RuntimeException{
//		try {
//			int count = userService.updateUser(user,result);
//			result.setSuccess(count==1);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
////			result.setMessage("保存数据失败!");
//		}
//		 
//		return result;//将对象序列化为JSON字符串,一流的形式返回
//	}
	
//	/**
//	 * 添加功能
//	 * Controller-><-ServiceImpl return-><-ECCAccout return
//	 */
//	@RequestMapping("/doAdd")
//	@ResponseBody
//	public Object doAdd(User user,AjaxResult result) throws RuntimeException{
//		try {
//			int count = userService.saveUser(user,result);
//			result.setSuccess(count==1);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
////			result.setMessage("保存数据失败!");
//		}
//		
//		return result;//将对象序列化为JSON字符串,一流的形式返回
//	}
//	
//	
//		
//	/**
//	 * 
//	 * doUser:(条件查询,数据是否存在). <br/> 
//	 * TODO(适用于表单校验).<br/> 
//	 * count为1 返回true 表示已存在
//	 * 为0 返回false 表示可用 
//	 * 大于1 返回false和异常信息. 代表异常. <br/> 
//	 * 
//	 * @author Chen Jiang Lin 
//	 * @param loginacct
//	 * @param username
//	 * @param eamil
//	 * @return 
//	 */
//	@RequestMapping("/doUser")
//	@ResponseBody
//	public Object doUser(String loginacct,String username,String eamil) {
//		AjaxResult result = new AjaxResult();
//		try {
//			Map<String,Object> paramMap = new HashMap<String,Object>();
//			System.out.println(loginacct);
//			paramMap.put("loginacct", loginacct);
//			paramMap.put("username", username);
//			paramMap.put("eamil", eamil);	
//			
//			int count = userService.queryCount(paramMap);
//			result.setSuccess(count==1);
//			if(count > 1) {
//				result.setMessage("数据异常,请联系管理员处理!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			result.setMessage("查询失败!");
//		}
//		
//		return result;
//	}
	
}
  
