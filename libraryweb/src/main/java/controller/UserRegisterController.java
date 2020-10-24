package controller;

import pojo.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-10 21:28
 **/
    @WebServlet("/user/register")
public class UserRegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        UserService userService = new UserService();
        User registerUser = userService.saveUser(user);
        if (registerUser != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/index.jsp");
        } else {
            resp.sendRedirect("/view/user/register.jsp?msg="+ URLEncoder.encode("用户已存在","utf-8"));
        }
    }
}
