package com.cms.system.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** 
* 保存分页信息的类 
* @author wangs
*/
public class Pager {
	private int totalRows; // 总行数  

	private int pageSize = 30; // 每页显示的行数  

	private int currentPage = 1; // 当前页号  

	private int totalPages; // 总页数  

	private int startRow; // 当前页在数据库中的起始行  

	private String linkUrl; // 要跳转的URL  
	/**
	 * 表中的数据
	 */
	private List<Map<String, Object>> tbList = new ArrayList<Map<String, Object>>();

	/**
	 * 要输出的页面元素
	 */
	private List<Map<String, Object>> pageElements = new ArrayList<Map<String, Object>>();

	public List<Map<String, Object>> getPageElements() {
		return pageElements;
	}

	public void setPageElements(List<Map<String, Object>> pageElements) {
		this.pageElements = pageElements;
	}

	public Pager() {
	}

	/**
	 * 
	 * @param cpage 当前页
	 * @param pageSize 每页大小
	 * @param totalRows 总行数
	 */
	public Pager(int cpage, int pageSize, int totalRows) {
		// 定义pager对象，用于传到页面  
		this.totalRows = totalRows;
		if (pageSize == 0) {
			totalPages = totalRows / this.getPageSize();
			int mod = totalRows % this.getPageSize();
			if (mod > 0) {
				totalPages++;
			}
		} else {
			this.pageSize = pageSize;
			totalPages = totalRows / pageSize;
			int mod = totalRows % pageSize;
			if (mod > 0) {
				totalPages++;
			}
		}
		// 从Request对象中获取当前页号  
		// 如果当前页号为空，表示为首次查询该页  
		// 如果不为空，则刷新pager对象，输入当前页号等信息  
		if (cpage != 0) {
			this.setStart(cpage);
		} else {
			this.setStart(1);
		}
	}

	/** 
	 * 设定查询的起始行数 
	 *  
	 * @param currentPage 
	 */

	public void setStart(int currentPage) {
		this.currentPage = currentPage;
		startRow = (currentPage - 1) * pageSize + 1;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public List<Map<String, Object>> getTbList() {
		return tbList;
	}

	public void setTbList(List<Map<String, Object>> tbList) {
		this.tbList = tbList;
	}
	
	
	/***
	 * @describe 支持分页从给定的List集合中获取数据
	 * @date 2014-03-05
	 * @author sunjf
	 * @param allList 总记录集合
	 * @param curPage 当前请求页码
	 * @param pageSize 每页显示的记录数量
	 * @return 有符合记录数据返回pager对象，没有符合的数据返回null
	 * @throws BusiException
	 */
	public static Pager getPagerFromList(List<Map<String,Object>> allList,int curPage,int pageSize) throws BusiException{
		if(pageSize <= 0 || curPage < 1){
			throw new BusiException("704539","请求参数传入错误");
		}
		if(allList == null || allList.size() == 0){
			return null;
		}
		int totalCount = allList.size();//记录总数
		//计算记录总页数
		int totalPage = ((totalCount % pageSize) != 0 ) ? (totalCount / pageSize) + 1 : (totalCount / pageSize);
		
		if(curPage > totalPage){
			throw new BusiException("704539","请求参数传入错误");
		}
		//获取记录起始索引
		int preNum = (curPage - 1) * pageSize;
		//获取记录结束索引
		int sufNum = preNum + pageSize;
		if(sufNum > totalCount ) sufNum = totalCount;
		List<Map<String,Object>> subList = allList.subList(preNum, sufNum);
		Pager pager = new Pager(curPage,pageSize,totalCount);
		pager.setTbList(subList);
		return pager;
	}
	

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("totalRows:").append(this.totalRows).append("	").append("pageSize:").append(this.pageSize)
				.append("	").append("currentPage:").append(this.currentPage).append("	").append("totalPages:")
				.append(this.totalPages).append("	").append("startRow:").append(this.startRow).append("	")
				.append("linkUrl:").append(this.linkUrl).append("	").append("tbListSize:").append(this.tbList.size())
				.append("	").append("tbListResult:").append("\r\n");
		for (Map<String, Object> map : tbList) {
			Iterator<Map.Entry<String, Object>> rowit = map.entrySet().iterator();
			while (rowit.hasNext()) {
				Map.Entry<String, Object> entery = rowit.next();
				sb.append(entery.getKey()).append(": ").append(entery.getValue()).append("	");
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}
}
