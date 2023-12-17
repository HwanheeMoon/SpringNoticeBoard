package com.test.jump.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
@PropertySource("classpath:/application.properties")
public class WebMvcConfig implements WebMvcConfigurer {

    // 파일 저장할 위치
    @Value("${upload.directory}")
    private String potoAdd;
    //web root가 아닌 외부 경로에 있는 리소스를 url로 불러올 수 있도록 설정
    //현재 localhost:8080/resources/user/1234.jpg
    //potoAdd에 실질적인 파일저장위치를 설정해줍니다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/static/images/**")
                .addResourceLocations("file" + potoAdd);
    }
}
