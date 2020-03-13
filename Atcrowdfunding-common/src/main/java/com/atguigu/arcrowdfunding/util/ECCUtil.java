/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:ECCUtil.java 
 * Package Name:com.atguigu.arcrowdfunding.util 
 * Date:2019年5月28日上午11:18:20 
 * 
 */

package com.atguigu.arcrowdfunding.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: ECCUtil
 * @Description: 校验工具类
 * @author Chen Jiang Lin
 * @date 2019-05-28 11:18
 * 
 */
public class ECCUtil {

	/**
	 * 校验email是否合法
	 * 
	 * @author Chen Jiang Lin
	 * @param email
	 * @return true合法 false不合法
	 */
	public static Boolean ECCEmail(String email,AjaxResult result) {
		Boolean bln = false;
		
		if(StringUtil.isEmpty(email)) {
			result.setMessage("邮箱不能为空!");
			return false;
		}else {						
			Pattern p = Pattern.compile("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");// 复杂匹配
			Matcher m = p.matcher(email);
			if(m.find()) {
				if(!"com".equals(email.substring(email.lastIndexOf(".")+1))) {
					result.setMessage("邮箱后缀必须为com ");
				}else {
					bln = true;
				}
			}else {
				result.setMessage("Email-不合法的邮箱地址!");
			}
			return bln;
		}
		
	}

	/**
	 * 
	 * ECCAccount:(校验账号). <br/>
	 * TODO(用于验证账号是否合法).<br/>
	 * 用户账号不能为空! 用户账号不能小于6位数!
	 * 
	 * 
	 * @author Chen Jiang Lin
	 */
	public static Boolean ECCAccount(String accout, AjaxResult result) {
		if (StringUtil.isEmpty(accout)) {
			result.setMessage("用户账号不能为空!");
			return false;
		} else if (accout.length() < 6) {
			result.setMessage("用户账号不能小于6位数!");
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 
	 * ECCName:(校验名称). <br/>
	 * TODO(用于验证名称是否合法).<br/>
	 * 名称不能为空! 名称必须为 3-20 个字符!
	 * 
	 * 
	 * @author Chen Jiang Lin
	 */
	public static Boolean ECCName(String name, AjaxResult result) {
		if (StringUtil.isEmpty(name)) {
			result.setMessage("名称不能为空!");
			return false;
		} else if (name.length() < 3 || name.length() > 20) {
			result.setMessage("名称必须为 3-20 个字符!");
			return false;
		} else {
			return true;
		}

	}

}
