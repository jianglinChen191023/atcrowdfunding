/** 
 * Project Name:Atcrowdfunding-manager-api 
 * File Name:CertService.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service 
 * Date:2019年6月14日下午2:31:58 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service;

import java.util.List;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.MemberCert;

/** 
* @ClassName: CertService 
* @Description: 资质管理接口
* @author Chen Jiang Lin 
* @date 2019-06-14 14:31
*  
*/
public interface CertService {
 
	List<Cert> queryAllCert();

	List<Cert> queryCertByAccttype(String accttype);

	void saveMemberCert(List<MemberCert> memberCertImgs);

	

	
	
}
  
