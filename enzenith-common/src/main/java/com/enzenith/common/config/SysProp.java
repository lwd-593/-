package com.enzenith.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  系统自定义配置
 * @author jikunle
 * @date 2019/4/10 8:55
 */
@Component
public class SysProp {
    @Value(value = "${sys.file.serverUrl:https://files.enzenith.com/fileweb/api/v1/resources/}")
    private String fileServerUrl;

    public String getFileServerUrl() {
        return fileServerUrl;
    }

    public void setFileServerUrl(String fileServerUrl) {
        this.fileServerUrl = fileServerUrl;
    }
}
