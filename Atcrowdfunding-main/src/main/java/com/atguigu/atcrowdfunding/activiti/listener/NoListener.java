/** 
 * Project Name:Atcrowdfunding-main 
 * File Name:YesListener.java 
 * Package Name:com.atguigu.atcrowdfunding.activiti.listener 
 * Date:2019年6月12日上午1:52:29 
 * 
 */  
      
package com.atguigu.atcrowdfunding.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/** 
* @ClassName: YesListener 
* @Description: 流程监听器
* @author Chen Jiang Lin 
* @date 2019-06-12 01:52
*  
*/
public class NoListener implements ExecutionListener {

	/** 
	* TODO 监听到 - 拒绝 
	* @see org.activiti.engine.delegate.ExecutionListener#notify(org.activiti.engine.delegate.DelegateExecution) 
	*/
	public void notify(DelegateExecution execution) throws Exception {
		System.out.println("审批拒绝...");
	}

}
  
