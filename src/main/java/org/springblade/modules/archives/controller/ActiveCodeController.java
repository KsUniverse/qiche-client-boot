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

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.modules.archives.entity.ActiveCodeEntity;
import org.springblade.modules.archives.vo.ActiveCodeVO;
import org.springblade.modules.archives.wrapper.ActiveCodeWrapper;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 激活码 控制器
 *
 * @author BladeX
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("blade-activeCode/activeCode")
@Api(value = "激活码", tags = "激活码接口")
public class ActiveCodeController extends BladeController {

	private final IActiveCodeService activeCodeService;

	/**
	 * 激活码 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入activeCode")
	public R<ActiveCodeVO> detail(ActiveCodeEntity activeCode) {
		ActiveCodeEntity detail = activeCodeService.getOne(Condition.getQueryWrapper(activeCode));
		return R.data(ActiveCodeWrapper.build().entityVO(detail));
	}

	/**
	 * 激活码 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入activeCode")
	public R<IPage<ActiveCodeVO>> list(ActiveCodeEntity activeCode, Query query) {
		IPage<ActiveCodeEntity> pages = activeCodeService.page(Condition.getPage(query),
			Wrappers.lambdaQuery(ActiveCodeEntity.class).like(StringUtil.isNotBlank(activeCode.getCode()),
					ActiveCodeEntity::getCode, activeCode.getCode())
				.like(StringUtil.isNotBlank(activeCode.getPhone()), ActiveCodeEntity::getPhone, activeCode.getPhone())
				.orderByDesc(ActiveCodeEntity::getUpdateTime));
		return R.data(ActiveCodeWrapper.build().pageVO(pages));
	}

	/**
	 * 激活码 自定义分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入activeCode")
	public R<IPage<ActiveCodeVO>> page(ActiveCodeVO activeCode, Query query) {
		IPage<ActiveCodeVO> pages = activeCodeService.selectActiveCodePage(Condition.getPage(query), activeCode);
		return R.data(pages);
	}

	/**
	 * 激活码 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入activeCode")
	public R save(@Valid @RequestBody ActiveCodeEntity activeCode) {
		return R.status(activeCodeService.save(activeCode));
	}

	/**
	 * 激活码 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入activeCode")
	public R update(@Valid @RequestBody ActiveCodeEntity activeCode) {
		return R.status(activeCodeService.updateById(activeCode));
	}

	/**
	 * 激活码 新增或修改
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入activeCode")
	public R submit(@Valid @RequestBody ActiveCodeEntity activeCode) {
		return R.status(activeCodeService.saveOrUpdate(activeCode));
	}

	/**
	 * 激活码 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(activeCodeService.deleteLogic(Func.toLongList(ids)));
	}


	@GetMapping("/generator")
	public R generator() {
		return R.data(UUID.fastUUID().toString());
	}
}
