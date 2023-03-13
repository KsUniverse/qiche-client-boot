package org.springblade.modules.archives.vo;

import lombok.Data;

@Data
public class DownloadVO {

	private Long fileId;

	private String code;

	private String mac;
}
