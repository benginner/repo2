package controller;

import pojo.User;
import service.UserService;

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
 * @create: 2020-10-17 17:39
 **/
@WebServlet({"/user/addbook"})
public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/user/addbook".equals(req.getRequestURI())) userAddBook(req,resp);
    }
    protected void userAddBook (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        User user = (User)req.getSession().getAttribute("user");
        String[] uids = req.getParameterValues("uid");
        UserService userService = new UserService();
        userService.addBook(user,uids);
    }
}
