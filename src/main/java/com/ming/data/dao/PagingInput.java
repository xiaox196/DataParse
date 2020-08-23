package com.ming.data.dao;



/**
 * 输入参数：分页
 *
 * @author 
 */
public class PagingInput {

    private int pageIndex = 1;

    private int pageSize = 20;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        pageIndex = pageIndex < 1 ? 1 : pageIndex;
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        pageSize = pageSize > 1000 ? 1000 : pageSize;
        pageSize = pageSize < 1 ? 1 : pageSize;
        this.pageSize = pageSize;
    }

	@Override
	public String toString()
	{
		return "PagingInput [pageIndex=" + pageIndex + ", pageSize=" + pageSize + "]";
	}
}
