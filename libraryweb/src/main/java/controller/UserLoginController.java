package controller;

import pojo.Book;
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
 * @create: 2020-10-10 22:29
 **/
@WebServlet({"/user/login","/user/changepassword"})
public class UserLoginController extends HttpServlet {
    UserService userService=new UserService();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/user/changepassword".equals(req.getRequestURI())) userChangePassword(req,resp);
        if ("/user/login".equals(req.getRequestURI())) userLogin(req,resp);
    }
    protected void userChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        boolean res = userService.changePassword(user);
        if (res){

        }else {

        }
    }
    protected void userLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        req.setCharacterEncoding("utf-8");
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        UserService userService = new UserService();
        boolean flag = userService.loginUser(user);
        if (flag) {
            req.getSession().setAttribute("user",user);
            resp.sendRedirect("/index.jsp");
        } else {
            resp.sendRedirect("/view/user/login.jsp?msg="+ URLEncoder.encode("用户名或密码错误","utf-8"));
        }
    }
}
