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
package org.springblade.modules.archives.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.modules.archives.entity.FileEntity;
import org.springblade.modules.archives.vo.FileVO;
import java.util.Objects;

/**
 * 文件目录 包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2023-03-11
 */
public class FileWrapper extends BaseEntityWrapper<FileEntity, FileVO>  {

	public static FileWrapper build() {
		return new FileWrapper();
 	}

	@Override
	public FileVO entityVO(FileEntity file) {
		FileVO fileVO = Objects.requireNonNull(BeanUtil.copy(file, FileVO.class));

		//User createUser = UserCache.getUser(file.getCreateUser());
		//User updateUser = UserCache.getUser(file.getUpdateUser());
		//fileVO.setCreateUserName(createUser.getName());
		//fileVO.setUpdateUserName(updateUser.getName());

		return fileVO;
	}


}
