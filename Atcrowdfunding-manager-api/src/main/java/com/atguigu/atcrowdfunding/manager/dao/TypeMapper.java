package com.atguigu.atcrowdfunding.manager.dao;

import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.util.Page;

import java.util.List;
import java.util.Map;

public interface TypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    Type selectByPrimaryKey(Integer id);

    List<Type> selectAll();

    int updateByPrimaryKey(Type record);

	List<Type> queryList(Map<String, Object> paramMap);

	Integer queryCount(Map<String, Object> paramMap);

}