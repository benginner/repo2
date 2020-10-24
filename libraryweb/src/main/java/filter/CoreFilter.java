package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @program: libraryweb
 * 描述：
 * @author: LJQ
 * @create: 2020-10-21 15:45
 **/
@WebFilter("/*")
public class CoreFilter implements Filter {
    private final String[] accesses=new String[]{
            ".js",".html",".jpg",".gif","png","login.jsp"
            ,"/view/user/register.jsp","/view/user/login.jsp"
            ,"/view/user/changePassword.jsp","/user/register"
            ,"/user/login","/user/changepassword"
    };
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String requestURI = request.getRequestURI();
        for (String access : accesses) {
            if (requestURI.endsWith(access)){
                filterChain.doFilter(request,response);
                return;
            }
        }
        if (request.getSession().getAttribute("user")==null){
            response.sendRedirect("/view/user/login.jsp?msg="+ URLEncoder.encode("请登陆","utf-8"));
        }
        filterChain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
