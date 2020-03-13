/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:BaseController.java 
 * Package Name:com.atguigu.atcrowdfunding.controller 
 * Date:2019年6月5日下午8:36:18 
 * 
 */  
      
package com.atguigu.arcrowdfunding.controller;

import java.util.HashMap;
import java.util.Map;

/** 
* @ClassName: BaseController 
* @Description: 通过ThreadLocal来共享数据
* @author Chen Jiang Lin 
* @date 2019-06-05 20:36
*  
*/
public class BaseController {

	/*
	 在ThreadLocal的get(),set()的时候都会清除线程ThreadLocalMap里所有key为null的value。 
	 而ThreadLocal的remove()方法会先将Entry中对key的弱引用断开，设置为null，然后再清除对应的key为null的value。 
	 
	 private ThreadLocal<Map<String,Object>> resultMap; //不能使用成员变量,因为控制器对象是单例的。会出现多线程并发问题
	 通过ThreadLocal来共享数据
	*/
	private ThreadLocal<Map<String,Object>> datas = new ThreadLocal<Map<String,Object>>();
	
	protected void start() {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		datas.set(resultMap);
	}
	
	public Object end() {
		Map<String,Object> resultMap = datas.get();
		datas.remove();
		return resultMap;
	}
	
	public void success(boolean flg) {
		Map<String,Object> resultMap = datas.get();
		resultMap.put("success", flg);
	}
	
	public void param(String key,Object val) {
		Map<String,Object> resultMap = datas.get();
		resultMap.put(key, val);
	}
	
	public void error(String msg) {
		Map<String,Object> resultMap = datas.get();
		resultMap.put("message", msg);
	}
	
}
  
