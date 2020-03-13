package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.RolePermission;
import com.atguigu.atcrowdfunding.bean.User;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
 
	List<Role> queryList(Map<String, Object> paramMap);

	Integer queryCount(Map<String, Object> paramMap);

	int deleteBatchRoleByVO(List<Role> list);

	int insertRolePermission(RolePermission rp);

	void deleteRolePermissionRoletionship(Integer roleid);
}