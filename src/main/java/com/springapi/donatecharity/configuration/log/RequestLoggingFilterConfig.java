package com.springapi.donatecharity.configuration.log;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;


@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public FilterRegistrationBean< CustomUrlFilter > filterRegistrationBean() {
        FilterRegistrationBean < CustomUrlFilter > registrationBean = new FilterRegistrationBean();
        CustomUrlFilter customURLFilter = new CustomUrlFilter();
        registrationBean.setFilter(customURLFilter);
        return registrationBean;
    }


}
