/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:CertServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.service.impl 
 * Date:2019年6月14日下午2:32:49 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.bean.MemberCert;
import com.atguigu.atcrowdfunding.manager.dao.CertMapper;
import com.atguigu.atcrowdfunding.manager.service.CertService;

/** 
* @ClassName: CertServiceImpl 
* @Description: 资质实现类
* @author Chen Jiang Lin 
* @date 2019-06-14 14:32
*  
*/
@Service
public class CertServiceImpl implements CertService {

	@Autowired
	private CertMapper certMapper;

	/** 
	* TODO 查询所有资质
	* @see com.atguigu.atcrowdfunding.manager.service.CertService#queryAllCert() 
	*/
	public List<Cert> queryAllCert() {

		return certMapper.selectAll(); 
	}

	/** 
	* TODO 根据用户类型查询
	* @see com.atguigu.atcrowdfunding.manager.service.CertService#queryCertByAccttype(java.lang.String) 
	*/
	public List<Cert> queryCertByAccttype(String accttype) {

		return certMapper.queryCertByAccttype(accttype); 
	}

	/** 
	* TODO 保存图片
	* @see com.atguigu.atcrowdfunding.manager.service.CertService#saveMemberCert(java.util.List) 
	*/
	public void saveMemberCert(List<MemberCert> memberCertImgs) {

		for (MemberCert memberCert : memberCertImgs) {
			certMapper.insertMemberCert(memberCert);
		}
		
	}

	
}
  
