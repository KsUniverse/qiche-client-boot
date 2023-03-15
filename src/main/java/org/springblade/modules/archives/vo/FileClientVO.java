package org.springblade.modules.archives.vo;

import lombok.Data;

import java.util.List;

@Data
public class FileClientVO {

	private Long id;

	private String name;

	private String url;

	private Boolean canDownload;
	private List<FileClientVO> files;
}
