/** 
 * Project Name:Atcrowdfunding-potal-api 
 * File Name:MemberService.java 
 * Package Name:com.atguigu.atcrowdfunding.potal.service 
 * Date:2019年6月12日下午3:29:25 
 * 
 */  
      
package com.atguigu.atcrowdfunding.potal.service;

import java.util.List;
import java.util.Map;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.util.AjaxResult;

/** 
* @ClassName: MemberService 
* @Description: 会员服务类
* @author Chen Jiang Lin 
* @date 2019-06-12 15:29
*  
*/
public interface MemberService {

	Member getMemberLogin(Map<String, Object> paramMap, AjaxResult result);

	void updateAcctType(Member loginMember);

	void updateBasicinfo(Member loginMember);

	void updateEmail(Member loginMember);

	void updateauthstatus(Member loginMember);

	Member getMemberById(Integer memberid);

	List<Map<String, Object>> queryCertByMemberid(Integer memberid);

}
  
