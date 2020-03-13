/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:TestTimer.java 
 * Package Name:junit.test 
 * Date:2019年6月12日上午2:04:23 
 * 
 */  
      
package junit.test;

import java.util.Timer;
import java.util.TimerTask;

/** 
* @ClassName: TestTimer 
* @Description: 测试Timer - 定时任务
* @author Chen Jiang Lin 
* @date 2019-06-12 02:04
*  
*/
public class TestTimer {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("I LIVE YOU");
			}
		}, 3000,1000);  //3000,1000); 延长3秒 每一秒执行
						//3000);	//3秒后执行一次
		
	}
	
}
  
