/** 
 * Project Name:Atcrowdfunding-manager-api 
 * File Name:ProjectTypeService.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service 
 * Date:2019年6月2日下午7:53:52 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.util.Page;

/** 
* @ClassName: ProjectTypeService 
* @Description: 项目分类服务接口
* @author Chen Jiang Lin 
* @date 2019-06-02 19:53
*  
*/
public interface ProjectTypeService {

	Page queryPage(Map<String, Object> paramMap);

	
	
}
  
