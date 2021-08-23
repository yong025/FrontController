package org.zerock.fc.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "logFilter", urlPatterns = {"/*"})//모두에게 적용
@Log4j2
public class LogFilter implements Filter { // implement Filter(javax.servlet
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("-------------------------------------");
        log.info("-------------------------------------");
        log.info("init filter");
        log.info("-------------------------------------");
        log.info("-------------------------------------");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //필터가 동작하는 방법은 dofilter이다.
        log.info("doFilter.................................");
        log.info("doFilter.................................");
        log.info("doFilter.................................");
        log.info("doFilter.................................");

        request.setCharacterEncoding("UTF-8");
        //FilterChain chain에 걸려있는 정보를 넘겨준다.
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
