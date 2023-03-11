/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.archives.controller;

import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.common.constant.CommonConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.archives.entity.FileEntity;
import org.springblade.modules.archives.service.IFileService;
import org.springblade.modules.system.entity.Dict;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.archives.entity.FileDirectoryEntity;
import org.springblade.modules.archives.vo.FileDirectoryVO;
import org.springblade.modules.archives.wrapper.FileDirectoryWrapper;
import org.springblade.modules.archives.service.IFileDirectoryService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 文件目录 控制器
 *
 * @author BladeX
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-fileDirectory/fileDirectory")
@Api(value = "文件目录", tags = "文件目录接口")
public class FileDirectoryController extends BladeController {

    private final IFileDirectoryService fileDirectoryService;

    private final IFileService fileService;

    /**
     * 文件目录 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入fileDirectory")
    public R<FileDirectoryVO> detail(FileDirectoryEntity fileDirectory) {
        FileDirectoryEntity detail = fileDirectoryService.getOne(Condition.getQueryWrapper(fileDirectory));
        return R.data(FileDirectoryWrapper.build().entityVO(detail));
    }

    /**
     * 文件目录 分页
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入fileDirectory")
    public R<IPage<FileDirectoryVO>> list(FileDirectoryEntity fileDirectory, Query query) {
        IPage<FileDirectoryEntity> pages = fileDirectoryService.page(Condition.getPage(query),
                Wrappers.lambdaQuery(FileDirectoryEntity.class)
                        .like(StringUtil.isNotBlank(fileDirectory.getName()), FileDirectoryEntity::getName,
                                fileDirectory.getName())
                        .eq(fileDirectory.getType() != null, FileDirectoryEntity::getType, fileDirectory.getType())
                        .orderByDesc(FileDirectoryEntity::getCreateTime));
        return R.data(FileDirectoryWrapper.build().pageVO(pages));
    }

    /**
     * 文件目录 自定义分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "分页", notes = "传入fileDirectory")
    public R<IPage<FileDirectoryVO>> page(FileDirectoryVO fileDirectory, Query query) {
        IPage<FileDirectoryVO> pages = fileDirectoryService.selectFileDirectoryPage(Condition.getPage(query),
                fileDirectory);
        return R.data(pages);
    }

    /**
     * 文件目录 新增
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入fileDirectory")
    public R save(@Valid @RequestBody FileDirectoryEntity fileDirectory) {
        return R.status(fileDirectoryService.save(fileDirectory));
    }

    /**
     * 文件目录 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入fileDirectory")
    public R update(@Valid @RequestBody FileDirectoryEntity fileDirectory) {
        return R.status(fileDirectoryService.updateById(fileDirectory));
    }

    /**
     * 文件目录 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "新增或修改", notes = "传入fileDirectory")
    public R submit(@Valid @RequestBody FileDirectoryEntity fileDirectory) {
        return R.status(fileDirectoryService.saveOrUpdate(fileDirectory));
    }

    /**
     * 文件目录 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        List<Long> longList = Func.toLongList(ids);
        if (CollectionUtil.isEmpty(longList)) {
            return R.status(false);
        }
        if (fileService.count(Wrappers.lambdaQuery(FileEntity.class).in(FileEntity::getDirectoryId, longList)) > 0) {
            throw new ServiceException("二级目录下存在文件, 无法删除二级目录");
        }
        return R.status(fileDirectoryService.removeBatchByIds(Func.toLongList(ids)));
    }

    /**
     * 字典键值列表
     */
    @GetMapping("/select")
    @ApiOperationSupport(order = 10)
    public R<List<FileDirectoryEntity>> select(Integer type) {
        List<FileDirectoryEntity> list = fileDirectoryService
                .list(Wrappers.<FileDirectoryEntity>query().lambda().eq(FileDirectoryEntity::getType, type));
        return R.data(list);
    }
}
