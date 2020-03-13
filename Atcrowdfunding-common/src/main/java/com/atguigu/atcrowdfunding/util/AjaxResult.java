/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:AjaxResult.java 
 * Package Name:com.atguigu.arcrowdfunding.util 
 * Date:2019年5月23日上午10:18:41 
 * 
 */  
      
package com.atguigu.atcrowdfunding.util;  
  
/** 
* @ClassName: AjaxResult 
* @Description: 异步请求工具类
* @author Chen Jiang Lin 
* @date 2019-05-23 10:18
*  
*/
public class AjaxResult {

	private boolean success;
	private String message;
	private Page page;
	
	private Object Data ;
	
	public boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Object getData() {
		return Data;
	}

	public void setData(Object data) {
		Data = data;
	}

	@Override
	public String toString() {
		return "AjaxResult [success=" + success + ", message=" + message + ", page=" + page + ", Data=" + Data + "]";
	}


	
	
	
	
	
}
  
