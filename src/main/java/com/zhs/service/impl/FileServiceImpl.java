package com.zhs.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zhs.condition.FileCondition;
import com.zhs.dao.FileRepository;
import com.zhs.dao.UserRepository;
import com.zhs.dto.FileDto;
import com.zhs.entity.QSysFile;
import com.zhs.entity.QSysUser;
import com.zhs.entity.SysFile;
import com.zhs.entity.SysUser;
import com.zhs.exception.ZhsException;
import com.zhs.service.IFileService;
import com.zhs.utils.SnowflakeIdWorker;
import com.zhs.vo.FileVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author: zhouhuasheng
 * @date: 2019/10/18 11:58
 * @Description:
 * @version: 1.0
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileRepository fileRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public void saveFile(FileDto fileDto) {

        Optional<SysUser> optional = userRepository.findById(fileDto.getUserId());
        if(!optional.isPresent()){
            throw new ZhsException("请输入正确的用户ID");
        }
        SysFile sysFile = new SysFile();
        BeanUtils.copyProperties(fileDto,sysFile);
        sysFile.setId(snowflakeIdWorker.nextId());
        sysFile.setCreateTime(new Date());
        sysFile.setUpdateTime(new Date());
        fileRepository.save(sysFile);
    }

    /**
     * 条件查询所有的自定义返回VO对象
     * @param fileConfition
     * @return
     */
    @Override
    public List<FileVo> findAll(FileCondition fileConfition) {
        QSysFile sysFile = QSysFile.sysFile;

        QSysUser sysUser = QSysUser.sysUser;
        //初始化组装条件(类似where 1=1)
        Predicate predicate = sysFile.isNotNull().or(sysFile.isNull());
        //执行动态条件拼装
        predicate = fileConfition.getFileName() == null ? predicate : ExpressionUtils.and(predicate, sysFile.fileName.like("%"+fileConfition.getFileName()+"%"));
        predicate = fileConfition.getUserId() == null ? predicate : ExpressionUtils.and(predicate, sysFile.userId.eq(fileConfition.getUserId()));
        predicate = fileConfition.getType() == null ? predicate : ExpressionUtils.and(predicate, sysFile.type.eq(fileConfition.getType()));
        List<FileVo> dtoList = jpaQueryFactory
                .select(
                        sysFile.id,
                        sysFile.fileName,
                        sysFile.fileSize,
                        sysFile.type,
                        sysFile.url,
                        sysUser.userName,
                        sysFile.createTime,
                        sysFile.updateTime
                )
                .from(sysFile,sysUser)
                .where(
                        sysFile.userId.eq(sysUser.id)
                )
                .where(predicate)

                .orderBy(sysFile.createTime.asc())
               // .offset(pageable.getOffset()) 分页
              //  .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(tuple -> FileVo.builder()
                        .id(tuple.get(sysFile.id))
                        .fileName(tuple.get(sysFile.fileName))
                        .fileSize(tuple.get(sysFile.fileSize))
                        .type(tuple.get(sysFile.type))
                        .url(tuple.get(sysFile.url))
                        .userName(tuple.get(sysUser.userName))
                        .createTime(tuple.get(sysFile.createTime))
                        .updateTime(tuple.get(sysFile.updateTime))
                        .build()
                )
                .collect(Collectors.toList());
        return dtoList;
    }

    @Override
    public Page<SysFile> findAllPage(FileCondition fileCondition, int page, int pageSize) {
        QSysFile sysFile = QSysFile.sysFile;
        //初始化组装条件(类似where 1=1)
        Predicate predicate = sysFile.isNotNull().or(sysFile.isNull());
        //执行动态条件拼装
        //执行动态条件拼装
        predicate = fileCondition.getFileName() == null ? predicate : ExpressionUtils.and(predicate, sysFile.fileName.like("%"+fileCondition.getFileName()+"%"));
        predicate = fileCondition.getUserId() == null ? predicate : ExpressionUtils.and(predicate, sysFile.userId.eq(fileCondition.getUserId()));
        predicate = fileCondition.getType() == null ? predicate : ExpressionUtils.and(predicate, sysFile.type.eq(fileCondition.getType()));
        Pageable pageable = PageRequest.of(page-1,pageSize);
        return  fileRepository.findAll(predicate, pageable);
    }

}
