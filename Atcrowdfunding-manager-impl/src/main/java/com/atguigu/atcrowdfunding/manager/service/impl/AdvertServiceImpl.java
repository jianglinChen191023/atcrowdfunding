/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:AdvertServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service.impl 
 * Date:2019年6月2日下午3:31:01 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.manager.dao.AdvertisementMapper;
import com.atguigu.atcrowdfunding.manager.service.AdvertService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: AdvertServiceImpl 
* @Description: 广告管理服务接口的实现类
* @author Chen Jiang Lin 
* @date 2019-06-02 15:31
*  
*/
@Service
public class AdvertServiceImpl implements AdvertService {

	@Autowired
	private AdvertisementMapper advertisementMapper;
	
	/** 
	* TODO 跳转修改页面 ById 查询
	* @see com.atguigu.atcrowdfunding.manager.service.AdvertService#getAdvertById(java.lang.Integer) 
	*/
	public Advertisement getAdvertById(Integer id) {

		return advertisementMapper.selectByPrimaryKey(id);
	}

	/** 
	* TODO 分页查询,模糊查询
	* @see com.atguigu.atcrowdfunding.manager.service.AdvertService#queryPage(java.util.Map) 
	*/
	public Page queryPage(Map<String, Object> paramMap) {

		Page page = new Page((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		
		Integer startIndex = page.getStartIndex();
		
		paramMap.put("startIndex", startIndex);
		
		List<Advertisement> datas = advertisementMapper.queryList(paramMap);
		
		page.setDatas(datas);
		
		Integer totalsize = advertisementMapper.queryCount(paramMap);
		
		page.setTotalsize(totalsize);
		
		return page; 
	}

	/** 
	* TODO 查询总数
	* @see com.atguigu.atcrowdfunding.manager.service.AdvertService#queryCount(java.util.Map) 
	*/
	public int queryCount(Map<String, Object> paramMap) {

		return advertisementMapper.queryCount(paramMap); 
	}

	/** 
	* TODO 修改功能
	* @see com.atguigu.atcrowdfunding.manager.service.AdvertService#updateAdvert(com.atguigu.atcrowdfunding.bean.Advertisement, com.atguigu.atcrowdfunding.util.AjaxResult) 
	*/
	public int updateAdvert(Advertisement advertisement, AjaxResult result) {

		return advertisementMapper.updateByPrimaryKey(advertisement); 
	}

	/** 
	* TODO BYID 删除功能
	* @see com.atguigu.atcrowdfunding.manager.service.AdvertService#deleteAdvert(java.lang.Integer) 
	*/
	public int deleteAdvert(Integer id) {

		return advertisementMapper.deleteByPrimaryKey(id); 
	}

	/** 
	* TODO 批量删除
	* @see com.atguigu.atcrowdfunding.manager.service.AdvertService#deleteBatchAdvertByVO(com.atguigu.atcrowdfunding.vo.Data) 
	*/
	public int deleteBatchAdvertByVO(Data data) {

		return advertisementMapper.deleteBatchAdvertByVO(data.getDatasAdvert()); 
	}

	/** 
	* TODO 添加功能
	* @see com.atguigu.atcrowdfunding.manager.service.AdvertService#saveAdvert(com.atguigu.atcrowdfunding.bean.Advertisement, com.atguigu.atcrowdfunding.util.AjaxResult) 
	*/
	public int saveAdvert(Advertisement advertisement, AjaxResult result) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("name", advertisement.getName());
		paramMap.put("status", advertisement.getStatus());
		
		if(advertisementMapper.queryCount(paramMap) ==1) result.setMessage("广告描述已存在!");
		
		if(advertisementMapper.queryCount(paramMap) >1) {
			result.setMessage("异常数据,请联系管理员处理!");
			throw new RuntimeException("异常数据,请管理员处理!"+"advertisement.class name... "+advertisement.getName());
		}
		
		if(result.getMessage() == null) {
			return advertisementMapper.insert(advertisement); 
		}else {
			return Const.COUNT;
		}
		
	}

}
  
