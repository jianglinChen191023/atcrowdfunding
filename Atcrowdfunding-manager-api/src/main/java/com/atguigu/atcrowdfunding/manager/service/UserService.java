/** 
 * Project Name:Atcrowdfunding-manager-api 
 * File Name:UserService.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service 
 * Date:2019年5月19日下午11:41:55 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: UserService 
* @Description: 管理员 业务层接口
* @author Chen Jiang Lin 
* @date 2019-05-19 23:41
*  
*/
public interface UserService {

	User getUserLogin(Map<String, Object> paramMap,AjaxResult result);
	 
	//@Deprecated
	//Page queryPage(Integer pageno, Integer pagesize);
	
	int saveUser(User user,AjaxResult result);

	Page queryPage(Map<String,Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	User getUserById(Integer id);

	int updateUser(User user, AjaxResult result);


	int deleteUser(Integer id);

	int deleteBatchUser(Integer[] ids);

	int deleteBatchUserByVO(Data data);

	List<Role> queryAllRole();

	List<Integer> queryRoleByUserid(Integer id);

	int saveUserRoleRelationship(Integer userid, Data data);

	int deleteUserRoleRelationship(Integer userid, Data data);

	List<Permission> queryPermissionByUserid(Integer id);
	
	
}
  
