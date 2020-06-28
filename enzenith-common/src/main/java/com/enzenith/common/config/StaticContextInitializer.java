package com.enzenith.common.config;

import com.enzenith.common.file.HttpFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *   加载自定义配置属性
 * @author jikunle
 * @date 2019/4/13 14:48
 */
@Component
public class StaticContextInitializer {
    @Autowired
    private SysProp sysProp;

    @PostConstruct
    public void init() {
        HttpFileUtils.setSysProp(sysProp);
    }
}
