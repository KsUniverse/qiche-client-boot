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

import org.springblade.modules.archives.entity.ActiveCodeEntity;
import org.springblade.modules.archives.vo.ActiveCodeVO;
import org.springblade.modules.archives.mapper.ActiveCodeMapper;
import org.springblade.modules.archives.service.IActiveCodeService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

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


}
