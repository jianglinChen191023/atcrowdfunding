/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:Data.java 
 * Package Name:com.atguigu.arcrowdfunding.vo 
 * Date:2019年5月30日下午5:07:04 
 * 
 */  
      
package com.atguigu.arcrowdfunding.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.atguigu.atcrowdfunding.bean.Advertisement;
import com.atguigu.atcrowdfunding.bean.Role;
import com.atguigu.atcrowdfunding.bean.User;

/** 
* @ClassName: Data 
* @Description: 数据包
* @author Chen Jiang Lin 
* @date 2019-05-30 17:07
*  
*/
public class Data {

	private List<User> datas = new ArrayList<User>();
	private List<Role> datasRole = new ArrayList<Role>();
	private List<Advertisement> datasAdvert = new ArrayList<Advertisement>();
	private List<Integer> ids;
	
	public List<User> getDatas() {
		return datas;
	}
	public void setDatas(List<User> datas) {
		this.datas = datas;
	}
	public List<Role> getDatasRole() {
		return datasRole;
	}
	public void setDatasRole(List<Role> datasRole) {
		this.datasRole = datasRole;
	}
	public List<Advertisement> getDatasAdvert() {
		return datasAdvert;
	}
	public void setDatasAdvert(List<Advertisement> datasAdvert) {
		this.datasAdvert = datasAdvert;
	}
	public List<Integer> getIds() {
		return ids;
	}
	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	 
	

	
	
	
}
  
