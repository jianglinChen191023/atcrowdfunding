/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:PermissionServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service.impl 
 * Date:2019年6月3日上午11:32:06 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.dao.PermissionMapper;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;

/** 
* @ClassName: PermissionServiceImpl 
* @Description: 许可维护服务接口的实现类
* @author Chen Jiang Lin 
* @date 2019-06-03 11:32
*  
*/
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;

	/** 
	* TODO 查询pid为null = 父节点
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#getRootPermission() 
	*/
	public Permission getRootPermission() {

		return permissionMapper.getRootPermission(); 
	}

	/** 
	* TODO ById查询
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#getChildrenPermissionByPid(java.lang.Integer) 
	*/
	public List<Permission> getChildrenPermissionByPid(Integer id) {

		return permissionMapper.getChildrenPermissionByPid(id); 
	}

	/** 
	* TODO 查询
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#queryAllPermission() 
	*/
	public List<Permission> queryAllPermission() {

		return permissionMapper.selectAll(); 
	}

	/** 
	* TODO 添加功能
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#savaPermission(com.atguigu.atcrowdfunding.bean.Permission) 
	*/
	public int savaPermission(Permission permission) {

		return permissionMapper.insert(permission); 
	}

	/** 
	* TODO 查询数据是否存在
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#queryPermission(java.util.Map) 
	*/
	public int queryCount(Map<String, Object> paramMap) {
		
		return permissionMapper.queryCount(paramMap); 
	}

	/** 
	* TODO 修改功能
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#updatePermission(com.atguigu.atcrowdfunding.bean.Permission) 
	*/
	public int updatePermission(Permission permission) {

		return permissionMapper.updateByPrimaryKey(permission); 
	}

	/** 
	* TODO 查询功能 ById
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#getPermissionById(java.lang.Integer) 
	*/
	public Permission getPermissionById(Integer id) {

		return permissionMapper.selectByPrimaryKey(id); 
	}

	/** 
	* TODO 删除功能
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#deletePermission(java.lang.Integer) 
	*/
	public int deletePermission(Integer id) {

		return permissionMapper.deleteByPrimaryKey(id); 
	}

	/** 
	* TODO 查询关联表 t_role_permission
	* @see com.atguigu.atcrowdfunding.manager.service.PermissionService#queryPermissionByRoleId(java.lang.Integer) 
	*/
	public List<Integer> queryPermissionIdsByRoleId(Integer roleid) {

		return permissionMapper.queryPermissionIdsByRoleId(roleid); 
	}
	
	
	
}
  
