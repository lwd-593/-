package com.enzenith.common.constants;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swagger2 配置文件
 * @author jikunle
 * @date 2019/3/3 21:19
 */
public class Swagger2 {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage("com.enzenith"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统管理")
                .description("系统管理接口文档")
                //服务条款网址
                .termsOfServiceUrl("http://www.enzenith.com")
                .version("1.0")
             //   .contact(new Contact("jikunle", "http://www.enzenith.com", "ikunle@163.com"))
                .build();
    }
}
