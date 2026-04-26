package web.tliaswebmanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import web.tliaswebmanagement.utils.JwtUtil;

import java.io.IOException;

@Slf4j
@WebFilter("/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {



        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //1 获取请求路径
        String requestURI=request.getRequestURI();// /emps/login


        //2 判断是否是登录请求，如果路径中包含/login，则放行
        if(requestURI.contains("/login")){
            log.info("登录请求，放行");
            chain.doFilter(request,response);
            return;
        }


        //3 获取请求头中的令牌（token）
        String token=request.getHeader("token");


        //4 判断令牌是否存在，如果不存在，说明用户未登录，拦截并返回提示信息（401状态码）
        if(token==null|| token.isEmpty()){
            log.info("令牌为空，拦截并返回提示信息（401状态码）");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        //5 令牌存在，则校验令牌，如果校验失败，拦截并返回提示信息（401状态码）
        try{
            JwtUtil.parseToken(token);
            log.info("令牌校验成功");
        }catch(Exception e){
            log.info("令牌校验失败，拦截并返回提示信息（401状态码）");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        //6 令牌校验成功，则放行请求
        chain.doFilter(request,response);



    }
}
