/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:AuthInterceptor.java 
 * Package Name:com.atguigu.arcrowdfunding.interceptor 
 * Date:2019年6月7日下午7:36:17 
 * 
 */  
      
package com.atguigu.atcrowdfunding.interceptor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;

/** 
* @ClassName: AuthInterceptor 
* @Description: 访问权限拦截器
* @author Chen Jiang Lin 
* @date 2019-06-07 19:36
*  
*/
public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private PermissionService permissionService;
	
	/** 
	* TODO 权限拦截器,未授权禁止访问系统相关资源
	* @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object) 
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		//1.查询所有许可
		//如果每次拦截请求都查询数据库表的所有许可.效率比较低.
		/*
		List<Permission> queryAllPermission = permissionService.queryAllPermission();
		
		Set<String> allURIs = new HashSet<String>();
		
		for (Permission permission : queryAllPermission) {
			allURIs.add("/"+permission.getUrl());
		}
		*/
		
		//改进效率:在服务器启动时加载所有许可路径.并存放到application域中
		Set<String> allURIs = (Set<String>)request.getSession().getServletContext().getAttribute(Const.ALL_PERMISSION_URI);
		
		//2.判断请求路径是否在所有许可范围内
		String servletPath = request.getServletPath();
		if(allURIs.contains(servletPath)) {
			
			//3.判断请求路径是否在用户所拥有的权限内
			Set<String> myURIs = (Set<String>)request.getSession().getAttribute(Const.MY_URIS);
			if(myURIs.contains(servletPath)) {
				return true;
			}else {
				response.sendRedirect(request.getContextPath()+"/login.htm");
				return false;
			}
			
		}else {	//不在拦截范围内
			return true ;
		}
		
	}
	
}
  
