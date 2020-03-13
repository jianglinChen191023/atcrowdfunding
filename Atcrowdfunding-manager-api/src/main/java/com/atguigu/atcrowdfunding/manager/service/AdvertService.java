/** 
 * Project Name:Atcrowdfunding-manager-api 
 * File Name:AdvertService.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service 
 * Date:2019年6月2日下午3:29:25 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service;

import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: AdvertService 
* @Description: 广告管理服务接口
* @author Chen Jiang Lin 
* @date 2019-06-02 15:29
*  
*/
public interface AdvertService {

    Advertisement getAdvertById(Integer id);

	Page queryPage(Map<String, Object> paramMap);

	int queryCount(Map<String, Object> paramMap);

	int updateAdvert(Advertisement advertisement, AjaxResult result);

	int deleteAdvert(Integer id);

	int deleteBatchAdvertByVO(Data data);
 
	int saveAdvert(Advertisement advertisement, AjaxResult result);
	
	
}
  
