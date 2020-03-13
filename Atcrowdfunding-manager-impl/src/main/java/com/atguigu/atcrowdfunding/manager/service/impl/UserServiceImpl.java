/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:UserServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service.impl 
 * Date:2019年5月19日下午11:43:26 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.FailException;
import com.atguigu.atcrowdfunding.manager.dao.UserMapper;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.ECCUtil;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

import freemarker.template.SimpleDate;

/** 
* @ClassName: UserServiceImpl 
* @Description: 管理员服务接口的实现类
* @author Chen Jiang Lin 
* @date 2019-05-19 23:43
*  
*/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/** 
	* TODO 登录功能
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#getUserLogin(java.util.Map) 
	*/
	public User getUserLogin(Map<String, Object> paramMap,AjaxResult result) {
		
		if(userMapper.queryCount(paramMap)>1) {
			result.setMessage("用户数据异常,请联系管理员处理!");
			throw new FailException("用户数据异常,请联系管理员处理!_账号为 : "+paramMap.get("loginacct"));
		}
		
		User user = userMapper.getUserLogin(paramMap);
		
		if(user==null) {
			result.setMessage("用户账号或密码不正确!");
			throw new FailException("用户账号或密码不正确!");
		} 
		
		
		
		
		return user; 
	}

	/** 
	* TODO 分页查询
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#queryPage(java.lang.Integer, java.lang.Integer) 
	
	public Page queryPage(Integer pageno, Integer pagesize) {
		Page page = new Page(pageno,pagesize);
		
		Integer startIndex = page.getStartIndex();
		
		List<User> datas = userMapper.queryList(startIndex,pagesize);
		
		page.setDatas(datas);
		
		Integer totalsize = userMapper.queryCount();
		
		page.setTotalsize(totalsize);
		
		return page; 
	}
	*/

	/** 
	* TODO 添加数据
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#saveUser(com.atguigu.atcrowdfunding.bean.User) 
	*/
	public int saveUser(User user,AjaxResult result){
		//获取createtime
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		
		String createtime = sdf.format(date);
		
		user.setCreatetime(createtime);
		
		//校验账号
		if(ECCUtil.ECCAccount(user.getLoginacct(),result)) {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("loginacct", user.getLoginacct());
			if (userMapper.queryCount(paramMap) == 1) result.setMessage("账号已存在!");
			if (userMapper.queryCount(paramMap) > 1) {
				result.setMessage("异常数据,请联系管理员处理!");
				throw new RuntimeException("异常数据,请管理员处理!"+"User.class loginacct... "+user.getLoginacct());
			}
		}
		
		//校验名称
		ECCUtil.ECCName(user.getUsername(),result);
//		if(ECCUtil.ECCName(user.getUsername(),result)) {
//			Map<String,Object> paramMap = new HashMap<String,Object>();
//			paramMap.put("username", user.getUsername());
//			if (userMapper.queryCount(paramMap) == 1) result.setMessage("名称已存在!");
//			if (userMapper.queryCount(paramMap) > 1){ 
//				result.setMessage("异常数据,请联系管理员处理!");
//				throw new RuntimeException("异常数据,请管理员处理!"+"User.class username... "+user.getUsername());
//			} 
//		}
	
		//校验email  
		user.setEmail(user.getEmail().toLowerCase());
		if(ECCUtil.ECCEmail(user.getEmail(),result)) {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("email", user.getEmail());
			if (userMapper.queryCount(paramMap) == 1) result.setMessage("邮箱已存在!");
			if (userMapper.queryCount(paramMap) > 1){
				result.setMessage("异常数据,请联系管理员处理!");
				throw new RuntimeException("异常数据,请管理员处理!"+"User.class email... "+user.getEmail());
			}
		}
		 
		//定义userpswd
		user.setUserpswd(MD5Util.digest(Const.PASSWORD));
		
		if(result.getMessage() == null) {
			return userMapper.insert(user);
		}else {
			return Const.COUNT;
		}
		
		
	}

	/** 
	* TODO 模糊查询 , 分页查询
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#queryPage(java.util.Map) 
	*/
	public Page queryPage(Map<String,Object> paramMap) {
		
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));

		Integer startIndex = page.getStartIndex();
		
		paramMap.put("startIndex", startIndex);
		
		List<User> datas = userMapper.queryList(paramMap);
		
		page.setDatas(datas);
		
		Integer totalsize = userMapper.queryCount(paramMap);
		
		page.setTotalsize(totalsize);
		
		return page; 
	}

	/** 
	* TODO 查询功能（User）. 
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#queryCount(java.util.Map) 
	*/
	public int queryCount(Map<String, Object> paramMap) {
		
		return userMapper.queryCount(paramMap); 
	}

	/** 
	* TODO 根据id查询. 
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#getUserById() 
	*/
	public User getUserById(Integer id) {
		 
		return userMapper.selectByPrimaryKey(id);
	}

	/** 
	* TODO 修改功能. 
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#updateUser(com.atguigu.atcrowdfunding.bean.User, com.atguigu.atcrowdfunding.util.AjaxResult) 
	*/
	public int updateUser(User user, AjaxResult result) {
				//校验账号
//				if(ECCUtil.ECCAccount(user.getLoginacct(),result)) {
//					Map<String,Object> paramMap = new HashMap<String,Object>();
//					paramMap.put("loginacct", user.getLoginacct());
//					if (userMapper.queryCount(paramMap) == 1) result.setMessage("账号已存在!");
//					if (userMapper.queryCount(paramMap) > 1) {
//						result.setMessage("异常数据,请联系管理员处理!");
//						throw new RuntimeException("异常数据,请管理员处理!"+"User.class loginacct... "+user.getLoginacct());
//					}
//				}
				
				//校验名称
				ECCUtil.ECCName(user.getUsername(),result);
			
				//校验email
				user.setEmail(user.getEmail().toLowerCase());
				if(ECCUtil.ECCEmail(user.getEmail(),result)) {
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("email", user.getEmail());
					//if (userMapper.queryCount(paramMap) == 1) result.setMessage("邮箱已存在!");
					if (userMapper.queryCount(paramMap) > 1){
						result.setMessage("异常数据,请联系管理员处理!");
						throw new RuntimeException("异常数据,请管理员处理!"+"User.class email... "+user.getEmail());
					}
				}
				
				if(result.getMessage() == null) {
					return userMapper.updateByPrimaryKey(user);
				}else {
					return Const.COUNT;
				} 
	}

	/** 
	* TODO 删除功能
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#deleteUser(java.lang.Integer) 
	*/
	public int deleteUser(Integer id) {

		return userMapper.deleteByPrimaryKey(id); 
	}

	/** 
	* TODO 批量删除
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#deleteBatchUser(java.lang.Integer[]) 
	*/
	public int deleteBatchUser(Integer[] ids) {
		int totalCount = 0;
		for (Integer id : ids) {
			int count = userMapper.deleteByPrimaryKey(id);
			totalCount += count;
		}
		return totalCount; 
	}

	/** 
	* TODO 批量删除
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#deleteBatchUserByVO(com.atguigu.atcrowdfunding.vo.Data) 
	*/
	public int deleteBatchUserByVO(Data data) {
		
		return userMapper.deleteBatchUserByVO(data.getDatas()); 
	}

	/** 
	* TODO 查询所有角色
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#queryAllRole() 
	*/
	public List<Role> queryAllRole() {

		return userMapper.queryAllRole(); 
	}

	/** 
	* TODO 查询已分配角色的id
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#queryRoleByUserid(java.lang.Integer) 
	*/
	public List<Integer> queryRoleByUserid(Integer id) {

		return userMapper.queryRoleByUserid(id); 
	}

	/** 
	* TODO 分配角色 
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#saveUserRoleRelationship(java.lang.Integer, com.atguigu.atcrowdfunding.vo.Data) 
	*/
	public int saveUserRoleRelationship(Integer userid, Data data) {

		return userMapper.saveUserRoleRelationship(userid,data); 
	}

	/** 
	* TODO 取消分配 
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#deleteUserRoleRelationship(java.lang.Integer, com.atguigu.atcrowdfunding.vo.Data) 
	*/
	public int deleteUserRoleRelationship(Integer userid, Data data) {

		return userMapper.deleteUserRoleRelationship(userid,data); 
	}

	/** 
	* TODO 查询许可
	* @see com.atguigu.atcrowdfunding.manager.service.UserService#queryPermissionByUserid(java.lang.Integer) 
	*/
	public List<Permission> queryPermissionByUserid(Integer id) {

		return userMapper.queryPermissionByUserid(id); 
	}



	

}
  
