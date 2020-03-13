/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:ApplicationContextUtils.java 
 * Package Name:com.atguigu.arcrowdfunding.util 
 * Date:2019年6月17日下午3:17:27 
 * 
 */  
      
package com.atguigu.atcrowdfunding.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
* @ClassName: ApplicationContextUtils 
* @Description: 自定义的IOC工具类,实现Spring监听器接口,以接口注入的方式获取IOC容器对象
* @author Chen Jiang Lin 
* @date 2019-06-17 15:17
*  
*/
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	public static ApplicationContext applicationContext;

	/** 
	* TODO applicationContext 接口注入
	* @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext) 
	*/
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextUtils.applicationContext = applicationContext;
	}

}
  
