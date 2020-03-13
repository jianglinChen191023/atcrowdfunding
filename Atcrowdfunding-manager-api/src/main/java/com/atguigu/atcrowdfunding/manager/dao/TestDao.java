package com.atguigu.atcrowdfunding.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.atguigu.atcrowdfunding.bean.Student;

/** 
* @ClassName: TestDao
* @Description: 测试类
* @author 陈江林
*  
*/
public interface TestDao {
	
	Student getStudent();
	List<Student> listStudent();
	
	public void insert(Map map);
	public void insert(Student student);
	
}
