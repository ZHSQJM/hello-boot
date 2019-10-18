package com.zhs.condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/18 12:24
 * @Description:
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCondition {

    /**文件名称*/
    private String fileName;

    /**文件所属用户*/
    private Long userId;

    /**文件所属类型  0 图片 1 文件 2视频 3 音频*/
    private Integer type;
}
