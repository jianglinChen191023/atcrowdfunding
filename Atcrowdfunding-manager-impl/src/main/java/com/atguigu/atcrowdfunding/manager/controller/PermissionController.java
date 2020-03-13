/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:PermissionController.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.controller 
 * Date:2019年6月3日上午11:27:31 
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.controller.BaseController;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

/** 
* @ClassName: PermissionController 
* @Description: 许可维护控制器 
* @author Chen Jiang Lin 
* @date 2019-06-03 11:27
*  
*/
@Controller
@RequestMapping("/permission")
public class PermissionController extends BaseController{

	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}
	
	@RequestMapping("/toIndex")
	public String toIndex() {
		return "permission/index";
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "permission/add";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(Integer id,Map map) {
		Permission permission = permissionService.getPermissionById(id);
		map.put("permission", permission);
		return "permission/update";
	}
	
	//删除
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doUpdate(Integer id) {
		start();
		
		try {
			int count = permissionService.deletePermission(id);
			
			success((count == 1));
		} catch (Exception e) {
			e.printStackTrace();
			error("删除许可树数据失败!");
		}
		
		return end();
	}
	
	//修改
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(Permission permission) {
		AjaxResult result = new AjaxResult();
		
		try {
			int count = permissionService.updatePermission(permission);
			
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("修改许可树数据失败!");
		}
		
		return result;
	}
		
	//保存
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission) {
		AjaxResult result = new AjaxResult();
		
		try {
			
			int count = permissionService.savaPermission(permission);
			
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存许可树数据失败!");
		}
		
		return result;
	}
	
	//校验
	@ResponseBody
	@RequestMapping("/doPermission")
	public Object doPermission(String name,String url) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("name", name);
			paramMap.put("url", url);
			
			int count = permissionService.queryCount(paramMap);
			
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("保存许可树数据失败!");
		}
		
		return result;
	}
	
	
	//5.zTree树形结构 -Demo5  一次加载数据,Map集合解决双层for性能问题
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		AjaxResult result = new AjaxResult();
		
		try {
			List<Permission> root = new ArrayList<Permission>();
			
			List<Permission> permissionList = permissionService.queryAllPermission();
			
			Map<Integer,Permission> map = new HashMap<Integer,Permission>();
			
			for (Permission innerPermission : permissionList) {
				map.put(innerPermission.getId(), innerPermission);
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
			
			result.setSuccess(true);
			result.setData(root);	
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("加载许可树数据失败!");
		}
		
		return result;
	}
	
		
	//4.zTree树形结构 -Demo4  一次加载数据,避免多次数据库交互,提高效率
	/*
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData(){
	
		AjaxResult result = new AjaxResult();
	
		try {
			List<Permission> root = new ArrayList<Permission>();
			
			//Demo 4 一次查询所有数据
			List<Permission> childredPermissons =  permissionService.queryAllPermission();
			for (Permission permission : childredPermissons) {
				//通过子查找父
				//子菜单
				Permission child = permission ; //假设为子菜单
				if(child.getPid() == null ){
					root.add(permission); //根节点
				}else{
					//父节点
					for (Permission innerpermission : childredPermissons) {
						if(child.getPid() == innerpermission.getId()){
							Permission parent = innerpermission;                                                        
							parent.getChildren().add(child);
							break ; //跳出内层循环,如果跳出外层循环,需要使用标签跳出
						}
					}
				}
			}
			result.setSuccess(true);
			result.setData(root);
			
		} catch (Exception e) {
			result.setMessage("加载许可树失败!");
			result.setSuccess(false);
			e.printStackTrace();
		}
		
		return result;
	}
	*/
	
	/**
	 * 采用递归加载数据
	 * 1.方法自身调用
	 * 2.参数有规律
	 * 3.跳出方法条件
	 */
	private void queryChildPermissions(Permission permission) {
		List<Permission> children = permissionService.getChildrenPermissionByPid(permission.getId());
		//组合父子关系
		permission.setChildren(children);

		for (Permission innerChild : children) {
			queryChildPermissions(innerChild);
		}
	}
	
	//Demo2 - 从数据库表t_permission查询数据,显示许可树
	/*
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		AjaxResult result = new AjaxResult();
		
		try {
			
			List<Permission> root = new ArrayList<Permission>();
			
			//父
			Permission permission = permissionService.getRootPermission();
			
			root.add(permission);
			
			//子
			List<Permission> children = permissionService.getChildrenPermissionByPid(permission.getId());
			
			//设置父子关系
			permission.setChildren(children);
			
			//孙子
			for (Permission child : children) {
				child.setOpen(true);
				
				List<Permission> innerChildren = permissionService.getChildrenPermissionByPid(child.getId());
				child.setChildren(innerChildren);
			}
			
			result.setSuccess(true);
			result.setData(root);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("加载许可树数据失败!");
		}
		
		return result;
	}
	*/
	
	//Demo1 - 模拟数据,生成树
	/*
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		AjaxResult result = new AjaxResult();
		
		try {
			
			List<Permission> root = new ArrayList<Permission>();
			
			//父
			Permission permission = new Permission();
			permission.setName("系统权限菜单");
			
			root.add(permission);
			
			//子
			List<Permission> children = new ArrayList<Permission>();
			
			Permission permission1 = new Permission();
			permission1.setName("控制面板");
			
			Permission permission2 = new Permission();
			permission2.setName("权限管理");
			
			children.add(permission1);
			children.add(permission2);
			
			//展开	
			permission.setOpen(true);
			
			//设置父子关系
			permission.setChildren(children);
			
			result.setSuccess(true);
			result.setData(root);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("加载许可树数据失败!");
		}
		
		  {"success":true,"message":null,"page":null,
		  "data":[{"id":null,"pid":null,"name":"系统权限菜单","icon":null,"url":null,"open":true,
		  "children":[{"id":null,"pid":null,"name":"控制面板","icon":null,"url":null,"open":false,"children":null},
		  {"id":null,"pid":null,"name":"权限管理","icon":null,"url":null,"open":false,"children":null}]}]} 
		  {"id":null,"pid":null,"name":"权限管理","icon":null,"url":null,"open":false,"children":null}]}}
		 
		return result;
	}
	 */
	
	
}
  
