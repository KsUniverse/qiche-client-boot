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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.secure.BladeUser;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.archives.entity.DownloadRecordEntity;
import org.springblade.modules.archives.vo.DownloadRecordVO;
import org.springblade.modules.archives.wrapper.DownloadRecordWrapper;
import org.springblade.modules.archives.service.IDownloadRecordService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 文件下载记录 控制器
 *
 * @author BladeX
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-downloadRecord/downloadRecord")
@Api(value = "文件下载记录", tags = "文件下载记录接口")
public class DownloadRecordController extends BladeController {

	private final IDownloadRecordService downloadRecordService;

	/**
	 * 文件下载记录 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入downloadRecord")
	public R<DownloadRecordVO> detail(DownloadRecordEntity downloadRecord) {
		DownloadRecordEntity detail = downloadRecordService.getOne(Condition.getQueryWrapper(downloadRecord));
		return R.data(DownloadRecordWrapper.build().entityVO(detail));
	}
	/**
	 * 文件下载记录 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入downloadRecord")
	public R<IPage<DownloadRecordVO>> list(DownloadRecordEntity downloadRecord, Query query) {
		IPage<DownloadRecordEntity> pages = downloadRecordService.page(Condition.getPage(query), Condition.getQueryWrapper(downloadRecord));
		return R.data(DownloadRecordWrapper.build().pageVO(pages));
	}

	/**
	 * 文件下载记录 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入downloadRecord")
	public R<IPage<DownloadRecordVO>> page(DownloadRecordVO downloadRecord, Query query) {
		IPage<DownloadRecordVO> pages = downloadRecordService.selectDownloadRecordPage(Condition.getPage(query), downloadRecord);
		return R.data(pages);
	}

	/**
	 * 文件下载记录 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入downloadRecord")
	public R save(@Valid @RequestBody DownloadRecordEntity downloadRecord) {
		return R.status(downloadRecordService.save(downloadRecord));
	}

	/**
	 * 文件下载记录 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入downloadRecord")
	public R update(@Valid @RequestBody DownloadRecordEntity downloadRecord) {
		return R.status(downloadRecordService.updateById(downloadRecord));
	}

	/**
	 * 文件下载记录 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入downloadRecord")
	public R submit(@Valid @RequestBody DownloadRecordEntity downloadRecord) {
		return R.status(downloadRecordService.saveOrUpdate(downloadRecord));
	}

	/**
	 * 文件下载记录 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(downloadRecordService.deleteLogic(Func.toLongList(ids)));
	}


}
