package pojo;

import java.util.List;

/**
 * Page是分页模型的对象
 * @param <T> 是具体的模型的javaBean类
 */
public class Page <T>{
    public static final Integer PAGE_SIZE = 4; // 定义每页展示items的数量
    // 完成分页操作需要定义以下五个变量
    public Integer pageTotalCount; // 总数据数量
    public Integer pageNo; // 当前页码号
    public Integer pageTotal; // 页码总数
    private Integer PageSize = PAGE_SIZE; // 定义每页展示items的数量
    private List<T> items; // 展示本页所展示的book数量

    public Page() {
    }

    public Page(Integer pageTotalCount, Integer pageNo, Integer pageTotal, Integer pageSize, List<T> items) {
        this.pageTotalCount = pageTotalCount;
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        PageSize = pageSize;
        this.items = items;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
