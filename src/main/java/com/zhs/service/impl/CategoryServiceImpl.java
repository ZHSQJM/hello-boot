package com.zhs.service.impl;

import com.zhs.dao.CategoryRepository;
import com.zhs.dao.ResourceRepository;
import com.zhs.dto.CategoryDto;
import com.zhs.entity.Category;
import com.zhs.entity.Resource;
import com.zhs.service.ICategoryService;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.CategoryVo;
import com.zhs.vo.PageVo;
import com.zhs.vo.ResourceVo;
import com.zhs.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author: zhouhuasheng
 * @date: 2019/10/28 10:44
 * @Description:
 * @version: 1.0
 */
@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto,category);
        category.setCreateTime(new Date());
        category.setUpdateTime(new Date());
        category.setStatus(0);
        category.setId(snowflakeIdWorker.nextId());
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryVo> findAll() {

        /**获取有效的类目*/
        List<Category> Categories = categoryRepository.findAllByStatus(0);

        List<CategoryVo> vos = new ArrayList<>(16);
        Categories.forEach(e->{
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setId(e.getId());
            categoryVo.setName(e.getName());
            /**获取有效的资源*/
            List<Resource> allByCategoryType = resourceRepository.findAllByCategoryTypeAndStatus(e.getId(),0);
            categoryVo.setList(allByCategoryType);
            vos.add(categoryVo);
        });
        return vos;
    }
}
