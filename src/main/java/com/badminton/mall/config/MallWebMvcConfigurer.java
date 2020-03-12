package com.badminton.mall.config;


import com.badminton.mall.common.Constants;
import com.badminton.mall.interceptor.AdminLoginInterceptor;
import com.badminton.mall.interceptor.BusinessLoginInterceptor;
import com.badminton.mall.interceptor.CartNumberInterceptor;
import com.badminton.mall.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MallWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private CartNumberInterceptor cartNumberInterceptor;
    @Autowired
    private BusinessLoginInterceptor businessLoginInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/admin为前缀的url路径（后台登陆拦截）
        registry.addInterceptor(adminLoginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/register")
                .excludePathPatterns("/admin/dist/**")
                .excludePathPatterns("/admin/plugins/**");
        // 添加一个拦截器，拦截以/business为前缀的url路径（后台登陆拦截）
        registry.addInterceptor(businessLoginInterceptor)
                .addPathPatterns("/business/**")
                .excludePathPatterns("/business/login")
                .excludePathPatterns("/business/register")
                .excludePathPatterns("/business/dist/**")
                .excludePathPatterns("/business/plugins/**");
        // 购物车中的数量统一处理
        registry.addInterceptor(cartNumberInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout");
        // 商城页面登陆拦截
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/login")
                .excludePathPatterns("/logout")
                .addPathPatterns("/goods/detail/**")
                .addPathPatterns("/shop-cart")
                .addPathPatterns("/shop-cart/**")
                .addPathPatterns("/saveOrder")
                .addPathPatterns("/orders")
                .addPathPatterns("/orders/**")            
                .addPathPatterns("/personal")
                .addPathPatterns("/personal/updateInfo")
                .addPathPatterns("/selectPayType")
                .addPathPatterns("/payPage");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/goods-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
