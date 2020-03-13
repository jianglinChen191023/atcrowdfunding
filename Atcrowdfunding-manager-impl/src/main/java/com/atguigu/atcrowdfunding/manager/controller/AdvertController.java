/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:AdvertController.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.controller 
 * Date:2019年6月2日下午3:24:43 
 * 
 */  
      
package com.atguigu.atcrowdfunding.manager.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.bean.User;
import com.atguigu.atcrowdfunding.manager.service.AdvertService;
import com.atguigu.atcrowdfunding.util.AjaxResult;
import com.atguigu.atcrowdfunding.util.Const;
import com.atguigu.atcrowdfunding.util.Page;
import com.atguigu.atcrowdfunding.util.StringUtil;
import com.atguigu.atcrowdfunding.vo.Data;

/** 
* @ClassName: AdvertController 
* @Description: 广告管理控制器
* @author Chen Jiang Lin 
* @date 2019-06-02 15:24
*  
*/
@Controller
@RequestMapping("/advert")
public class AdvertController {

	@Autowired
	private AdvertService advertService;
	
	@RequestMapping("index")
	public String index() {
		
		return "advert/index";
	}
	
	@RequestMapping("toIndex")
	public String toIndex() {
		
		return "advert/index";
	}
	
	@RequestMapping("toUpdate")
	public String toUpdate(Integer id,Map<String, Advertisement> map) {
		
		Advertisement advert = advertService.getAdvertById(id);
		map.put("advert", advert);
		
		return "advert/update";
	}
	
	@RequestMapping("toAdd")
	public String toAdd() {
		
		return "advert/add";
	}
	
	/**
	*  分页查询
	*/
	@RequestMapping("/doIndex")
	@ResponseBody
	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1")Integer pageno,
			@RequestParam(value="pagesize",required=false,defaultValue="10")Integer pagesize,
			String queryText) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("pageno", pageno);
			paramMap.put("pagesize", pagesize);
			
			if(StringUtil.isNotEmpty(queryText)) {
				
				if(queryText.contains("%")) {
					queryText = queryText.replaceAll("%", "\\\\%");
				}
				
				if(queryText.contains("_")) {
					queryText = queryText.replaceAll("_", "\\\\_");
				}
				
				paramMap.put("queryText", queryText);
			}
			
			Page page = advertService.queryPage(paramMap);

			result.setSuccess(true);
			result.setPage(page);
			
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("查询数据失败!");
			e.printStackTrace();
			
		}
		
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
	
	
	/**
	 * 
	 * doAdvert:(条件查询,数据是否存在). <br/> 
	 * TODO(适用于表单校验).<br/> 
	 * count为1 返回true 表示已存在
	 * 为0 返回false 表示可用 
	 * 大于1 返回false和异常信息. 代表异常. <br/> 
	 * 
	 * @author Chen Jiang Lin 
	 * @param name
	 * @return 
	 */
	@RequestMapping("/doAdvert")
	@ResponseBody
	public Object doAdvert(String name) {
		AjaxResult result = new AjaxResult();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("name", name);

			int count = advertService.queryCount(paramMap);
			result.setSuccess(count==1);
			if(count > 1) {
				result.setMessage("数据异常,请联系管理员处理!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("查询失败!");
		}
		
		return result;
	}
	
	/**
	 * 修改功能实现	
	 * Controller-><-ServiceImpl return-><-ECCAccout return
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public Object doUpdate(Advertisement advertisement,AjaxResult result) throws RuntimeException{
		try {
			int count = advertService.updateAdvert(advertisement,result);
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
//			result.setMessage("保存数据失败!");
		}
		 
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
	
	/** 
	 *  根据id 删除功能
	 * @author Chen Jiang Lin 
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDelete")
	@ResponseBody 
	public Object doDelete(Integer id) {
		AjaxResult result = new AjaxResult();
		try {
			result.setSuccess(advertService.deleteAdvert(id) == 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("删除失败!");
			
		}
		
		return result;
	}
	
	/**
	 *  批量 删除功能
	 * @author Chen Jiang Lin 
	 * @param id
	 * @return
	 */
	@RequestMapping("/doDeleteBatch")
	@ResponseBody 
	public Object doDeleteBatch(Data data) {
		
		AjaxResult result = new AjaxResult();
		try {
			result.setSuccess(advertService.deleteBatchAdvertByVO(data) == data.getDatasAdvert().size());
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("删除失败!");
			
		}
		
		return result;
	}
	

	/**
	 * 添加功能
	 * Controller-><-ServiceImpl return-><-ECCAccout return
	 */
	/*
	@RequestMapping("/doAdd")
	@ResponseBody
	public Object doAdd(Advertisement advertisement,AjaxResult result) throws RuntimeException{
		try {
			int count = advertService.saveAdvert(advertisement,result);
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
	*/

	/**
	 * 添加功能
	 */
	@RequestMapping("/doAdd") 
	@ResponseBody 
	public Object doAdd(Advertisement advertisement,AjaxResult result,HttpServletRequest request,HttpSession session) throws RuntimeException{
		try {
			MultipartHttpServletRequest mreq = (MultipartHttpServletRequest)request;
			
			MultipartFile mfile = mreq.getFile("advpic");
			
			String name = mfile.getOriginalFilename(); //java.jpg
			String extname = name.substring(name.lastIndexOf("."));
			
			String iconpath = UUID.randomUUID().toString()+extname; //34234234.jpg
			
			ServletContext servletContext = session.getServletContext();
			String realpath = servletContext.getRealPath("/pics");
			
			String path = realpath+ "\\adv\\"+iconpath;
			
			mfile.transferTo(new File(path));
			
			User user = (User) session.getAttribute(Const.LOGIN_USER);
			
			advertisement.setUserid(user.getId());
			advertisement.setStatus("1");
			advertisement.setIconpath(iconpath);
			
			int count = advertService.saveAdvert(advertisement,result);
			result.setSuccess(count==1);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage("上传失败!"); 
		}
		return result;//将对象序列化为JSON字符串,一流的形式返回
	}
	
}
  
