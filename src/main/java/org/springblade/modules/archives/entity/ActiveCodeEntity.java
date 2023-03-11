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
 * 激活码 实体类
 *
 * @author BladeX
 * @since 2023-03-11
 */
@Data
@TableName("xy_active_code")
@ApiModel(value = "ActiveCode对象", description = "激活码")
@EqualsAndHashCode(callSuper = true)
public class ActiveCodeEntity extends TenantEntity {

	/**
	 * 激活码
	 */
	@ApiModelProperty(value = "激活码")
	private String code;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;
	/**
	 * mac地址
	 */
	@ApiModelProperty(value = "mac地址")
	private String mac;
	/**
	 * 激活码结束时间
	 */
	@ApiModelProperty(value = "激活码结束时间")
	private Date endTime;
	/**
	 * 激活码限制下载次数
	 */
	@ApiModelProperty(value = "激活码限制下载次数")
	private Integer downloadTimes;

}
