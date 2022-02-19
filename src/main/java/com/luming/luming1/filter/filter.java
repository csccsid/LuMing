package com.luming.luming1.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//目前没作用，但是方便以后拓展功能
@WebFilter(filterName = "Filter1", value = "/*")
public class filter implements Filter
{
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        System.out.println("通过过滤器");
        chain.doFilter(request, response);
        /*
        String uri = request.getRequestURI();
        if(uri.endsWith(""));
         */
    }
}
