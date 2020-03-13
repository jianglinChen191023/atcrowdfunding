/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:Test03.java 
 * Package Name:junit.test 
 * Date:2019年5月24日上午2:03:39 
 * 
 */

package junit.test;

import java.util.List;

/**
 * @ClassName: Test03
 * @Description: 空指针面试题
 * @author Chen Jiang Lin
 * @date 2019-05-24 02:03
 * 
 */
public class Test03 {

	public static void main(String[] args) throws InterruptedException {
//		 String str = null ;
//		 str.toUpperCase();//成员方法 : 依赖于对象才能调用
		 

		 /*
		 Thread t = null; 
		 t.sleep(3000); //静态方法,正常是通过类名称类调用(Thread.sleep(3000	)),而不推荐用对象来调用
		 */
//		Thread t = null;
//		Thread.sleep(3000);
		
		//Exception in thread "main" java.lang.NumberFormatException:
//		String i = null;
//		int j = Integer.parseInt(i);
//		System.out.println(j);
		
		//Exception in thread "main" java.lang.NullPointerException
//		Integer i = null;
//		int j = i ; //自动拆箱
//		System.out.println(j);
		
//		List<String> list = null;
//		
//		Exception in thread "main" java.lang.NullPointerException
//		for (int i = 0; i < list.size(); i++) {
//			
//		}
		
//		for (String str : args) {
//			System.out.println("str="+str);
//		}
		
		
	}

}
