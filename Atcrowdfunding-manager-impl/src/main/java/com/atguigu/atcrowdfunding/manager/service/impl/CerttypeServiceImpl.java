/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:CerttypeServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service.impl 
 * Date:2019年6月14日下午2:18:11 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.manager.dao.AccountTypeCertMapper;
import com.atguigu.atcrowdfunding.manager.service.CerttypeService;

/** 
* @ClassName: CerttypeServiceImpl 
* @Description: 账户资质类型实现类
* @author Chen Jiang Lin 
* @date 2019-06-14 14:18
*  
*/
@Service
public class CerttypeServiceImpl implements CerttypeService {

	@Autowired
	private AccountTypeCertMapper accountTypeCertMapper;

	/** 
	* TODO 查询所有账户资类型
	* @see com.atguigu.atcrowdfunding.manager.service.CerttypeService#queryCertAccttype() 
	*/
	public List<Map<String, Object>> queryCertAccttype() {

		return accountTypeCertMapper.queryCertAccttype(); 
	}

	/** 
	* TODO 添加账户类型
	* @see com.atguigu.atcrowdfunding.manager.service.CerttypeService#inertAcctTypeCert(java.util.Map) 
	*/
	public int inertAcctTypeCert(Map<String, Object> paramMap) {
		
		return accountTypeCertMapper.inertAcctTypeCert(paramMap); 
	}

	/** 
	* TODO  删除账户类型
	* @see com.atguigu.atcrowdfunding.manager.service.CerttypeService#deleteAcctTypeCert(java.util.Map) 
	*/
	public int deleteAcctTypeCert(Map<String, Object> paramMap) {

		return accountTypeCertMapper.deleteAcctTypeCert(paramMap); 
	}
	
	
	
}
  
