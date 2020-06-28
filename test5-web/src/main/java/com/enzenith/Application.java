/**
 * @author: LinWeiDa
 * @date: 2020-04-26 11:30
 */
package com.enzenith;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * {阐述类的作用}
 * @author: LinWeiDa
 * @date: 2020-04-26 11:30
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@ServletComponentScan
@MapperScan("com.enzenith.test.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
