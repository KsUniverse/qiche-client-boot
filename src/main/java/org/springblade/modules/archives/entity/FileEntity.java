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
import java.lang.Boolean;
import java.util.Date;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;

/**
 * 文件目录 实体类
 *
 * @author BladeX
 * @since 2023-03-11
 */
@Data
@TableName("xy_file")
@ApiModel(value = "File对象", description = "文件目录")
@EqualsAndHashCode(callSuper = true)
public class FileEntity extends TenantEntity {

	/**
	 * 文件名称
	 */
	@ApiModelProperty(value = "文件名称")
	private String name;
	/**
	 * 类型
	 */
	@ApiModelProperty(value = "类型")
	private Integer type;
	/**
	 * 二级目录
	 */
	@ApiModelProperty(value = "二级目录")
	private Long directoryId;
	/**
	 * oss路径
	 */
	@ApiModelProperty(value = "oss路径")
	private String url;
	/**
	 * 是否支持预览
	 */
	@ApiModelProperty(value = "是否支持预览")
	private Boolean preview;

	@ApiModelProperty(value = "能否下载")
	private Boolean canDownload;

}
