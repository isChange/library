package com.igeek.library.entity;

import java.util.List;


@SuppressWarnings("all")
/**
 * 分页模型对象
 * T 是分页模型中对应模块的JavaBean
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 5;

    private Integer pageNo;     //当前页面数
    private Integer pageTotal;  //页面总数
    private Integer pageTotalCount; //总记录条数
    private Integer pageSize = PAGE_SIZE;// 每给页面的记录条数
    private List<T> items;   // 页面 书籍的数据
    private String url ;       //对应模块所需引入的地址

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        /**
         * 数据边界的有效检查
         * 校验设置的数据是否超过正常的访问
         * 该地方是校验查找的页数在不在指定范围内
         */
        if (pageNo < 1){
            this.pageNo = 1;
        }else if (pageNo > pageTotal){
            this.pageNo = pageTotal;
        }else {
            this.pageNo = pageNo;
        }
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageTotalCount=" + pageTotalCount +
                ", pagesize=" + pageSize +
                ", items=" + items +
                '}';
    }
}
