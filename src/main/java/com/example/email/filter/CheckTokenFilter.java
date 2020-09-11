package com.example.email.filter;

import com.example.email.utils.TokenUtils;
import freemarker.core.JSONOutputFormat;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Declaration:
 * @Author: Mr.Zhaonan
 * @Date: 2020/8/20 15:54
 */
@WebFilter(filterName = "checkTokenFilter", urlPatterns = "/*")
@Order(2)
@Slf4j
public class CheckTokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
            System.out.println(path);
            if (path.equals("/api/login") || path.equals("/api/register") || path.equals("/api/getToken") || path.equals("/api/getSms") || path.equals("/api/testHttp")){
                log.info("Login to get Token permission");
                filterChain.doFilter(request,response);
            } else {
                Map<String, Object> map = TokenUtils.verifyToken(request.getHeader("AuthorizationToken"));
                if((Integer) map.get("resCode") == 0){
                    log.info("Token validation successful");
                    filterChain.doFilter(request,response);
                } else {
                    log.error("Token validation failed: "+map.get("msg"));
                    response.getWriter().println(JSONObject.toJSONString(map));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
