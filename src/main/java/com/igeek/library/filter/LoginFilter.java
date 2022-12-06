package com.igeek.library.filter;

import com.igeek.library.entity.Admin;
import com.igeek.library.entity.ReaderCard;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        ReaderCard readerCard = (ReaderCard) session.getAttribute("readercard");
        if (admin != null || readerCard != null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            servletRequest.getRequestDispatcher("/").forward(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
