/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:TestLike.java 
 * Package Name:junit.test 
 * Date:2019年6月12日上午10:43:50 
 * 
 */  
      
package junit.test;  
  
/** 
* @ClassName: TestLike 
* @Description: 测试模糊查询
* @author Chen Jiang Lin 
* @date 2019-06-12 10:43
*  
*/
public class TestLike {

	public static void main(String[] args) {
		
		String name = "CJL";
		String like = "C";
		System.out.println(name.contains(like));
		
		String like1 = "CC";
		System.out.println(name.contains(like1));
	}
	
}
  
