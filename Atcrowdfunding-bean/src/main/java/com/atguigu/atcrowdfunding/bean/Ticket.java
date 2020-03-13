/** 
 * Project Name:Atcrowdfunding-bean 
 * File Name:Ticket.java 
 * Package Name:com.atguigu.atcrowdfunding.bean 
 * Date:2019年6月14日上午9:02:06 
 * 
 */  
      
package com.atguigu.atcrowdfunding.bean;  
  
/** 
* @ClassName: Ticket 
* @Description: 流程记录实体类
* @author Chen Jiang Lin 
* @date 2019-06-14 09:02
*  
*/
public class Ticket {

	private Integer id,memberid;
	private String piid,status,authcode,pstep;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getPstep() {
		return pstep;
	}
	public void setPstep(String pstep) {
		this.pstep = pstep;
	}
	
	

	
}
  
