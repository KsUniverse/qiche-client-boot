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
package org.springblade.modules.develop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@TableName("blade_code")
@ApiModel(value = "Code对象", description = "Code对象")
public class Code implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "主键")
	@TableId(value = "id", type = IdType.ASSIGN_ID)
	private Long id;

	/**
	 * 数据模型主键
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "数据模型主键")
	private Long modelId;

	/**
	 * 模块名称
	 */
	@ApiModelProperty(value = "服务名称")
	private String serviceName;

	/**
	 * 模块名称
	 */
	@ApiModelProperty(value = "模块名称")
	private String codeName;

	/**
	 * 表名
	 */
	@ApiModelProperty(value = "表名")
	private String tableName;

	/**
	 * 实体名
	 */
	@ApiModelProperty(value = "表前缀")
	private String tablePrefix;

	/**
	 * 主键名
	 */
	@ApiModelProperty(value = "主键名")
	private String pkName;

	/**
	 * 后端包名
	 */
	@ApiModelProperty(value = "后端包名")
	private String packageName;

	/**
	 * 模版类型
	 */
	@ApiModelProperty(value = "模版类型")
	private String templateType;

	/**
	 * 作者信息
	 */
	@ApiModelProperty(value = "作者信息")
	private String author;

	/**
	 * 子表模型主键
	 */
	@ApiModelProperty(value = "子表模型主键")
	private String subModelId;

	/**
	 * 子表绑定外键
	 */
	@ApiModelProperty(value = "子表绑定外键")
	private String subFkId;

	/**
	 * 树主键字段
	 */
	@ApiModelProperty(value = "树主键字段")
	private String treeId;

	/**
	 * 树父主键字段
	 */
	@ApiModelProperty(value = "树父主键字段")
	private String treePid;

	/**
	 * 树名称字段
	 */
	@ApiModelProperty(value = "树名称字段")
	private String treeName;

	/**
	 * 基础业务模式
	 */
	@ApiModelProperty(value = "基础业务模式")
	private Integer baseMode;

	/**
	 * 包装器模式
	 */
	@ApiModelProperty(value = "包装器模式")
	private Integer wrapMode;

	/**
	 * 远程调用模式
	 */
	@ApiModelProperty(value = "远程调用模式")
	private Integer feignMode;

	/**
	 * 代码风格
	 */
	@ApiModelProperty(value = "代码风格")
	private String codeStyle;

	/**
	 * 后端路径
	 */
	@ApiModelProperty(value = "后端路径")
	private String apiPath;

	/**
	 * 前端路径
	 */
	@ApiModelProperty(value = "前端路径")
	private String webPath;

	/**
	 * 是否已删除
	 */
	@TableLogic
	@ApiModelProperty(value = "是否已删除")
	private Integer isDeleted;


}
