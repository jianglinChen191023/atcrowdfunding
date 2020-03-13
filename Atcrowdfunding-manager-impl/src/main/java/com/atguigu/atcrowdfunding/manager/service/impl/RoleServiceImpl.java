/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:RoleServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service.impl 
 * Date:2019年6月1日下午11:03:49 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.dao.RoleMapper;
import com.atguigu.atcrowdfunding.manager.service.RoleService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: RoleServiceImpl 
* @Description: 角色服务接口的实现类
* @author Chen Jiang Lin 
* @date 2019-06-01 23:03
*  
*/
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleDao;
	
	/** 
	* TODO 模糊查询 , 分页查询
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#queryPage(java.util.Map) 
	*/
	public Page queryPage(Map<String,Object> paramMap) {
		
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));

		Integer startIndex = page.getStartIndex();
		
		paramMap.put("startIndex", startIndex);
		
		List<Role> datas = roleDao.queryList(paramMap);
		
		page.setDatas(datas);
		
		Integer totalsize = roleDao.queryCount(paramMap);
		
		page.setTotalsize(totalsize);
		
		return page; 
	}

	/** 
	* TODO byid 删除
	* @see com.atguigu.atcrowdfunding.manager.service.RoleService#deleteRole(java.lang.Integer) 
	*/
	public int deleteRole(Integer id) {

		return roleDao.deleteByPrimaryKey(id); 
	}

	/** 
	* TODO 添加
	* @see com.atguigu.atcrowdfunding.manager.service.RoleService#saveRole(com.atguigu.atcrowdfunding.bean.Role, com.atguigu.atcrowdfunding.util.AjaxResult) 
	*/
	public int saveRole(Role role, AjaxResult result) {

		return roleDao.insert(role); 
	}

	/** 
	* TODO 查询名称
	* @see com.atguigu.atcrowdfunding.manager.service.RoleService#queryCount(java.util.Map) 
	*/
	public int queryCount(Map<String, Object> paramMap,AjaxResult result) {
		
		if(roleDao.queryCount(paramMap) > 1) {
			result.setMessage("异常数据,请联系管理员处理!");
			throw new RuntimeException("异常数据,请管理员处理!"+"Role.class name... "+paramMap.get("name"));
		}
		
		if(result.getMessage() == null) {
			return roleDao.queryCount(paramMap);
		}else {
			return Const.COUNT;
		}

	}

	/** 
	* TODO 批量删除
	* @see com.atguigu.atcrowdfunding.manager.service.RoleService#deleteBatchRoleByVO(com.atguigu.atcrowdfunding.vo.Data) 
	*/
	public int deleteBatchRoleByVO(Data data) {

		return roleDao.deleteBatchRoleByVO(data.getDatasRole()); 
	}

	/** 
	* TODO 跳转修改,Role by ID 
	* @see com.atguigu.atcrowdfunding.manager.service.RoleService#getRoleById(java.lang.Integer) 
	*/
	public Role getRoleById(Integer id) {

		return roleDao.selectByPrimaryKey(id);  
	}

	/** 
	* TODO 修改功能
	* @see com.atguigu.atcrowdfunding.manager.service.RoleService#updateRole(com.atguigu.atcrowdfunding.bean.Role, com.atguigu.atcrowdfunding.util.AjaxResult) 
	*/
	public int updateRole(Role role, AjaxResult result) {

		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("name", role.getName());
		int count = roleDao.queryCount(paramMap);
		if(count == 1) {
			result.setMessage("名称已存在!");
		}
		if(count > 1) {
			result.setMessage("异常数据,请联系管理员处理!");
			throw new RuntimeException("异常数据,请管理员处理!"+"Role.class name... "+paramMap.get("name"));
		}
		
		if(result.getMessage() == null) {
			return roleDao.updateByPrimaryKey(role); 
		}else {
			return Const.COUNT;
		}
		
		
	}

	/** 
	* TODO 分配许可 先删除在添加
	* @see com.atguigu.atcrowdfunding.manager.service.RoleService#saveRolePermissionRoletionship(java.lang.Integer, com.atguigu.atcrowdfunding.vo.Data) 
	*/
	public int saveRolePermissionRoletionship(Integer roleid, Data datas) {

		roleDao.deleteRolePermissionRoletionship(roleid);
		
		int totalCount = 0;
		List<Integer> ids = datas.getIds();
		for (Integer permission : ids) {
			RolePermission rp = new RolePermission();
			rp.setRoleid(roleid);
			rp.setPermissionid(permission);
			int count = roleDao.insertRolePermission(rp);
			totalCount += count;
		}
		
		return totalCount; 
	}

}
  
