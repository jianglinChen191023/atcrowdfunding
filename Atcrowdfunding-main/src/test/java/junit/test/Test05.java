/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:Test05.java 
 * Package Name:junit.test 
 * Date:2019年5月29日下午3:48:29 
 * 
 */  
      
package junit.test;  
  
/** 
* @ClassName: Test05 
* @Description: 截取字符测试
* @author Chen Jiang Lin 
* @date 2019-05-29 15:48
*  
*/
public class Test05 {

		public static void main(String[] args) {
		String str ="232ljsfsf.sdfl23.ljsdfsdfsdfss.23423.sdfsdfsfd";
		//根据第二个点的位置，截取 字符串。得到结果 result
		String result=str.substring(str.lastIndexOf(".")+1);
		//输出结果
		System.out.println(result);
		}
}
  
