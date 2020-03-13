package com.atguigu.atcrowdfunding.bean;

import java.io.Serializable;
/** 
* @ClassName: Student
* @Description: 用于测试的实体类
* @author 陈江林
*  
*/
public class Student implements Serializable{

	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	
	
	public Student() {
		super();
	}
	public Student(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
