package service;

import dao.BookDao;
import pojo.Book;
import pojo.PageBean;

import java.util.List;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-10 20:15
 **/
public class BookService {
    BookDao bookDao = new BookDao();
    public boolean saveBook(Book book){
        book.setId(bookDao.querySingle("select seq_library_book.nextval from dual"));
        int res = bookDao.saveBook(book);
        if (res>0)
            return true;
        return false;
    }
public  PageBean<Book> queryBooksByPages(PageBean<Book> pageBean){
    pageBean.setTotalCount(bookDao.queryTotalCount(pageBean));
    pageBean.setList(bookDao.queryBooksByPage(pageBean));
    return pageBean;
}

    /*public PageBean<Book> queryBooksByPage(String _currentPage,String _row){
        if (Integer.parseInt(_currentPage)<1)_currentPage="1";
        int currentPage=Integer.parseInt(_currentPage);
        int row=Integer.parseInt(_row);
        PageBean<Book> pageBean = new PageBean<>();
        BookDao bookDao = new BookDao();
        int totalCount = bookDao.queryTotalCount();
        int totalPage=(totalCount%row)==0?totalCount/row:totalCount/row+1;
        if (totalPage<currentPage){
            pageBean.setRows(totalPage);
            pageBean.setList(bookDao.queryBooksByPage((totalPage-1)*row+1,(totalPage)*row));
            pageBean.setCurrentPage(totalPage);
        }else {
            pageBean.setCurrentPage(currentPage);
            pageBean.setRows(row);
            pageBean.setList(bookDao.queryBooksByPage(currentPage==1?(currentPage-1)*row:(currentPage-1)*row+1,(currentPage)*row));
        }
        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }*/
}
