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
package org.springblade.modules.archives.service;

import org.springblade.core.tool.api.R;
import org.springblade.modules.archives.entity.FileDirectoryEntity;
import org.springblade.modules.archives.vo.FileDirectoryVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 文件目录 服务类
 *
 * @author BladeX
 * @since 2023-03-11
 */
public interface IFileDirectoryService extends BaseService<FileDirectoryEntity> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param fileDirectory
	 * @return
	 */
	IPage<FileDirectoryVO> selectFileDirectoryPage(IPage<FileDirectoryVO> page, FileDirectoryVO fileDirectory);


    R<List<FileDirectoryVO>> directory(FileDirectoryVO fileDirectoryVO);
}
