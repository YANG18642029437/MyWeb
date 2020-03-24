package com.czxy.upLoad.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("com.czxy.upload")
@Data
@Component
public class ImageConfig {
    /** 图片的上传地址 */
    public String imagePath;

}
