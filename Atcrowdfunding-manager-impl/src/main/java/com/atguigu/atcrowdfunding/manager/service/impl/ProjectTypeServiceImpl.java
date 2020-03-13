/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:ProjectTypeServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service.impl 
 * Date:2019年6月2日下午7:54:39 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Type;
import com.atguigu.atcrowdfunding.manager.dao.TypeMapper;
import com.atguigu.atcrowdfunding.manager.service.ProjectTypeService;
import com.atguigu.atcrowdfunding.util.Page;

/** 
* @ClassName: ProjectTypeServiceImpl 
* @Description: 项目分类服务接口的实现类
* @author Chen Jiang Lin 
* @date 2019-06-02 19:54
*  
*/
@Service
public class ProjectTypeServiceImpl implements ProjectTypeService {

	@Autowired
	private TypeMapper typeMapper;

	/** 
	* TODO 分页查询,模糊查询
	* @see com.atguigu.atcrowdfunding.manager.service.ProjectTypeService#queryPage(java.util.Map) 
	*/
	public Page queryPage(Map<String, Object> paramMap) {
		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		
		paramMap.put("page", page);
		
		Integer startIndex = page.getStartIndex();
		
		paramMap.put("startIndex", startIndex);
		
		List<Type> datas = typeMapper.queryList(paramMap);
		
		page.setDatas(datas);
		
		Integer totalsize = typeMapper.queryCount(paramMap);
		
		page.setTotalsize(totalsize);
		
		return page;
	}
	
	
	
	
}
  
