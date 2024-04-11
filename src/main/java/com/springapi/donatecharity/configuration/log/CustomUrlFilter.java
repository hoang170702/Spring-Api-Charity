package com.springapi.donatecharity.configuration.log;


import com.springapi.donatecharity.utils.GsonParserUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;


import java.io.BufferedReader;
import java.util.logging.LogRecord;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;


@Slf4j
public class CustomUrlFilter  extends GenericFilterBean {
    private static final String REQUEST_ID = "request_id";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestId = UUID.randomUUID().toString();
        servletRequest.setAttribute(REQUEST_ID, requestId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
