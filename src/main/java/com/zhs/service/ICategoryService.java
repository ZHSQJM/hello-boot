package com.zhs.service;

import com.zhs.dto.CategoryDto;
import com.zhs.vo.CategoryVo;
import com.zhs.vo.PageVo;

import java.util.List;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 10:42
 * @Description:
 * @version: 1.0
 */
public interface ICategoryService {


    /**
     * 保存类目
     * @param categoryDto
     */
    void saveCategory(CategoryDto categoryDto);



    /**
     * 查询所有
     * @return
     */
    List<CategoryVo> findAll();




}
