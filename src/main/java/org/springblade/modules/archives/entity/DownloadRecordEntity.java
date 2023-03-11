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
package org.springblade.modules.archives.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;

/**
 * 文件下载记录 实体类
 *
 * @author BladeX
 * @since 2023-03-11
 */
@Data
@TableName("xy_download_record")
@ApiModel(value = "DownloadRecord对象", description = "文件下载记录")
@EqualsAndHashCode(callSuper = true)
public class DownloadRecordEntity extends TenantEntity {

	/**
	 * 激活码
	 */
	@ApiModelProperty(value = "激活码")
	private String code;
	/**
	 * 文件id
	 */
	@ApiModelProperty(value = "文件id")
	private Long fileId;

}
