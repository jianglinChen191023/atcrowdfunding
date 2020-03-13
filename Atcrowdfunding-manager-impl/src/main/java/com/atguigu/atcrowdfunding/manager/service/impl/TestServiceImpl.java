package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Student;
import com.atguigu.atcrowdfunding.manager.dao.TestDao;
import com.atguigu.atcrowdfunding.manager.service.TestService;
/** 
* @ClassName: TestDao
* @Description: 测试类
* @author 陈江林
*  
*/
@Service
public class TestServiceImpl implements TestService {
 
	@Autowired
	public TestDao testDao;
	  
	public void insert() {
/*		Map map = new HashMap();
		map.put("name", "zhang3");*/
		
/*		testDao.insert(new Student(2,"cjl"));*/
		testDao.insert(new Student(3,"cjl3"));
	}

	
	
	public Student getStudent() {
		return testDao.getStudent();
	}



	public List<Student> listStudent() {
		// TODO Auto-generated method stub
		return testDao.listStudent();
	}

}
