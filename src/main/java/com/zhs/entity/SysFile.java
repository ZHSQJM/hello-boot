package com.zhs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/16 9:39
 * @Description: 系统文件
 * @version: 1.0
 */
@Entity
@Table(name = "tb_sys_file")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SysFile {

    /**主键*/
    @Id
    @Column(length = 32)
    private Long id;

    /**文件名称*/
    private String fileName;

    /**文件大小*/
    private String fileSize;

    /**文件存储地址*/
    private String url;

    /**文件所属用户*/
    private Long userId;

    /**文件所属类型  0 图片 1 文件 2视频 3 音频*/
    private Integer type;

    /**创建时间*/
    private Date createTime;

    /**修改时间*/
    private Date updateTime;


    /**账号状态  0 正常  1 异常  2 删除*/
    @Column(nullable = false,length = 1)
    private Integer status;
    /**
     * 这是开发中不会使用这种强建模
     */
//    /**用户实体*/
//    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
//    @JoinColumn(name = "userId")
//    private SysUser sysUser;
}
