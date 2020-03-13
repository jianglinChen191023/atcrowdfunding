package com.atguigu.atcrowdfunding.listener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.manager.service.PermissionService;
import com.atguigu.atcrowdfunding.util.Const;
/** 
* @ClassName: DispatcherController
* @Description: 在服务器启动时,加载数据
* @author 陈江林
* @date 2019-05-15 14:29
*  
*/
public class StartSystemListener implements ServletContextListener {

	/**
	 * 将项目上下文路径(requset.getContextPath)放置到application域中。
	 * 在服务器启动时,创建application对象时需要执行的方法.
	 */
	public void contextInitialized(ServletContextEvent sce) {
		//1.将项目上下文路径(requset.getContextPath)放置到application域中。
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		application.setAttribute("APP_PATH", contextPath);
		System.out.println("APP_PATH..."+contextPath);
		
		//2.加载所有许可路径
		ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application); //获取服务器加载的IOC容器.
		PermissionService permissionService= ioc.getBean(PermissionService.class);
		List<Permission> queryAllPermission = permissionService.queryAllPermission();
		
		Set<String> allURIs = new HashSet<String>();
		
		for (Permission permission : queryAllPermission) {
			allURIs.add("/"+permission.getUrl());
		}
		
		application.setAttribute(Const.ALL_PERMISSION_URI, allURIs);
	}

	public void contextDestroyed(ServletContextEvent sce) {

	}

}
