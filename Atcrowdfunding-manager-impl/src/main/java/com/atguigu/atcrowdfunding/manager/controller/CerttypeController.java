/** 
 * Project Name:Atcrowdfunding-manager-impl 
 * File Name:CerttypeController.java 
 * Package Name:com.atguigu.atcrowdfunding.manager.controller 
 * Date:2019年6月14日下午2:12:55 
 * 
 */

package com.atguigu.atcrowdfunding.manager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.bean.Cert;
import com.atguigu.atcrowdfunding.manager.service.CertService;
import com.atguigu.atcrowdfunding.manager.service.CerttypeService;
import com.atguigu.atcrowdfunding.util.AjaxResult;

/**
 * @ClassName: CerttypeController
 * @Description: 账号资质类型控制类
 * @author Chen Jiang Lin
 * @date 2019-06-14 14:12
 * 
 */
@Controller
@RequestMapping("/certtype")
public class CerttypeController {

	@Autowired
	private CerttypeService certtypeService;

	@Autowired
	private CertService certService;

	@RequestMapping("/index")
	public String index(Map<String, Object> map) {
		//查询所有资质
		List<Cert> queryAllCert = certService.queryAllCert();
		
		map.put("allCert", queryAllCert);
		
		//查询资质与账号类型之间的关系(表示之前给账号类型分配过资质)
		List<Map<String, Object>> certAccttypeList = certtypeService.queryCertAccttype();
		map.put("certAccttypeList", certAccttypeList);
		
		return "certtype/index";
	}
					  
	@RequestMapping("/deleteAcctTypeCert")
	@ResponseBody
	public Object deleteAcctTypeCert(Integer certid,Integer accttype) {
		AjaxResult result = new AjaxResult();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("certid", certid);
			paramMap.put("accttype", accttype);
			
			int count = certtypeService.deleteAcctTypeCert(paramMap);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	
	@RequestMapping("/inertAcctTypeCert")
	@ResponseBody
	public Object inertAcctTypeCert(Integer certid,Integer accttype) {
		AjaxResult result = new AjaxResult();
		
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("certid", certid);
			paramMap.put("accttype", accttype);
			
			int count = certtypeService.inertAcctTypeCert(paramMap);
			result.setSuccess(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		return result;
	}
	

}
