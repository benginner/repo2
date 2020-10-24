package pojo;

import java.util.List;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-15 14:49
 **/
public class PageBean<T> {
    private Integer totalCount;//总记录数
    private Integer totalPage;//总页码
    private List<T> list;//每页数据
    private Integer currentPage;//当前页码
    private Integer rows;//每页记录数
    private T entity;
    private Integer start;
    private Integer end;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        totalPage=(int)Math.ceil(getTotalCount()*1.0/getRows());
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getCurrentPage() {
        if (currentPage==null||currentPage==0){
            currentPage=1;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRows() {
        if (rows==null||rows==0){
            rows=5;
        }
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getStart() {
        start=(getCurrentPage()-1)*getRows()+1;
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        end=(getCurrentPage()*getRows());
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public PageBean(T entity) {
        this.entity = entity;
    }

    public PageBean() {
    }

    public PageBean(Integer totalCount, Integer totalPage, List<T> list, Integer currentPage, Integer rows, T entity, Integer start, Integer end) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
        this.entity = entity;
        this.start = start;
        this.end = end;
    }
}
