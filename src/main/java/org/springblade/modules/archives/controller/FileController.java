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

import java.io.IOException;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.resource.builder.oss.OssBuilder;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.archives.entity.FileEntity;
import org.springblade.modules.archives.vo.FileVO;
import org.springblade.modules.archives.wrapper.FileWrapper;
import org.springblade.modules.archives.service.IFileService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件目录 控制器
 *
 * @author BladeX
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-file/file")
@Api(value = "文件目录", tags = "文件目录接口")
public class FileController extends BladeController {

    private final IFileService fileService;

    private final OssBuilder ossBuilder;

    /**
     * 文件目录 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入file")
    public R<FileVO> detail(FileEntity file) {
        FileEntity detail = fileService.getOne(Condition.getQueryWrapper(file));
        return R.data(FileWrapper.build().entityVO(detail));
    }

    /**
     * 文件目录 分页
     */
    @GetMapping("/list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入file")
    public R<IPage<FileVO>> list(FileEntity file, Query query) {
        IPage<FileEntity> pages = fileService.page(Condition.getPage(query), Wrappers.lambdaQuery(FileEntity.class)
                .like(StringUtil.isNotBlank(file.getName()), FileEntity::getName, file.getName())
                .eq(FileEntity::getType, file.getType())
				.eq(file.getDirectoryId() != null, FileEntity::getDirectoryId, file.getDirectoryId())
                .orderByAsc(FileEntity::getName));
        return R.data(FileWrapper.build().pageVO(pages));
    }

    /**
     * 文件目录 自定义分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "分页", notes = "传入file")
    public R<IPage<FileVO>> page(FileVO file, Query query) {
        IPage<FileVO> pages = fileService.selectFilePage(Condition.getPage(query), file);
        return R.data(pages);
    }

    /**
     * 文件目录 新增
     */
    @PostMapping("/save")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入file")
    public R save(@Valid @RequestBody FileEntity file) {
        return R.status(fileService.save(file));
    }

    /**
     * 文件目录 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入file")
    public R update(@Valid @RequestBody FileEntity file) {
        return R.status(fileService.updateById(file));
    }

    /**
     * 文件目录 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "新增或修改", notes = "传入file")
    public R submit(@Valid @RequestBody FileEntity file) {
        return R.status(fileService.saveOrUpdate(file));
    }

    /**
     * 文件目录 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(fileService.deleteLogic(Func.toLongList(ids)));
    }


    @SneakyThrows
    @PostMapping("/put-file")
    public R<BladeFile> putFile(@RequestParam MultipartFile file, @RequestParam Integer type,
            @RequestParam Long directoryId) {
        BladeFile bladeFile = ossBuilder.template().putFile(file.getOriginalFilename(), file.getInputStream());
        FileEntity entity = new FileEntity();
        entity.setName(file.getOriginalFilename());
        entity.setDirectoryId(directoryId);
        entity.setPreview(false);
        entity.setType(type);
		if(type == 1 || type == 6) {
			entity.setCanDownload(false);
		} else {
			entity.setCanDownload(true);
		}
        entity.setUrl(bladeFile.getLink().replace("shanghai-internal", "shanghai"));
        fileService.save(entity);
        return R.data(bladeFile);
    }

}
