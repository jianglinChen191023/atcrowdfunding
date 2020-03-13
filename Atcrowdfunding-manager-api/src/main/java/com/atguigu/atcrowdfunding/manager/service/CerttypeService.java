/** 
 * Project Name:Atcrowdfunding-manager-api 
 * File Name:Certtype.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service 
 * Date:2019年6月14日下午2:14:29 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;
import java.util.Map;

/** 
* @ClassName: Certtype 
* @Description: 账号资质类型接口
* @author Chen Jiang Lin 
* @date 2019-06-14 14:14
*  
*/
public interface CerttypeService {

	List<Map<String, Object>> queryCertAccttype();

	int inertAcctTypeCert(Map<String, Object> paramMap);

	int deleteAcctTypeCert(Map<String, Object> paramMap);

}
  
