package com.ohgiraffers.chap06securityjwt.auth.config;

import com.ohgiraffers.chap06securityjwt.auth.filter.HeaderFilter;
import com.ohgiraffers.chap06securityjwt.auth.interceptor.JwtTokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    //정적자원(리소스)에 접근을 하게 하기 위하 ㄴ리소스 경로 설정
    // 정적 자원은 일반적으로 html css js 이미지 등포함
    // 핸들링 인터셉트도 넣고

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/static/", "classpath:/public/",
            "classpath:/", "classpath:/resources/",
            "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //모든 경로의 요청에 대해 정적 자원을 제공하도록 설정
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    //특정 필터를 등록하고, 필터가 모든 요청에 대해 동작하도록 설정
    @Bean
    public FilterRegistrationBean<HeaderFilter> getfilterRegistrationBean() {
        FilterRegistrationBean<HeaderFilter> registrationBean =
                new FilterRegistrationBean<HeaderFilter>(createHeaderFilter());
        registrationBean.setOrder(Integer.MIN_VALUE); // 필터의 우선순위를 가장 먼저
        registrationBean.addUrlPatterns("/*"); // 모든 요청에대해 적용
        return registrationBean;
    }

    //headerFilter 빈 생성
    @Bean
    public HeaderFilter createHeaderFilter() {
        return new HeaderFilter(); //필터하나~
    }

    @Bean
    public JwtTokenInterceptor jwtTokenInterceptor() {
        return new JwtTokenInterceptor(); //인터셉터하나~~
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(jwtTokenInterceptor()) //토큰 유효성검사
               .addPathPatterns("/**") //모든 요청 경로에 대해 인터셉터 적용
               .excludePathPatterns("/login","/signup"); // 로그인 회원가입 제외
    }
}
