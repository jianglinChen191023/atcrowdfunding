package com.atguigu.atcrowdfunding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Member;
import com.atguigu.atcrowdfunding.bean.Permission;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.exception.FailException;
import com.atguigu.atcrowdfunding.manager.service.UserService;
import com.atguigu.atcrowdfunding.potal.service.MemberService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.MD5Util;
import com.atguigu.atcrowdfunding.util.StringUtil;

/**
 * @ClassName: DispatcherController
 * @Description: 处理器类(DispatcherController),处理请求方式,判断并跳转页面
 * @author 陈江林
 * @date 2019-05-15 14:25
 * 
 */
//@Scope("prototype") 线程安全问题 
//@Controller
public class DispatcherController_02 {
	/**
	 * 线程安全问题: @Scope("prototype") 不推荐成员变量 , 数据放到其他地方(request,) 推荐局部变量
	 * 
	 * request一次有效,独享 ThreadLocal: 1.同一个线程数据共享 (1)可以解决同一个线程当中线程安全的问题,多层可以使用同一个数据
	 * 
	 */
	
	@Autowired
	private UserService userService;

	@Autowired
	private MemberService memberService;

	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(String closeCookieToLogin, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		boolean toLogin = false;
		// 判断是否切换账号 - 清空Cookie并跳转到登录页面
		if ("1".equals(closeCookieToLogin)) {
			Cookie[] cookies = request.getCookies();

			// 清空cookie
			for (Cookie cookie : cookies) {

				cookie.setMaxAge(0);

				cookie.setPath("/");

				response.addCookie(cookie);

			}
			toLogin = true;
		}
		// 判断是否需要自动登录
		// 如果之前登录并保存过,cookie中存放了用户信息,需要获取cookie中的信息,并进行数据库的验证

		boolean needLogin = true;
		String logintype = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) { // 如果客户端禁用了Cookie,那么无法获取Cookie信息

			for (Cookie cookie : cookies) {
				if ("logincode".equals(cookie.getName())) {
					String logincode = cookie.getValue();
					System.out.println("获取到Cookie中的键值对" + cookie.getName() + "============" + logincode);

					String[] split = logincode.split("&");
					if (split.length == 3) {
						String loginacct = split[0].split("=")[1];
						String userpwd = split[1].split("=")[1];
						logintype = split[2].split("=")[1];

						if ("member".equals(logintype)) {
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("loginacct", loginacct);
							paramMap.put("userpswd", userpwd);

							Member member = memberService.getMemberLogin(paramMap, null);
							if (member != null) {
								session.setAttribute(Const.LOGIN_MEMBER, member);
								needLogin = false;
							}

						} else if ("user".equals(logintype)) {
							Map<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("loginacct", loginacct);
							paramMap.put("userpswd", userpwd);

							User user = userService.getUserLogin(paramMap, null);
							if (user != null) {
								session.setAttribute(Const.LOGIN_USER, user);
								needLogin = false;
							}
							// 设置权限
							List<Permission> mypermissions = userService.queryPermissionByUserid(user.getId());

							Permission permissionRoot = new Permission();

							Map<Integer, Permission> map = new HashMap<Integer, Permission>();

							Set<String> myUris = new HashSet<String>(); // 用于拦截器拦截许可权限

							for (Permission innerPermission : mypermissions) {
								map.put(innerPermission.getId(), innerPermission);

								myUris.add("/" + innerPermission.getUrl());
							}

							session.setAttribute(Const.MY_URIS, myUris);

							for (Permission permission : mypermissions) {

								Permission child = permission;// 假设为子菜单
								if (child.getPid() == null) {
									permissionRoot = permission;
								} else {
									// 父节点
									Permission parent = map.get(child.getPid());
									if (parent == null) {

									} else {
										parent.getChildren().add(child);
									}

								}

							}
							session.setAttribute("permissionRoot", permissionRoot);
						}
					}

				}
			}
		}
		if (toLogin) {
			return "login";
		} else if (needLogin) {
			return "login";
		} else {
			if ("member".equals(logintype)) {
				return "redirect:/member.htm";
			} else if ("user".equals(logintype)) {

				return "redirect:/main.htm";
			}
		}

		return "login";
	}

	@RequestMapping("/reg")
	public String reg() {
		
		return "reg";
	}

	@RequestMapping("/main")
	public String main() {

		return "main";
	}

	@RequestMapping("/member")
	public String member() {

		return "member/member";
	}

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/role")
	public String role() {
		return "role";
	}

	@RequestMapping("/permission")
	public String permission() {
		return "permission";
	}

	@RequestMapping("/auth_cert")
	public String auth_cert() {
		return "auth_cert";
	}

	@RequestMapping("/auth_adv")
	public String auth_adv() {
		return "auth_adv";
	}

	@RequestMapping("/auth_project")
	public String auth_project() {
		return "auth_project";
	}

	@RequestMapping("/cert")
	public String cert() {
		return "cert";
	}

	@RequestMapping("/type")
	public String type() {
		return "type";
	}

	@RequestMapping("/process")
	public String process() {
		return "process";
	}

	@RequestMapping("/advertisement")
	public String advertisement() {
		return "advertisement";
	}

	@RequestMapping("/message")
	public String message() {
		return "message";
	}

	@RequestMapping("/project_type")
	public String project_type() {

		return "project_type";
	}

	@RequestMapping("/tag")
	public String tag() {
		return "tag";
	}

	@RequestMapping("/param")
	public String param() {
		return "param";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 销毁session对象,或清理session域
//		c.setMaxAge(0);
		return "redirect:/index.htm";
	}

	// 异步请求
	// @ResponseBody 结合Jackson 组件,将返回结果转换为字符串,将JSON串以流的形式返回给客户端
	@RequestMapping("/doLogin")
	@ResponseBody
	public Object doLogin(String loginacct, String userpswd, String type, HttpSession session, String rememberme,
			HttpServletResponse response) {
		Cookie ck = null;
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("loginacct", loginacct);
			paramMap.put("userpswd", MD5Util.digest(userpswd));
			paramMap.put("type", type);

			if ("member".equals(type)) {

				Member member = memberService.getMemberLogin(paramMap, result);

				session.setAttribute(Const.LOGIN_MEMBER, member);

				if ("1".equals(rememberme)) {
					String logincode = "loginacct=" + member.getLoginacct() + "&userpwd=" + member.getUserpswd()
							+ "&logintype=member";
					System.out.println("用户-存放到Cookie中的键值对: loginacct:::::::" + logincode);

					Cookie c = new Cookie("logincode", logincode);

					c.setMaxAge(60 * 60 * 24 * 14); // 2周时间Cookie过期 单位秒
					c.setPath("/"); // 表示任何请求路径都可以访问Cookie

					response.addCookie(c);
				}

				result.setSuccess(true);
			} else if ("user".equals(type)) {
				User user = userService.getUserLogin(paramMap, result);

				session.setAttribute(Const.LOGIN_USER, user);

				result.setSuccess(true);

				if ("1".equals(rememberme)) {
					String logincode = "loginacct=" + user.getLoginacct() + "&userpwd=" + user.getUserpswd()
							+ "&logintype=user";
					System.out.println("用户-存放到Cookie中的键值对: loginacct:::::::" + logincode);

					ck = new Cookie("logincode", logincode);

					ck.setMaxAge(60 * 60 * 24 * 14); // 2周时间Cookie过期 单位秒
					ck.setPath("/"); // 表示任何请求路径都可以访问Cookie

					response.addCookie(ck);
				}

				// ---------------------
				// 加载当前登录用户的所拥有的权限.

				List<Permission> mypermissions = userService.queryPermissionByUserid(user.getId());

				if (mypermissions.get(0) == null) {
					result.setMessage("用户没有被授权,请联系管理员处理!");
					throw new FailException("用户没有授权,id为 : " + user.getId());
				}

				Permission permissionRoot = new Permission();

				Map<Integer, Permission> map = new HashMap<Integer, Permission>();

				Set<String> myUris = new HashSet<String>(); // 用于拦截器拦截许可权限

				for (Permission innerPermission : mypermissions) {
					map.put(innerPermission.getId(), innerPermission);

					myUris.add("/" + innerPermission.getUrl());
				}

				session.setAttribute(Const.MY_URIS, myUris);

				for (Permission permission : mypermissions) {

					Permission child = permission;// 假设为子菜单
					if (child.getPid() == null) {
						permissionRoot = permission;
					} else {
						// 父节点
						Permission parent = map.get(child.getPid());
						if (parent == null) {

						} else {
							parent.getChildren().add(child);
						}

					}

				}
				session.setAttribute("permissionRoot", permissionRoot);
			} else {

			}

			result.setData(type);

			// ---------------------
		} catch (Exception e) {
			if (result.getMessage() == null) {
				result.setMessage("登录失败!");
			}
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	// 同步请求:
	/*
	 * @RequestMapping("/doLogin") public String doLogin(String loginacct,String
	 * userpswd,String type,HttpSession session) { Map<String,Object> paramMap = new
	 * HashMap<String,Object>(); paramMap.put("loginacct", loginacct);
	 * paramMap.put("userpswd", userpswd); paramMap.put("type", type);
	 * 
	 * User user = userService.getUserLogin(paramMap);
	 * 
	 * session.setAttribute(Coust.LOGIN_USER, user);
	 * 
	 * return "redirect:/main.htm"; }
	 */

}
