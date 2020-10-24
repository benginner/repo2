package controller;

import pojo.Book;
import pojo.Dictionary;
import pojo.PageBean;
import service.BookService;
import service.DictionaryService;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-15 11:26
 **/
@WebServlet("/showbook")
public class ShowBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        //初始化列表
        DictionaryService dictionaryService=new DictionaryService();
        HashMap<String, List<Dictionary>> dictionaryHashMap = dictionaryService.addBookInit();
        req.setAttribute("dictionaryList",dictionaryHashMap.get("bookType"));
        //分页
        PageBean<Book> pageBean = new PageBean<>();
        String currentpage = req.getParameter("currentpage");
        String row = req.getParameter("row");
        if (StringUtils.isNotEmpty(currentpage)){
            pageBean.setCurrentPage(Integer.parseInt(currentpage));
        }
        if (StringUtils.isNotEmpty(row)){
            pageBean.setRows(Integer.parseInt(row));
        }

        //查询
        String bookName = req.getParameter("bookName");
        String bookType = req.getParameter("bookType");
        Book book = new Book();
        if (StringUtils.isNotEmpty(bookType)){
        book.getBookType().setId(Integer.parseInt(bookType));
        }
        if (StringUtils.isNotEmpty(bookName)){
            book.getBookType().setName(bookName);
        }
        pageBean.setEntity(book);
        BookService bookService = new BookService();
        PageBean<Book> bookPageBean = bookService.queryBooksByPages(pageBean);




        req.setAttribute("page",bookPageBean);
        req.setAttribute("totalPage",bookPageBean.getTotalPage());
        req.getRequestDispatcher("list.jsp").forward(req,resp);

    }
}
