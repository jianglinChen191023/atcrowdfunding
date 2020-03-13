package com.atguigu.atcrowdfunding.potal.dao;

import com.atguigu.atcrowdfunding.bean.MembeAddress;
import java.util.List;

public interface MembeAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MembeAddress record);

    MembeAddress selectByPrimaryKey(Integer id);

    List<MembeAddress> selectAll();

    int updateByPrimaryKey(MembeAddress record);
}