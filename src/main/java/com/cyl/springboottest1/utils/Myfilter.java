package com.cyl.springboottest1.utils;

import com.cyl.springboottest1.entity.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 我的过滤器，判断uri的session
 */
@Component
@WebFilter(urlPatterns = "/*",filterName = "abc")
public class Myfilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = "/updatepwd,/toupdatepwd,/logout,/upload,/select";
        List k = Arrays.asList(url.split(","));
        if (k.contains(request.getServletPath())) {
            User user = (User) request.getSession().getAttribute("se_name");
            if (user!=null){
                filterChain.doFilter(request,response);
                return;
            }
            request.getRequestDispatcher("/WEB-INF/jsp/index1.jsp").forward(request,response);
            return;
        }
        filterChain.doFilter(request,response);


    }

    @Override
    public void destroy() {
    }
}
