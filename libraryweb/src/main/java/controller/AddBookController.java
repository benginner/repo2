package controller;

import pojo.Book;
import service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-10 19:52
 **/
@WebServlet("/book/add")
public class AddBookController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Book book = new Book();
        book.getBookType().setId(Integer.parseInt(req.getParameter("bookType")));
        book.setBookname(req.getParameter("bookname"));
        book.setBookamount(Integer.parseInt(req.getParameter("booknumber")));
        BookService bookService = new BookService();
        boolean bookRes = bookService.saveBook(book);
        if (bookRes){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
    }
}
