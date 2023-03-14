package org.springblade.modules.archives.vo;

import lombok.Data;

@Data
public class FileClientVO {

	private Long id;

	private String name;

	private String url;

	private Boolean canDownload;
}
