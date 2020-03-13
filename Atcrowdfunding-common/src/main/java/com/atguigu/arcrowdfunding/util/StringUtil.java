/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:StringUtil.java 
 * Package Name:com.atguigu.arcrowdfunding.util 
 * Date:2019年5月23日下午2:50:56 
 * 
 */  
      
package com.atguigu.arcrowdfunding.util;  
  
/** 
* @ClassName: StringUtil 
* @Description:	 判断字符串是否为空的工具类
* @author Chen Jiang Lin 
* @date 2019-05-23 14:50
*  
*/
public class StringUtil {

	public static boolean isEmpty(String s) {
		return s == null || "".equals(s);  //     s == null | s.equals("");  //位与,逻辑与区别,非空字符串放置在前面,避免空指针
	}			
		
	public static boolean isNotEmpty(String s) {
		return !isEmpty(s);
	}

}
  
