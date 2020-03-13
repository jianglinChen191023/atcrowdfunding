/** 
 * Project Name:Atcrowdfunding-common 
 * File Name:Page.java 
 * Package Name:com.atguigu.arcrowdfunding.util 
 * Date:2019年5月25日上午10:04:33 
 * 
 */  
      
package com.atguigu.atcrowdfunding.util;

import java.util.List;

/** 
* @ClassName: Page 
* @Description: 分页工具
* @author Chen Jiang Lin 
* @date 2019-05-25 10:04
*  
*/
public class Page {

	private Integer pageno;	//当前页
	private Integer pagesize;	//查询数
	private List datas;	
	private Integer totalsize;	//总页数
	private Integer totalno;	//多少页
	private Integer startIndex;	// limit ?,10(pagesize)
	
	

	public Page() {
		super();
	}
	
	public Page(Integer pageno,Integer pagesize) {
		if(pageno <= 0) {
			this.pageno = 1;
		}else {
			this.pageno = pageno;
		}
		if(pagesize <= 	0) {
			this.pagesize = 10;
		}else {
			this.pagesize = pagesize;
		}
	}
	
	public Page(Integer pageno, Integer pagesize, List datas, Integer totalsize, Integer totalno) {
		super();
		this.pageno = pageno;
		this.pagesize = pagesize;
		this.datas = datas;
		this.totalsize = totalsize;
		this.totalno = totalno;
	}

	public Integer getPageno() {
		return pageno;
	}
	public void setPageno(Integer pageno) {
		this.pageno = pageno;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public Integer getTotalsize() {
		return totalsize;
	}
	public void setTotalsize(Integer totalsize) {
		this.totalsize = totalsize;
		this.totalno = (totalsize%pagesize)==0?(totalsize/pagesize):(totalsize/pagesize+1);
	}
	public Integer getTotalno() {
		return totalno;
	}
	private void setTotalno(Integer totalno) {
		this.totalno = totalno;
	}
	
	public Integer getStartIndex() {
		return (this.pageno - 1) * pagesize;
	}

	/** 
	* @see java.lang.Object#toString() 
	*/
	@Override
	public String toString() {
		return "Page [pageno=" + pageno + ", pagesize=" + pagesize + ", datas=" + datas + ", totalsize=" + totalsize
				+ ", totalno=" + totalno + ", startIndex=" + startIndex + "]";
	}

	
}
  
