/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:RoleController.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.controller 
 * Date:2019年6月1日下午10:03:40 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.controller.BaseController;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.manager.service.RoleService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: RoleController 
* @Description: 角色控制器
* @author Chen Jiang Lin 
* @date 2019-06-01 22:03
*  
*/
@RequestMapping("/role")
@Controller
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/index")
	public String role() {
		return "role/index";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "role/index";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "role/add";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Map<String, Role> map) {
		
		Role role = roleService.getRoleById(id);
		map.put("role", role);
		return "role/update";
	}
	
	@RequestMapping("/assignPermission")
	public String assignPermission() {
		return "role/assignPermission";
	}
	
	@ResponseBody
	@RequestMapping("/doAssignPmisson")
	public Object doAssignPmisson(Integer roleid,Data datas) {
		AjaxResult result = new AjaxResult();
		
		try {
			int count = roleService.saveRolePermissionRoletionship(roleid, datas);
			
			result.setSuccess(count == datas.getIds().size()); 
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("分配许可失败!");
		}
		
		return result;
	}

	//5.zTree树形结构 -Demo5  一次加载数据,Map集合解决双层for性能问题
	@ResponseBody
	@RequestMapping("/loadDataAsync")
	public Object loadDataAsync(Integer roleid) {

		List<Permission> root = new ArrayList<Permission>();
		
		List<Permission> permissionList = permissionService.queryAllPermission();
		
		//根据角色id查询该角色之前分配过的许可
		List<Integer> permissionIdsForRoleid = permissionService.queryPermissionIdsByRoleId(roleid);			
		
		Map<Integer,Permission> map = new HashMap<Integer,Permission>();
		
		for (Permission innerPermission : permissionList) {
			map.put(innerPermission.getId(), innerPermission);
			if(permissionIdsForRoleid.contains(innerPermission.getId())) {
				innerPermission.setChecked(true);
			}
		}
		
		for (Permission permission : permissionList) {
			
			Permission child = permission ;//假设为子菜单
			if(child.getPid() == null) {
				root.add(permission);
			}else {
				//父节点
				Permission parent = map.get(child.getPid());
				if(parent == null) {
					
				}else {
					parent.getChildren().add(child);
				}
				
			}
			
		}
			
		return root;
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
			
			Page page = roleService.queryPage(paramMap);
			result.setSuccess(true);
			result.setPage(page);
			
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败!");
		}
		
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
	
	/** 
	 *  根据id 删除功能
	 * @author Chen Jiang Lin 
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDelete")
	@ResponseBody 
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			result.setSuccess(roleService.deleteRole(id) == 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("删除失败!");
			
		}
		
		return result;
	}
	
	/**
	 * 添加功能
	 * Controller-><-ServiceImpl return-><-ECCAccout return
	 */
	@RequestMapping("/doAdd")
	@ResponseBody
	public Object doAdd(Role role,AjaxResult result) throws RuntimeException{
		try {
			int count = roleService.saveRole(role,result);
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
//			result.setMessage("保存数据失败!");
		}
		
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
	
	/**
	 * 
	 * doRole:(条件查询,数据是否存在). <br/> 
	 * TODO(适用于表单校验).<br/> 
	 * count为1 返回true 表示已存在
	 * 为0 返回false 表示可用 
	 * 大于1 返回false和异常信息. 代表异常. <br/> 
	 * 
	 * @author Chen Jiang Lin 
	 * @param loginacct
	 * @param username
	 * @param eamil
	 * @return 
	 */
	@RequestMapping("/doRole")
	@ResponseBody
	public Object doRole(String name) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("name", name);
			
			int count = roleService.queryCount(paramMap,result);
			result.setSuccess(count==1);

		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("查询失败!");
		}
		
		return result;
	}
	
	/**
	 *  批量 删除功能
	 * @author Chen Jiang Lin 
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeleteBatch")
	@ResponseBody 
	public Object doDeleteBatch(Data data) {
		
		AjaxResult result = new AjaxResult();
		try {
			result.setSuccess(roleService.deleteBatchRoleByVO(data) == data.getDatasRole().size());
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("删除失败!");
			
		}
		
		return result;
	}
	
	/**
	 * 修改功能实现	
	 * Controller-><-ServiceImpl return-><-ECCAccout return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Object doUpdate(Role role,AjaxResult result) throws RuntimeException{
		try {
			int count = roleService.updateRole(role,result);
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
	
}
  
