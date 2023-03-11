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

import org.springblade.modules.archives.entity.FileDirectoryEntity;
import org.springblade.modules.archives.vo.FileDirectoryVO;
import org.springblade.modules.archives.mapper.FileDirectoryMapper;
import org.springblade.modules.archives.service.IFileDirectoryService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 文件目录 服务实现类
 *
 * @author BladeX
 * @since 2023-03-11
 */
@Service
public class FileDirectoryServiceImpl extends BaseServiceImpl<FileDirectoryMapper, FileDirectoryEntity> implements IFileDirectoryService {

	@Override
	public IPage<FileDirectoryVO> selectFileDirectoryPage(IPage<FileDirectoryVO> page, FileDirectoryVO fileDirectory) {
		return page.setRecords(baseMapper.selectFileDirectoryPage(page, fileDirectory));
	}


}
