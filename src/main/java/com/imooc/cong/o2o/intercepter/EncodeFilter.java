package com.imooc.cong.o2o.intercepter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: fangcong
 * @date: 2019/6/9
 */
public class EncodeFilter extends CharacterEncodingFilter {
    private String encoding = "UTF-8";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        request.setCharacterEncoding(this.encoding);//设置指定的编码
        response.setCharacterEncoding(this.encoding);
        filterChain.doFilter(request, response);
    }
}
