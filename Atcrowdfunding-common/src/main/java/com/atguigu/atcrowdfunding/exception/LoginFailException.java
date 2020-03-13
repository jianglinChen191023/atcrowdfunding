/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:LoginFailException.java 
 * Package Name:com.atguigu.arcrowdfunding.exception 
 * Date:2019年5月22日下午8:56:09 
 * 
 */  
      
package com.atguigu.atcrowdfunding.exception;  
  
/** 
* @ClassName: LoginFailException 
* @Description: 异常类
* @author Chen Jiang Lin 
* @date 2019-05-22 20:56
*  
*/
public class LoginFailException extends RuntimeException{

	/** 
	* @Fields serialVersionUID : 序列化
	*/ 
	private static final long serialVersionUID = 1L;

	public LoginFailException(String message) {
		super(message);
	}
	
}
  
