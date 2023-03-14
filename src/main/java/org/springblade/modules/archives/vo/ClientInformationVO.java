package org.springblade.modules.archives.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientInformationVO {

	@NotNull(message = "介绍不能为空")
	private String data;
}
