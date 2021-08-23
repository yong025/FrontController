package org.zerock.fc.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Log4j2
@WebFilter(filterName = "signin", urlPatterns = {"/board/register", "/board/read"})
public class SigninFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Signin Fileter..............................");
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        HttpSession session = req.getSession();
//
//        //not yet login
//        if(session.getAttribute("member") == null){
//            res.sendRedirect("/board/login");
//            return;
//        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
