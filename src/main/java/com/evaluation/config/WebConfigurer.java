package com.evaluation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.core.env.Environment;

/**
 * @author: ChenXing
 * @date: 2023/4/26 16:37
 * @Description:
 */

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private Environment env;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (Boolean.parseBoolean(env.getProperty("spring.web.interceptors-enabled"))) {
            registry.addInterceptor(loginInterceptor)
                    .addPathPatterns("/**")
                    .excludePathPatterns("/static/**","/index","/login/login","/swagger-ui/**","/doc.html","/webjars/**",
                            "/error","/swagger-resources/**","/v3/**","/swagger-ui.html");
        }

    }

    //@Override
    //public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    registry.addResourceHandler("/swagger-ui/**")
    //            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
    //            .resourceChain(false);
    //    registry.addResourceHandler("/doc.html")
    //            .addResourceLocations("classpath:/META-INF/resources/");
    //    registry.addResourceHandler("/webjars/**")
    //            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    //}
}
