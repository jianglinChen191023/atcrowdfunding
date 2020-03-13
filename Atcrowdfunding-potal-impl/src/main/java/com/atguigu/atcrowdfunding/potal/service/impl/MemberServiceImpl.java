/** 
 * Project Name:Atcrowdfunding-potal-impl 
 * File Name:MemberServiceImpl.java 
 * Package Name:com.atguigu.atcrowdfunding.potal.service.impl 
 * Date:2019年6月12日下午3:31:37 
 * 
 */  
      
package com.atguigu.atcrowdfunding.potal.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.exception.FailException;
import com.atguigu.atcrowdfunding.potal.dao.MemberMapper;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

/** 
* @ClassName: MemberServiceImpl 
* @Description: 会员服务实现类
* @author Chen Jiang Lin 
* @date 2019-06-12 15:31
*  
*/
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memeberMapper;
	
	/** 
	* TODO 会员登录
	* @see com.atguigu.atcrowdfunding.potal.service.MemberService#getMemberLogin(java.util.Map, com.atguigu.atcrowdfunding.util.AjaxResult) 
	*/
	public Member getMemberLogin(Map<String, Object> paramMap, AjaxResult result) {
		if(memeberMapper.queryCount(paramMap)>1) {
			result.setMessage("用户数据异常,请联系管理员处理!");
			throw new FailException("用户数据异常,请联系管理员处理!_账号为 : "+paramMap.get("loginacct"));
		}
		Member member = memeberMapper.getMemberLogin(paramMap);
		if(member==null) {
			result.setMessage("用户账号或密码不正确!");
			throw new FailException("用户账号或密码不正确!");
		} 
		
		return member;
	}

	/** 
	* TODO 修改账户类型
	* @see com.atguigu.atcrowdfunding.potal.service.MemberService#updateAcctType(com.atguigu.atcrowdfunding.bean.Member) 
	*/
	public void updateAcctType(Member loginMember) {

		memeberMapper.updateByPrimaryKey(loginMember);
	}

	/** 
	* TODO 添加实名认证信息
	* @see com.atguigu.atcrowdfunding.potal.service.MemberService#updateBasicinfo(com.atguigu.atcrowdfunding.bean.Member) 
	*/
	public void updateBasicinfo(Member loginMember) {

		memeberMapper.updateBasicinfo(loginMember);
	}

	/** 
	* TODO 简单描述该方法的实现功能（可选）. 
	* @see com.atguigu.atcrowdfunding.potal.service.MemberService#updateEmail(com.atguigu.atcrowdfunding.bean.Member) 
	*/
	public void updateEmail(Member loginMember) {

		memeberMapper.updateEmail(loginMember);
	}

	/** 
	* TODO 更新申请流程 1 - 正在申请中
	* @see com.atguigu.atcrowdfunding.potal.service.MemberService#updateauthstatus(com.atguigu.atcrowdfunding.bean.Member) 
	*/
	public void updateauthstatus(Member loginMember) {

		memeberMapper.updateauthstatus(loginMember);
	}

	/** 
	* TODO 通过id查询会员
	* @see com.atguigu.atcrowdfunding.potal.service.MemberService#getMemberById(java.lang.Integer) 
	*/
	public Member getMemberById(Integer memberid) {

		return memeberMapper.getMemberById(memberid); 
	}

	/** 
	* TODO 根据会员id查询资质信息
	* @see com.atguigu.atcrowdfunding.potal.service.MemberService#queryCertByMemberid(java.lang.Integer) 
	*/
	public List<Map<String, Object>> queryCertByMemberid(Integer memberid) {

		return memeberMapper.queryCertByMemberid(memberid); 
	}

}
  
