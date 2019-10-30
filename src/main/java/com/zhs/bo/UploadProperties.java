package com.zhs.bo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @project: zblog
 * @author: zhs
 * @date: 2019/4/26 17:25
 * @package: com.yuanjie.config
 * @description:
 */

@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {

    private String dir;

    private String host;

    private String url;
}
