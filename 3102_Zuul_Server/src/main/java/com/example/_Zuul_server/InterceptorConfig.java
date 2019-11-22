//package com.example._Zuul_server;
//
//import com.example._Zuul_server.Interceptors.CachingInterceptor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class InterceptorConfig implements WebMvcConfigurer {
//
//    Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new CachingInterceptor());
//        logger.info("Interceptor Registered");
//    }
//}