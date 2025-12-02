package com.taskTracking.logger;

import javax.servlet.*;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String ip = request.getRemoteAddr();
        String path = request instanceof HttpServletRequest ? ((HttpServletRequest) request).getRequestURI() : "";
        System.out.println("[REQUEST]" + LocalDateTime.now() + " | IP: " + ip + " | Path: " + path);
        chain.doFilter(request, response);
    }
}
