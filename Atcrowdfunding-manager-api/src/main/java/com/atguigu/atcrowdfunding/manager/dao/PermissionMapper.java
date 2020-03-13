package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Permission;
import java.util.List;
import java.util.Map;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

	Permission getRootPermission();

	List<Permission> getChildrenPermissionByPid(Integer id);
 
	int queryCount(Map<String, Object> paramMap);

	List<Integer> queryPermissionIdsByRoleId(Integer roleid);
}