package com.tanlifei.support.okhttp.json;

public class BasePager<T>  {


    static final String LIST_KEY = "list";


    private int pageSize;
    private T list;
    private int pageNumber;
    private int totalRow;
    private int totalPage;


    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }


}
