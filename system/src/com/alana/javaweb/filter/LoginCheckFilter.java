import com.alana.javaweb.model.User;
import com.alana.javaweb.service.AccountService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * 在访问任意路径前都执行此过滤器的代码，如果未登录就发送一个重定向响应到客户端，指示客户端访问 login.jsp 页面。
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        String path = req.getRequestURI();
        boolean loggedIn = (session != null && session.getAttribute("user") != null);

        if (loggedIn || path.endsWith("login.jsp") || path.endsWith("register.jsp")||path.endsWith("register")||path.endsWith("login")) {
            chain.doFilter(request, response);
        } else {
//            检查cookies
            Cookie[] cookies = req.getCookies();
            String username = null;
            String password = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if("username".equals(name)){
                        username = cookie.getValue();
                    }else if("password".equals(name)){
                        password = cookie.getValue();
                    }
                }

                if(username != null && password != null){
                    AccountService accountService = new AccountService();
                    if( accountService.login(username, password)!=null){
                        // 获取session
                        session = req.getSession();

                        User user = new User(username, password);
                        session.setAttribute("user", user);

                        // 正确，表示登录成功
                        chain.doFilter(request, response);
                    }else {
                        ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");

                    }
                }
                else {
                    ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");

                }
            }else {
                ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/login.jsp");
            }


        }
    }
}
