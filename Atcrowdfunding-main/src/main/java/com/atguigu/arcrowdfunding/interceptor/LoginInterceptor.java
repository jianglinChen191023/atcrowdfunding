/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:LoginInterceptor.java 
 * Package Name:com.atguigu.arcrowdfunding.interceptor 
 * Date:2019年6月7日下午5:46:54 
 * 
 */  
      
package com.atguigu.arcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.arcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.User;

/** 
* @ClassName: LoginInterceptor 
* @Description: 登录拦截器,未登录系统,禁止访问相关资源
* @author Chen Jiang Lin 
* @date 2019-06-07 17:46
*  
*/
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	/** 
	* TODO 请求拦截处理
	* @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object) 
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//1.定义哪些路径是不需要拦截的(将这些路径称为白名单)
		Set<String> uri = new HashSet<String>();
		uri.add("/index.htm");
		uri.add("/user/reg.htm");
		uri.add("/user/reg.do");
		uri.add("/login.htm");
		uri.add("/doLogin.do");
		uri.add("/logout.do");
		uri.add("/member.htm");
		uri.add("/TestSmrz.htm");
		
		//获取请求路径
		String serletPath = request.getServletPath();
		
		if(uri.contains(serletPath)) {
			return true ;
		}
		
		//2.判断用户是否登录,如果登录就放行
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Const.LOGIN_USER);
		Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
		if(user != null || member != null) {
			return true;
		}else {
			response.sendRedirect(request.getContextPath()+"/login.htm");
			return false;
		}
	}
	
	

}
