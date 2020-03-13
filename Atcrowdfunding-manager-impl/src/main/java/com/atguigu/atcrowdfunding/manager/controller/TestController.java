package com.atguigu.atcrowdfunding.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Student;
import com.atguigu.atcrowdfunding.manager.service.TestService;
import com.atguigu.atcrowdfunding.manager.service.impl.TestServiceImpl;
/** 
* @ClassName: TestDao
* @Description: 测试类
* @author 陈江林
*  
*/
@Controller
public class TestController {

	/**
	 * Autowiired怎么进行依赖注入? 首先根据byType进行类型查找 如果查找到一个,直接注入; 如果查找到多个: 根据byName进行注入
	 * 将多个对象中其中名称与变量名称一致的那个bean注入尽量。 如果多个对象没有名称是与变量名称一致的:
	 * 可以指定 @Qualifier(value="testServiceImpl"),将其其中一个注入进来
	 * 如果 @Qualifier所指定的名称,没有一如何一个bean的名称一致,会报错。
	 * 如果无法注入,不希望报错: @Autowired(required=false)
	 * 
	 */
	/**
	 * AOP原理:动态代理 如果目的奥对象有接口,那么默认采用JDK动态代理(基于接口(代理类和目标类实现共同的接口。)). 如果目标对象
	 * 没有接口,那么采用Cglib动态代理(基于继承(代理类是目标类的子类。)). 推荐,使用JDK动态代理,也就是我们推荐采用面向接口编程.面向抽象编程。
	 */
	/*
	 * @Autowired(required=false)
	 * 
	 * @Qualifier(value="testServiceImpl")
	 */
	@Autowired
	private TestService testService;// 依赖倒转原则.

	@Transactional
	@RequestMapping("/test")
	public String test() {
		System.out.println("TestController.test()");

		testService.insert();
		
		return "success";
	}

	@RequestMapping("/testselect")
	@ResponseBody
	public Student testselect() {
		System.out.println("TestController.testselect()");
		
		System.out.println("---------名称:");

		return testService.getStudent();
	}

	@RequestMapping("/testlist")
	@ResponseBody
	public List<Student> testlsit() {
		System.out.println("TestController.testlist()");

		return testService.listStudent();
	}

}
