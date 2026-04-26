package web.tliaswebmanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter("/*")// 拦截所有请求
public class DemoFilter implements Filter {


    //初始化方法，在web服务器启动的时候执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(" init 初始化方法");
    }


    //拦截到请求之后执行，会执行多次
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info(" doFilter 拦截到请求");

        chain.doFilter(request, response);
    }

    //销毁方法，在web服务器关闭的时候执行，只执行一次
    @Override
    public void destroy() {
        log.info(" destroy 销毁方法");
    }
}
