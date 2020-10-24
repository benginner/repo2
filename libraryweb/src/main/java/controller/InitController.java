package controller;

import pojo.Dictionary;
import service.DictionaryService;

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
 * @create: 2020-10-22 19:25
 **/
@WebServlet("/init/addbook")
public class InitController extends HttpServlet {
    DictionaryService dictionaryService = new DictionaryService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/init/addbook".equals(req.getRequestURI())) initAddBook(req, resp);
    }

    protected void initAddBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, List<Dictionary>> dictionaryHashMap = dictionaryService.addBookInit();
        req.setAttribute("dictionaryList",dictionaryHashMap.get("bookType"));
        req.getRequestDispatcher("/view/book/add.jsp").forward(req,resp);
    }
}
