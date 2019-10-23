package com.zhs.service;

import com.zhs.condition.FileCondition;
import com.zhs.dto.FileDto;
import com.zhs.entity.SysFile;
import com.zhs.vo.FileVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/18 11:57
 * @Description:
 * @version: 1.0
 */
public interface IFileService {


    /**
     * 保存文件
     * @param fileDto
     */
    void saveFile(FileDto fileDto);


    /**
     * 条件查询获取分装的返回值
     * @param fileConfition
     * @return
     */
    List<FileVo> findAll(FileCondition fileConfition);


    /**
     * 条件查询分页 获取对象就是改实体类
     * @param fileCondition
     * @param page
     * @param pageSize
     * @return
     */
    Page<SysFile> findAllPage(FileCondition fileCondition,int page,int pageSize);



    FileVo getFileById(Long id);
}
