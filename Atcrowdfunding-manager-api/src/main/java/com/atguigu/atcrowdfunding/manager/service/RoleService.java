/** 
 * Project Name:Atcrowdfunding-manager-api 
 * File Name:roleService.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service 
 * Date:2019年6月1日下午11:01:17 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: roleService 
* @Description: 角色服务接口
* @author Chen Jiang Lin 
* @date 2019-06-01 23:01
*  
*/
public interface RoleService {

	public Page queryPage(Map<String, Object> paramMap);

	public int deleteRole(Integer id);

	public int saveRole(Role role, AjaxResult result);

	public int queryCount(Map<String, Object> paramMap,AjaxResult result);

	public int deleteBatchRoleByVO(Data data);

	public Role getRoleById(Integer id);

	public int updateRole(Role role, AjaxResult result);
 
	public int saveRolePermissionRoletionship(Integer roleid, Data datas);

}
  
