package com.wordgen.util;

/**
 * 分页
 * 
 * @author RayWang
 *
 */
public class Pager {

    private long pageNo = 0; // 当前页号
    private long pageSize = 10; // 每页记录数
    private long rows = 0; // 总记录数
    private long pages = 0; // 总页数
    private long begin = 0; // 第一个记录
    private long end = 0; // 最后一个记录

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
//        calculate(); // 计算开始和结束记录
    }

    public long getPages() {
//        if (getRows() == 0) {
//            this.pages = 0;
//        } else {
//            this.pages = getRows() % getPageSize() == 0 ? getRows() / getPageSize()
//                    : getRows() / getPageSize() + 1;
//        }
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    private void calculate() {
        // 从0开始
        this.begin = (getPageNo() - 1) * getPageSize();
        this.end = getPageNo() * getPageSize() - 1;
        this.end = (this.end > getRows()) ? getRows() : this.end;
    }

}
