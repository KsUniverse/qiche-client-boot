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
package org.springblade.modules.archives.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.modules.archives.entity.ActiveCodeEntity;
import org.springblade.modules.archives.vo.ActiveCodeVO;
import org.springblade.modules.archives.mapper.ActiveCodeMapper;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Date;
import java.util.Objects;

/**
 * 激活码 服务实现类
 *
 * @author BladeX
 * @since 2023-03-11
 */
@Service
public class ActiveCodeServiceImpl extends BaseServiceImpl<ActiveCodeMapper, ActiveCodeEntity> implements IActiveCodeService {

	@Override
	public IPage<ActiveCodeVO> selectActiveCodePage(IPage<ActiveCodeVO> page, ActiveCodeVO activeCode) {
		return page.setRecords(baseMapper.selectActiveCodePage(page, activeCode));
	}

	@Override
	public boolean bind(String code, String mac) {
		ActiveCodeEntity activeCodeEntity = baseMapper.selectOne(Wrappers.lambdaQuery(ActiveCodeEntity.class).eq(ActiveCodeEntity::getCode, code));
		verify(activeCodeEntity, code, mac);
		ActiveCodeEntity updateActiveCodeEntity = new ActiveCodeEntity();
		updateActiveCodeEntity.setMac(mac);
		if (baseMapper.update(updateActiveCodeEntity, Wrappers.lambdaQuery(ActiveCodeEntity.class)
			.eq(ActiveCodeEntity::getId, activeCodeEntity.getId())
			.eq(ActiveCodeEntity::getUpdateTime, activeCodeEntity.getUpdateTime())) <= 0) {
			throw new ServiceException("激活失败, 请稍后重试");
		}
		return true;
	}

	@Override
	public boolean verify(ActiveCodeEntity activeCodeEntity, String code, String mac) {
		if(activeCodeEntity == null) {
			activeCodeEntity = baseMapper.selectOne(Wrappers.lambdaQuery(ActiveCodeEntity.class).eq(ActiveCodeEntity::getCode, code));
		}
		if (activeCodeEntity == null) {
			throw new ServiceException("无效的激活码");
		}
		if (activeCodeEntity.getEndTime().before(new Date())) {
			throw new ServiceException("激活码已过期");
		}
		if (StringUtil.isNotBlank(activeCodeEntity.getMac()) && !Objects.equals(mac, activeCodeEntity.getMac())) {
			throw new ServiceException("激活码已被激活");
		}
		return true;
	}
}
