package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Student;
/** 
* @ClassName: TestDao
* @Description: 测试类
* @author 陈江林
*  
*/
public interface TestService {

	List<Student> listStudent();
	public void insert();
	Student getStudent();
}
