/** 
 * Project Name:Atcrowdfunding-manager-api 
 * File Name:PermissionService.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service 
 * Date:2019年6月3日上午11:29:40 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Permission;

/** 
* @ClassName: PermissionService 
* @Description: 许可维护服务接口
* @author Chen Jiang Lin 
* @date 2019-06-03 11:29
*  
*/
public interface PermissionService {

	Permission getRootPermission();

	List<Permission> getChildrenPermissionByPid(Integer id);

	List<Permission> queryAllPermission();

	int savaPermission(Permission permission);

	int queryCount(Map<String, Object> paramMap);

	int updatePermission(Permission permission);

	Permission getPermissionById(Integer id);
 
	int deletePermission(Integer id);

	List<Integer> queryPermissionIdsByRoleId(Integer roleid);

	
	
}
  
