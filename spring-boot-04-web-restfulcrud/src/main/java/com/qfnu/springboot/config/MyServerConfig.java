package com.qfnu.springboot.config;

import com.qfnu.springboot.filter.MyFilter;
import com.qfnu.springboot.listener.MyListener;
import com.qfnu.springboot.servlet.MyServlet;
import jakarta.servlet.Filter;
import jakarta.servlet.Servlet;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServerConfig {

    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>(new MyServlet(),"/myServlet");
        return servletServletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new MyFilter());
        filterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterFilterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> eventListenerServletListenerRegistrationBean = new ServletListenerRegistrationBean<MyListener>(new MyListener());
        return eventListenerServletListenerRegistrationBean;
    }

    //配置嵌入式的Servlet容器
    @Bean  //一定要将这个定制器加入到容器中
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> MyCustomizer(){
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            //定制嵌入式的Servlet容器相关的规则
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                factory.setPort(8083);
            }
        };
    }


}
